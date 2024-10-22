package io.quantumknight.video.framework.swing;
/********************************************************************************************
//* Filename: 		MasterJFrame.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    JFC/SWING FRAMEWORK - PRIMARY EXTERNAL CONTAINER FOR SWING COMPONENTS
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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import io.quantumknight.video.framework.constants.SwingApplicationConstants;


public class MasterJFrame extends JFrame {

	private static final long serialVersionUID = 8690822995958764149L;
	
	/**
	 * Constructor for MasterJFrame.java
	 * @param String applicationName
	 * @param int initialWidth
	 * @param int initialHeight
	*/
	public MasterJFrame(String applicationName, int initialWidth, int initialHeight) {
		super(applicationName);
		this.init(initialWidth, initialHeight);
	}
	
	/**
	 * Initialization sequence establishes window dimensions and places
	 * a JFrame in the center of the screen.  Establishes exit listener
	 * and accomodates Windows TaskBar size
	 * @param int initialWidth
	 * @param int initialHeight
	 * @return void
	*/
	private void init(int initialWidth, int initialHeight) {
	
		int height = initialHeight;
		int width = initialWidth;
		
		/** Calculate Screen Dimensions ******************************************* **/
		Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
		double screen_width = screen_size.getWidth();
		double screen_height = screen_size.getHeight();
		
		screen_height -= 30;	// Subtract 30px for Operating System Task Bar
		
		/** Center the Frame on the Screen **************************************** **/
		int x_pos = (int)((screen_width - width) / 2);
		int y_pos = (int)((screen_height - height) / 2);
	
	
		/** Adjust for 800 X 600 (or smaller) Screens ***************************** **/
		if (screen_height <= height) {
			height = (int)((screen_height - 10));
			y_pos = 5;
		}
	
		/** Apply size / location settings for JFrame ***************************** **/
		this.setSize(width, height);
		this.setLocation(x_pos,y_pos);
		this.setBackground(Color.gray);
	
		
		/** Initialize JFrame as hidden - Must be made visible explicitly by caller **/
		this.setVisible(false);
		
		
		/** Apply Global Window Exit Listener ******************************************** **/
		if (SwingApplicationConstants.USE_GLOBAL_EXIT) {
		    this.addWindowListener(new ExitListener());
	    }
	}
}