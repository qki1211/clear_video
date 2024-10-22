package io.quantumknight.common.swing.webcam.components;
/********************************************************************************************
//* Filename: 		WebcamSignalHandler.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    WRAPPER IMPLEMENTATION - SARXOS WEBCAM SIGNAL HANDLER
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
import sun.misc.Signal;
import sun.misc.SignalHandler;


/**
 * Primitive signal handler. This class is using undocumented classes from
 * sun.misc.* and therefore should be used with caution.
 * 
 * @author Bartosz Firyn (SarXos)
 */
final class WebcamSignalHandler implements SignalHandler {

	private static Logger LOG = LogManager.getLogger(WebcamSignalHandler.class);

	private WebcamDeallocator deallocator = null;

	private SignalHandler handler = null;

	public WebcamSignalHandler() {
		handler = Signal.handle(new Signal("TERM"), this);
	}

	@Override
	public void handle(Signal signal) {

		LOG.warn("Detected signal {} {}, calling deallocator"+ signal.getName()+ signal.getNumber());

		// do nothing on "signal default" or "signal ignore"
		if (handler == SIG_DFL || handler == SIG_IGN) {
			return;
		}

		try {
			deallocator.deallocate();
		} finally {
			handler.handle(signal);
		}
	}

	public void set(WebcamDeallocator deallocator) {
		this.deallocator = deallocator;
	}

	public WebcamDeallocator get() {
		return this.deallocator;
	}

	public void reset() {
		this.deallocator = null;
	}
}
