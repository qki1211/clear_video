package io.quantumknight.common.swing.webcam.components.driver;
/********************************************************************************************
//* Filename: 		WebcamDefaultDriver.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    WRAPPER IMPLEMENTATION - SARXOS WEBCAM DEFAULT DRIVER
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.bridj.Pointer;

import io.quantumknight.common.swing.webcam.components.WebcamDevice;
import io.quantumknight.common.swing.webcam.components.WebcamDiscoverySupport;
import io.quantumknight.common.swing.webcam.components.WebcamDriver;
import io.quantumknight.common.swing.webcam.components.WebcamTask;
import io.quantumknight.video.framework.io.LogManager;
import io.quantumknight.video.framework.io.Logger;

public class WebcamDefaultDriver implements WebcamDriver, WebcamDiscoverySupport {

	static {
		if (!"true".equals(System.getProperty("webcam.debug"))) {
			System.setProperty("bridj.quiet", "true");
		}
	}

	private static class WebcamNewGrabberTask extends WebcamTask {

		private AtomicReference<OpenIMAJGrabber> grabber = new AtomicReference<OpenIMAJGrabber>();

		public WebcamNewGrabberTask(WebcamDriver driver) {
			super(driver, null);
		}

		public OpenIMAJGrabber newGrabber() {
			try {
				process();
			} catch (InterruptedException e) {
				LOG.error("Processor has been interrupted");
				return null;
			}
			return grabber.get();
		}

		@Override
		public void handle() {
			grabber.set(new OpenIMAJGrabber());
		}
	}

	private static class GetDevicesTask extends WebcamTask {

		private volatile List<WebcamDevice> devices = null;
		private volatile OpenIMAJGrabber grabber = null;

		public GetDevicesTask(WebcamDriver driver) {
			super(driver, null);
		}

		/**
		 * Return camera devices.
		 *
		 * @param grabber the native grabber to use for search
		 * @return Camera devices.
		 */
		public List<WebcamDevice> getDevices(OpenIMAJGrabber grabber) {

			this.grabber = grabber;

			try {
				process();
			} catch (InterruptedException e) {
				LOG.error("Processor has been interrupted");
				return Collections.emptyList();
			}

			return devices;
		}

		@Override
		public void handle() {

			devices = new ArrayList<WebcamDevice>();

			Pointer<DeviceList> pointer = grabber.getVideoDevices();
			DeviceList list = pointer.get();

			for (Device device : list.asArrayList()) {
				devices.add(new WebcamDefaultDevice(device));
			}
		}
	}

	/**
	 * Logger.
	 */
	private static Logger LOG = LogManager.getLogger(WebcamDefaultDriver.class);

	private static OpenIMAJGrabber grabber = null;

	@Override
	public List<WebcamDevice> getDevices() {

		LOG.debug("Searching devices");

		if (grabber == null) {

			WebcamNewGrabberTask task = new WebcamNewGrabberTask(this);
			grabber = task.newGrabber();

			if (grabber == null) {
				return Collections.emptyList();
			}
		}

		List<WebcamDevice> devices = new GetDevicesTask(this).getDevices(grabber);

//		if (LOG.isDebugEnabled()) {
			for (WebcamDevice device : devices) {
				LOG.debug("Found device {}"+ device.getName());
			}
//		}

		return devices;
	}

	@Override
	public long getScanInterval() {
		return DEFAULT_SCAN_INTERVAL;
	}

	@Override
	public boolean isScanPossible() {
		return true;
	}

	@Override
	public boolean isThreadSafe() {
		return false;
	}
}
