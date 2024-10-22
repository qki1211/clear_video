package io.quantumknight.common.swing.webcam.state;
/********************************************************************************************
//* Filename: 		WebcamSingleton.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    JFC/SWING FRAMEWORK - WEBCAM BRIDJ WRAPPER - 
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
import java.util.List;

import io.quantumknight.common.swing.webcam.WebcamPanel;
import io.quantumknight.common.swing.webcam.components.Webcam;
import io.quantumknight.common.swing.webcam.components.WebcamDevice;
import io.quantumknight.common.swing.webcam.components.WebcamDriver;
import io.quantumknight.video.framework.io.LogManager;
import io.quantumknight.video.framework.io.Logger;
import io.quantumknight.video.util.TimeMarkerNano;

public class WebcamSingleton {

	private static final Logger logger = LogManager.getLogger(WebcamSingleton.class);
	
	private static WebcamSingleton _INSTANCE = new WebcamSingleton();  // Static Local Initializer - Java Singleton Pattern
	
	private Webcam activeLocalMirrorWebcam;
	private WebcamPanel activeLocalMirrorWebcamDisplayPanel;
	
	private WebcamDriver driver = null;  // Webcam driver (LtiCivil, JMF, FMJ, JQT, OpenCV, VLCj, etc).
	private volatile List<Webcam> attachedDevices;
	
	private boolean initialized = false;
	
	
	/**
	 * Private Constructor - Java Singleton Pattern
	 */
	private WebcamSingleton() {
		super();
		this.initialize();
	}
	
	/**
	 * Singleton Accessor - PUBLIC
	 * @return TransactionSingleton
	*/
	public static WebcamSingleton getInstance() {
		return _INSTANCE;
	}
	
	/***********************************************************************************************************************************/
	/*** INITIALIZATION SEQUENCE BELOW THIS LINE ***************************************************************************************/
	/***********************************************************************************************************************************/
	
	/**
	 * Initialize Singleton - Construct XOR Generator with 'N' Seeds
	 * @param seed
	 * @return void
	*/
	private void initialize() {
		
		TimeMarkerNano sw = new TimeMarkerNano();
		
		try {
			
			logger.info("LOCAL WEBCAM - INITIALIZING!");
			
			sw.setTimeMark(1);
			
			// ON STARTUP - LOAD AVAILABLE WEBCAM DRIVERS
			this.driver = Webcam.getDriver();
			if (this.driver == null) {
				throw new Exception("CANNOT ALLOCATE WEBCAM DRIVER - SERVICE FAILURE");
			}
			
			this.initialized = true;
			
			sw.setTimeMark(2);
			
			logger.info("LOCAL WEBCAM INITIALIZED - " + sw.compareTimeFormattedString(1, 2));
		}
		catch(Exception e) {
			logger.fatal("CANNOT INITIALIZE LOCAL WEBCAM: " + e.getMessage(), e.fillInStackTrace());
		}
	}
	
	/***********************************************************************************************************************************/
	/*** ACCESSOR METHODS BELOW THIS LINE **********************************************************************************************/
	/***********************************************************************************************************************************/
	
    /**
	 * DAEMON PROCESS - Allow webcam devices to be monitored externally (background thread) and updated periodically
	 * @throws Exception
	*/
	public void updateDevices() throws Exception {
		List<WebcamDevice> devices = this.getDriver().getDevices();
		List<Webcam> webcams = new ArrayList<Webcam>();
		for (WebcamDevice device : devices) {
			webcams.add(new Webcam(device));
		}
		this.setAttachedDevices(webcams);
	}
	
	/**
	 * @return the attachedDevices
	 */
	public List<Webcam> getAttachedDevices() {
		
		if ((this.attachedDevices == null) || (this.attachedDevices.size() < 1)) {
			
			try {
				this.updateDevices();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		return attachedDevices;
	}
	
	/**
	 * @param attachedDevices the attachedDevices to set
	 */
	public void setAttachedDevices(List<Webcam> attachedDevices) {
		this.attachedDevices = attachedDevices;
	}
	
	/**
	 * @return the driver
	 */
	public WebcamDriver getDriver() {
		return driver;
	}

	/**
     * @return the initialized
     */
    public boolean isInitialized() {
        return initialized;
    }

	/**
	 * @return the activeLocalMirrorWebcam
	 */
	public Webcam getActiveLocalMirrorWebcam() {
		return activeLocalMirrorWebcam;
	}

	/**
	 * @param activeLocalMirrorWebcam the activeLocalMirrorWebcam to set
	 */
	public void setActiveLocalMirrorWebcam(Webcam activeLocalMirrorWebcam) {
		this.activeLocalMirrorWebcam = activeLocalMirrorWebcam;
	}

	/**
	 * @return the activeLocalMirrorWebcamDisplayPanel
	 */
	public WebcamPanel getActiveLocalMirrorWebcamDisplayPanel() {
		return activeLocalMirrorWebcamDisplayPanel;
	}

	/**
	 * @param activeLocalMirrorWebcamDisplayPanel the activeLocalMirrorWebcamDisplayPanel to set
	 */
	public void setActiveLocalMirrorWebcamDisplayPanel(WebcamPanel activeLocalMirrorWebcamDisplayPanel) {
		this.activeLocalMirrorWebcamDisplayPanel = activeLocalMirrorWebcamDisplayPanel;
	}
}
