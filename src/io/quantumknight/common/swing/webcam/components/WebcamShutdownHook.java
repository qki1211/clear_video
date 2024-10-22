package io.quantumknight.common.swing.webcam.components;
/********************************************************************************************
//* Filename: 		WebcamShutdownHook.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    WRAPPER IMPLEMENTATION - SARXOS WEBCAM SHUTDOWN HOOK
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

import io.quantumknight.video.framework.io.LogManager;
import io.quantumknight.video.framework.io.Logger;

/**
 * Shutdown hook to be executed when JVM exits gracefully. This class intention
 * is to be used internally only.
 * 
 * @author Bartosz Firyn (sarxos)
 */
public final class WebcamShutdownHook extends Thread {

	/**
	 * Logger.
	 */
	private static Logger LOG = LogManager.getLogger(WebcamShutdownHook.class);

	/**
	 * Number of shutdown hook instance.
	 */
	private static int number = 0;

	/**
	 * Webcam instance to be disposed / closed.
	 */
	private Webcam webcam = null;

	/**
	 * Create new shutdown hook instance.
	 * 
	 * @param webcam the webcam for which hook is intended
	 */
	protected WebcamShutdownHook(Webcam webcam) {
		super("shutdown-hook-" + (++number));
		this.webcam = webcam;
		this.setUncaughtExceptionHandler(WebcamExceptionHandler.getInstance());
	}

	@Override
	public void run() {
		LOG.info("Automatic {} deallocation"+ webcam.getName());
		webcam.dispose();
	}
}
