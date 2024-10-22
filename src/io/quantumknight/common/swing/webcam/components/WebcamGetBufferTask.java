package io.quantumknight.common.swing.webcam.components;
/********************************************************************************************
//* Filename: 		WebcamGetBufferTask.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    WRAPPER IMPLEMENTATION - SARXOS WEBCAM GET-BUFFER-TASK
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

import java.nio.ByteBuffer;

import io.quantumknight.common.swing.webcam.components.WebcamDevice.BufferAccess;
import io.quantumknight.video.framework.io.LogManager;
import io.quantumknight.video.framework.io.Logger;

public class WebcamGetBufferTask extends WebcamTask {

	private static Logger LOG = LogManager.getLogger(WebcamGetBufferTask.class);

	private volatile ByteBuffer buffer = null;

	public WebcamGetBufferTask(WebcamDriver driver, WebcamDevice device) {
		super(driver, device);
	}

	public ByteBuffer getBuffer() {
		try {
			process();
		} catch (InterruptedException e) {
			LOG.debug("Image buffer request interrupted", e);
			return null;
		}
		return buffer;
	}

	@Override
	public void handle() {

		WebcamDevice device = getDevice();
		if (!device.isOpen()) {
			return;
		}

		if (!(device instanceof BufferAccess)) {
			return;
		}

		buffer = ((BufferAccess) device).getImageBytes();
	}
}
