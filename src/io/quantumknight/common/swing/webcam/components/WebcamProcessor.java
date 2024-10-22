package io.quantumknight.common.swing.webcam.components;
/********************************************************************************************
//* Filename: 		WebcamProcessor.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    WRAPPER IMPLEMENTATION - SARXOS WEBCAM PROCESSOR
//* 				
//* 				
//* ******************************************************************************************
//* 				
//* 
//* 				SOFTWARE LICENSE AGREEMENT:
//* 				--------------------------------------------------------------------------
//* 				Licensed under the Apache License, Version 2.0 (the "License");
//* 				you may not use this file except in compliance with the License.
//* 				You may obtain a copy of the License at
//* 
//*    					https://www.apache.org/licenses/LICENSE-2.0
//* 
//* 				Unless required by applicable law or agreed to in writing, software
//* 				distributed under the License is distributed on an "AS IS" BASIS,
//* 				WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//* 				See the License for the specific language governing permissions and
//* 				limitations under the License.
//* 
//* ******************************************************************************************
//* 
//* 				COMMODITY CLASSIFICATION : UNITED STATES DEPARTMENT OF COMMERCE
//* 				--------------------------------------------------------------------------
//* 				THIS ENCRYPTION ITEM PROVIDING AN OPEN CRYPTOGRAPHIC INTERFACE IS AUTHORIZED
//* 				FOR LICENSE EXCEPTION ENC UNDER SECTIONS 740.17 (A) AND (B)(2) OF THE EXPORT
//* 				ADMINISTRATION REGULATIONS (EAR). 
//* 
//* 				UNITED STATES DEPARTMENT OF COMMERCE
//* 				BUREAU OF INDUSTRY AND SECURITY 
//* 				WASHINGTON, D.C. 20230
//* 
//* 				BIS/EA/STC/IT
//* 
/********************************************************************************************/

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import io.quantumknight.video.framework.io.LogManager;
import io.quantumknight.video.framework.io.Logger;


public class WebcamProcessor {

	private static Logger LOG = LogManager.getLogger(WebcamProcessor.class);

	/**
	 * Thread doing supersync processing.
	 *
	 * @author sarxos
	 */
	public static final class ProcessorThread extends Thread {

		private static final AtomicInteger N = new AtomicInteger(0);

		public ProcessorThread(Runnable r) {
			super(r, String.format("atomic-processor-%d", N.incrementAndGet()));
		}
	}

	/**
	 * Thread factory for processor.
	 *
	 * @author Bartosz Firyn (SarXos)
	 */
	private static final class ProcessorThreadFactory implements ThreadFactory {

		@Override
		public Thread newThread(Runnable r) {
			Thread t = new ProcessorThread(r);
			t.setUncaughtExceptionHandler(WebcamExceptionHandler.getInstance());
			t.setDaemon(true);
			return t;
		}
	}

	/**
	 * Heart of overall processing system. This class process all native calls wrapped in tasks, by
	 * doing this all tasks executions are super-synchronized.
	 *
	 * @author Bartosz Firyn (SarXos)
	 */
	private static final class AtomicProcessor implements Runnable {

		private SynchronousQueue<WebcamTask> inbound = new SynchronousQueue<WebcamTask>(true);
		private SynchronousQueue<WebcamTask> outbound = new SynchronousQueue<WebcamTask>(true);

		/**
		 * Process task.
		 *
		 * @param task the task to be processed
		 * @throws InterruptedException when thread has been interrupted
		 */
		public void process(WebcamTask task) throws InterruptedException {
			inbound.put(task);

			Throwable t = outbound.take().getThrowable();
			if (t != null) {
				throw new WebcamException("Cannot execute task", t);
			}
		}

		@Override
		public void run() {
			while (true) {
				WebcamTask t = null;
				try {
					(t = inbound.take()).handle();
				} catch (InterruptedException e) {
					break;
				} catch (Throwable e) {
					if (t != null) {
						t.setThrowable(e);
					}
				} finally {
					if (t != null) {
						try {
							outbound.put(t);
						} catch (InterruptedException e) {
							break;
						} catch (Exception e) {
							throw new RuntimeException("Cannot put task into outbound queue", e);
						}
					}
				}
			}
		}
	}

	/**
	 * Is processor started?
	 */
	private static final AtomicBoolean started = new AtomicBoolean(false);

	/**
	 * Execution service.
	 */
	private static ExecutorService runner = null;

	/**
	 * Static processor.
	 */
	private static final AtomicProcessor processor = new AtomicProcessor();

	/**
	 * Singleton instance.
	 */
	private static final WebcamProcessor INSTANCE = new WebcamProcessor();;

	private WebcamProcessor() {
	}

	/**
	 * Process single webcam task.
	 *
	 * @param task the task to be processed
	 * @throws InterruptedException when thread has been interrupted
	 */
	public void process(WebcamTask task) throws InterruptedException {

		if (started.compareAndSet(false, true)) {
			runner = Executors.newSingleThreadExecutor(new ProcessorThreadFactory());
			runner.execute(processor);
		}

		if (!runner.isShutdown()) {
			processor.process(task);
		} else {
			throw new RejectedExecutionException("Cannot process because processor runner has been already shut down");
		}
	}

	public void shutdown() {
		if (started.compareAndSet(true, false)) {

			LOG.debug("Shutting down webcam processor");

			runner.shutdown();

			LOG.debug("Awaiting tasks termination");

			while (runner.isTerminated()) {

				try {
					runner.awaitTermination(100, TimeUnit.MILLISECONDS);
				} catch (InterruptedException e) {
					return;
				}

				runner.shutdownNow();
			}

			LOG.debug("All tasks has been terminated");
		}

	}

	public static synchronized WebcamProcessor getInstance() {
		return INSTANCE;
	}
}
