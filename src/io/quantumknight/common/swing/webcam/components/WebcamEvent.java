package io.quantumknight.common.swing.webcam.components;
/********************************************************************************************
//* Filename: 		WebcamEvent.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    WRAPPER IMPLEMENTATION - SARXOS WEBCAM EVENT
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

import java.awt.image.BufferedImage;
import java.util.EventObject;


/**
 * Webcam event.
 * 
 * @author Bartosz Firyn (SarXos)
 */
public class WebcamEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	/**
	 * Image acquired from webcam
	 */
	private BufferedImage image = null;

	/**
	 * Event type.
	 */
	private WebcamEventType type = null;

	/**
	 * Webcam event.
	 * 
	 * @param type the event type
	 * @param w the webcam object
	 */
	public WebcamEvent(WebcamEventType type, Webcam w) {
		this(type, w, null);
	}

	/**
	 * Webcam event.
	 * 
	 * @param type the event type
	 * @param w the webcam object
	 * @param image the image acquired from webcam
	 */
	public WebcamEvent(WebcamEventType type, Webcam w, BufferedImage image) {
		super(w);
		this.type = type;
		this.image = image;
	}

	@Override
	public Webcam getSource() {
		return (Webcam) super.getSource();
	}

	/**
	 * Return image acquired by webcam. This method will return not-null object
	 * <b>only</b> in case new image acquisition event. For all other events, it
	 * will simply return null.
	 * 
	 * @return Acquired image
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * Return event type.
	 * 
	 * @return Event type
	 * @see WebcamEventType
	 */
	public WebcamEventType getType() {
		return type;
	}
}
