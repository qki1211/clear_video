package io.quantumknight.video.framework.swing;
/********************************************************************************************
//* Filename: 		SplashScreen.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    JFC/SWING FRAMEWORK - SPLASH SCREEN - WHILE PROGRAM LOADS
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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

import io.quantumknight.video.framework.constants.SwingApplicationConstants;

public class SplashScreen extends JWindow {
	
	private static final long serialVersionUID = -5322427300590026944L;
	
	/**
	 * Constructor: Takes (Image, Frame, WaitTime)
	*/
	public SplashScreen() throws Exception {
		super(SwingApplicationRuntime.getMasterFrame());
		this.init();
	}
	
	/**
	 * Initialize and display this Splash Screen!
	 * @return void
	 * @throws Exception
	*/
	@SuppressWarnings("rawtypes")
	private void init() throws Exception {
	    
		ImageIcon icon = null;
		HashMap graphicsSplashScreen = SwingApplicationRuntime.getGraphicsSplashScreen();
		if (graphicsSplashScreen != null) {	    
		    //	HashMap object contains Swing ImageIcon Mapping >> 
		    //  Example: [ String() | ImageIcon() ]
		    icon = (ImageIcon)graphicsSplashScreen.get(SwingApplicationConstants.IMGKEY_SPLASH_SCREEN);
		}
		else {
		    throw new Exception("Cannot Load Splash Screen: Graphics Not Found!");
		}
		
		
		JLabel pic = new JLabel(icon);
		getContentPane().add(pic,BorderLayout.CENTER);
		this.pack();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension picSize = pic.getPreferredSize();
		setLocation(screenSize.width / 2 - (picSize.width / 2),
					screenSize.height / 2 - (picSize.height / 2));
		
		/** Inner Class **/
		final Runnable closerRunner = new Runnable() {
			public void run() {
				setVisible(false);
				dispose();
			}
		};
		
		/** Inner Class 2 **/
		Runnable waitRunner = new Runnable() {
			public void run() {
				
				try {
					while (true) {
						boolean loaded = SwingApplicationRuntime.isAllComponentsLoadedOK();
						if (loaded) {
							SwingUtilities.invokeAndWait(closerRunner);
							break;
						}
						else {
							try {
								Thread.sleep(1000);
							}
							catch (InterruptedException ioe) {}
						}
					}
				}
				catch (Exception e) {}
			}
		};
		
		this.setVisible(true);
		Thread splashThread = new Thread(waitRunner, "Splash Screen Thread");
		splashThread.start();
	}
}
