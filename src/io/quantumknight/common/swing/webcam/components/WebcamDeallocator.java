package io.quantumknight.common.swing.webcam.components;
/********************************************************************************************
//* Filename: 		WebcamDeallocator.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    WRAPPER IMPLEMENTATION - SARXOS WEBCAM DE-ALLOCATOR
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;


/**
 * Deallocator which goal is to release all devices resources when SIGTERM
 * signal is detected.
 * 
 * @author Bartosz Firyn (SarXos)
 */
final class WebcamDeallocator {

	private static final WebcamSignalHandler HANDLER = new WebcamSignalHandler();

	private final Webcam[] webcams;

	/**
	 * This constructor is used internally to create new deallocator for the
	 * given devices array.
	 * 
	 * @param devices the devices to be stored in deallocator
	 */
	private WebcamDeallocator(Webcam[] devices) {
		this.webcams = devices;
	}

	/**
	 * Store devices to be deallocated when TERM signal has been received.
	 * 
	 * @param webcams the webcams array to be stored in deallocator
	 */
	protected static void store(Webcam[] webcams) {
		if (HANDLER.get() == null) {
			HANDLER.set(new WebcamDeallocator(webcams));
		} else {
			throw new IllegalStateException("Deallocator is already set!");
		}
	}

	protected static void unstore() {
		HANDLER.reset();
	}

	protected void deallocate() {
		for (Webcam w : webcams) {
			try {
				w.dispose();
			} catch (Throwable t) {
				caugh(t);
			}
		}
	}

	private void caugh(Throwable t) {
		File f = new File(String.format("webcam-capture-hs-%s", System.currentTimeMillis()));
		PrintStream ps = null;
		try {
			t.printStackTrace(ps = new PrintStream(f));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				ps.close();
			}
		}
	}

}
