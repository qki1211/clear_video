package io.quantumknight.video.framework.swing;
/********************************************************************************************
//* Filename: 		TesterJFrame.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    JFC/SWING FRAMEWORK - JFRAME USED FOR SCREENS TO SELF TEST / UNIT TEST
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


public class TesterJFrame extends JFrame {

	private static final long serialVersionUID = -6908657575332966953L;
	
	/**
	 * Constructor for TesterJFrame.java
	 * @param String screenName
	*/
	public TesterJFrame(String screenName) {
		super(screenName);
		this.init();
	}
	
	/**
	 * Initialization sequence establishes window dimensions and places
	 * a JFrame in the center of the screen.  Establishes exit listener
	 * and accommodates Windows TaskBar size
	 * @return void
	*/
	private void init() {
	
		int height = SwingApplicationConstants.TESTER_WINDOW_HEIGHT;
		int width = SwingApplicationConstants.TESTER_WINDOW_WIDTH;
		
		/** Calculate Screen Dimensions ******************************************* **/
		Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
		double screen_width = screen_size.getWidth();
		double screen_height = screen_size.getHeight();
		
		screen_height -= 30;	// subtract 30px for windows Task Bar
		
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
		this.setVisible(true);
		this.pack();
		
		/** Apply Window Exit Listener ******************************************** **/
		this.addWindowListener(new ExitListener());
	}
}