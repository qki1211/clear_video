package io.quantumknight.common.swing.webcam.components;
/********************************************************************************************
//* Filename: 		WebcamDiscoveryEvent.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    WRAPPER IMPLEMENTATION - SARXOS WEBCAM DISCOVERY EVENT
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

import java.util.EventObject;


/**
 * This event is generated when webcam has been found or lost.
 * 
 * @author Bartosz Firyn (sarxos)
 */
public class WebcamDiscoveryEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	/**
	 * Event type informing about newly connected webcam.
	 */
	public static final int ADDED = 1;

	/**
	 * Event type informing about lately disconnected webcam.
	 */
	public static final int REMOVED = 2;

	/**
	 * Event type (webcam connected / disconnected).
	 */
	private int type = -1;

	/**
	 * Create new webcam discovery event.
	 * 
	 * @param webcam the webcam which has been found or removed
	 * @param type the event type
	 * @see #ADDED
	 * @see #REMOVED
	 */
	public WebcamDiscoveryEvent(Webcam webcam, int type) {
		super(webcam);
		this.type = type;
	}

	/**
	 * Return the webcam which has been found or removed.
	 * 
	 * @return Webcam instance
	 */
	public Webcam getWebcam() {
		return (Webcam) getSource();
	}

	/**
	 * Return event type (webcam connected / disconnected)
	 * 
	 * @return Integer value
	 * @see #ADDED
	 * @see #REMOVED
	 */
	public int getType() {
		return type;
	}
}
