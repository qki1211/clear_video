package io.quantumknight.common.swing.webcam.components;
/********************************************************************************************
//* Filename: 		WebcamTask.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    WRAPPER IMPLEMENTATION - SARXOS WEBCAM TASK
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

public abstract class WebcamTask {

	private boolean doSync = true;
	private WebcamProcessor processor = null;
	private WebcamDevice device = null;
	private Throwable throwable = null;

	public WebcamTask(boolean threadSafe, WebcamDevice device) {
		this.doSync = !threadSafe;
		this.device = device;
		this.processor = WebcamProcessor.getInstance();
	}

	public WebcamTask(WebcamDriver driver, WebcamDevice device) {
		this(driver == null ? false : driver.isThreadSafe(), device);
	}

	public WebcamTask(WebcamDevice device) {
		this(false, device);
	}

	public WebcamDevice getDevice() {
		return device;
	}

	/**
	 * Process task by processor thread.
	 * 
	 * @throws InterruptedException when thread has been interrupted
	 */
	public void process() throws InterruptedException {

		boolean alreadyInSync = Thread.currentThread() instanceof WebcamProcessor.ProcessorThread;

		if (alreadyInSync) {
			handle();
		} else {
			if (doSync) {
				if (processor == null) {
					throw new RuntimeException("Driver should be synchronized, but processor is null");
				}
				processor.process(this);
			} else {
				handle();
			}
		}
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable t) {
		this.throwable = t;
	}

	public abstract void handle();
}
