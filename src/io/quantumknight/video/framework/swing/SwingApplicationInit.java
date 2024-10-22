package io.quantumknight.video.framework.swing;
/********************************************************************************************
//* Filename: 		SwingApplicationInit.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    JFC/SWING FRAMEWORK - COMMON INIT SEQUENCE FOR ALL JFC/SWING APPLICATIONS
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

import javax.swing.JApplet;


public class SwingApplicationInit {

	/**
	 * Generic Initialization Sequence for Registering Custom Event Handlers
	 * @param String[] classNames
	 * @return void
	 * @throws Exception
	*/
	public void initEventHandling(String[] classNames) throws Exception {
	    
	    // Single point of Initialization for this Event Handling Singleton
	    MasterEventRouter router = MasterEventRouter.getInstance();
	    for (int i=0; i<classNames.length; i++) {
	        router.registerEventHandler(Class.forName(classNames[i]).newInstance());
	    }
	}
	
	/**
	 * Generic Initialization Sequence for Swing Programs
	 * @param String applicationName
	 * @param Stringp[][] applicationScreens
	 * @param int initialWidth
	 * @param int initialHeight
	 * @return void
	 * @throws Exception
	*/
	public void initScreens(String applicationName, String[][] applicationScreens, int initialWidth, int initialHeight, boolean useSplash) throws Exception {
		initScreens(applicationName, applicationScreens, initialWidth, initialHeight, useSplash, false);
	}
	
	/**
	 * Generic Initialization Sequence for Swing Programs
	 * @param String applicationName
	 * @param Stringp[][] applicationScreens
	 * @param int initialWidth
	 * @param int initialHeight
	 * @return void
	 * @throws Exception
	*/
	@SuppressWarnings("unused")
	public void initScreens(String applicationName, String[][] applicationScreens, int initialWidth, int initialHeight, boolean useSplash, boolean loadAudio) throws Exception {
		
		
	    //	STEP 2) LOAD SPLASH SCREEN AND GRAPHICS INTO MEMORY
	    SwingApplicationRuntime.initMasterJFrame(applicationName, initialWidth, initialHeight);
		SplashScreen splashScreen;
	    if (useSplash) {
	        SwingApplicationRuntime.loadSplashImages();
	        splashScreen = new SplashScreen();
	    }
	
		//	STEP 3) LOAD SYSTEM GRAPHICS, SETTINGS, AND ALL SYSTEM SCREENS INTO MEMORY
		SwingApplicationRuntime.loadApplicationImages();
		SwingApplicationRuntime.loadApplicationScreens(applicationScreens);
		if (loadAudio) {
			SwingApplicationRuntime.loadApplicationAudio();
		}
		
		//	STEP 4) PAUSE FOR COMPLETION
		if (useSplash) {
	        try { Thread.sleep(3000); } catch (InterruptedException ioe) {}
	    	SwingApplicationRuntime.setAllComponentsLoadedOK(true);	//	Splash Screen will query this for completion
	    	try { Thread.sleep(750); } catch (InterruptedException ioe) {}
	    }
		
		
		//	STEP 5) CLEAN-UP RESOURCES
	    splashScreen = null;
		SwingApplicationRuntime.setGraphicsSplashScreen(null);	//	purge Splash Screen from memory!
		System.gc();
	}
	
	/**
	 * Generic Initialization Sequence for Swing Programs
	 * @param String applicationName
	 * @param Stringp[][] applicationScreens
	 * @param int initialWidth
	 * @param int initialHeight
	 * @param JApplet container
	 * @return void
	 * @throws Exception
	*/
	public void initAppletScreens(String applicationName, String[][] applicationScreens, int initialWidth, int initialHeight, boolean useSplash, JApplet container) throws Exception {
		
		
	    //	STEP 1) LOAD SPLASH SCREEN AND GRAPHICS INTO MEMORY
	    SwingApplicationRuntime.initMasterApplet(container);
	    
	    
	    // STEP 2) CONDITIONALLY LAUNCH SPLASH SCREEN - CONSTRUCTOR KEEPS SPLASH THREAD OPEN
	    @SuppressWarnings("unused")
		SplashScreen splashScreen;
	    if (useSplash) {
	        SwingApplicationRuntime.loadSplashImages();
	        splashScreen = new SplashScreen();
	    }
	    
	    
		//	STEP 3) LOAD SYSTEM GRAPHICS, SETTINGS, AND ALL SYSTEM SCREENS INTO MEMORY
		SwingApplicationRuntime.loadApplicationImages();
		SwingApplicationRuntime.loadApplicationScreens(applicationScreens);
		
		
		
		//	STEP 4) PAUSE FOR COMPLETION
		if (useSplash) {
	        try { Thread.sleep(3000); } catch (InterruptedException ioe) {}
	    	SwingApplicationRuntime.setAllComponentsLoadedOK(true);	//	Splash Screen will query this for completion
	    	try { Thread.sleep(750); } catch (InterruptedException ioe) {}
	    }
		
		
		//	STEP 5) CLEAN-UP RESOURCES
	    splashScreen = null;
		SwingApplicationRuntime.setGraphicsSplashScreen(null);	//	purge Splash Screen from memory!
		System.gc();
		
		
		
		
		
		//	STEP 6) SETUP APPLET INIT PARAMETERS
		
		/** Calculate Screen Dimensions ******************************************* **/
		Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
		@SuppressWarnings("unused")
		double screen_width = screen_size.getWidth();
		double screen_height = screen_size.getHeight();
		
	//	screen_height -= 30;	// subtract 30px for windows Task Bar
		
		/** Center the Frame on the Screen **************************************** **/
	//	int x_pos = (int)((screen_width - initialWidth) / 2);
	//	int y_pos = (int)((screen_height - initialHeight) / 2);
	
		
	
		/** Adjust for 800 X 600 (or smaller) Screens ***************************** **/
		if (screen_height <= initialHeight) {
			initialHeight = (int)((screen_height - 10));
	//		y_pos = 5;
		}
	
		int x_pos = 0;  // allow HTML to dictate applet size.  FIX TO TOP-LEFT
		int y_pos = 0;	// allow HTML to dictate applet size.  FIX TO TOP-LEFT
		
		/** Apply size / location settings for JFrame ***************************** **/
		container.setSize(initialWidth, initialHeight);
		container.setLocation(x_pos,y_pos);
		container.setBackground(Color.gray);
	
		
		/** Initialize JFrame as hidden - Must be made visible explicitly by caller **/
		container.setVisible(false);
	
	}
}
