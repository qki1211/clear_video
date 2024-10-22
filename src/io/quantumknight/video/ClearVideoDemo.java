package io.quantumknight.video;
/********************************************************************************************
//* Filename: 		ClearVideoDemo.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    Encrypted Video Demo Application
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
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import io.quantumknight.video.action.HomeAction;
import io.quantumknight.video.constants.ConstantsApplicationGlobalUI;
import io.quantumknight.video.constants.ConstantsElements;
import io.quantumknight.video.constants.ConstantsEventRegistry;
import io.quantumknight.video.constants.ConstantsSystemGraphics;
import io.quantumknight.video.framework.io.LogManager;
import io.quantumknight.video.framework.io.Logger;
import io.quantumknight.video.framework.swing.ImageTile;
import io.quantumknight.video.framework.swing.MasterJFrame;
import io.quantumknight.video.framework.swing.SwingApplicationInit;
import io.quantumknight.video.framework.swing.SwingApplicationRuntime;
import io.quantumknight.video.gui.elements.XVIDMenubar;
import io.quantumknight.video.gui.elements.XVIDToolbar;
import io.quantumknight.video.gui.panels.XVIDGlassPane;
import io.quantumknight.video.state.GlobalInitializationSingleton;


public class ClearVideoDemo {
	
	private static final Logger log = LogManager.getLogger(ClearVideoDemo.class);
	
	/**
	 * Application Initialization Sequence 
	 * @throws Exception
	*/
	private void start() throws Exception {
		
		
		//	STEP 1) SET SWING LOOK & FEEL TO NATIVE ENVIRONMENT / OS
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    	
    	
        //	STEP 2)	Initialize Java Swing Application Common Libraries && Framework Components
	    SwingApplicationInit init = new SwingApplicationInit();  // INITIALIZE IN-MEMORY CACHE ELEMENTS
        init.initScreens(	ConstantsApplicationGlobalUI.APPLICAION_NAME, 
			        		ConstantsElements.SYSTEM_SCREENS, 
			        		ConstantsApplicationGlobalUI.INITIAL_WINDOW_WIDTH, 
			        		ConstantsApplicationGlobalUI.INITIAL_WINDOW_HEIGHT, 
			        		ConstantsApplicationGlobalUI.USE_SPLASH_SCREEN,
			        		ConstantsApplicationGlobalUI.LOAD_SYSTEM_AUDIO);
			        		
        init.initEventHandling(ConstantsEventRegistry.EVENT_HANDLERS);
        
        
        //	STEP 3)	MANUAL CONFIGURATIONS TO JFRAME
        MasterJFrame masterFrame = SwingApplicationRuntime.getMasterFrame();
        masterFrame.setResizable(true);
        masterFrame.setExtendedState(masterFrame.getExtendedState()|MasterJFrame.MAXIMIZED_BOTH); // Start Maximized!
        masterFrame.setVisible(true);
        
        
        //  STEP 4)  SET PROGRAM ICON
        ImageTile iceIcon = new ImageTile((ImageIcon)SwingApplicationRuntime.getGraphicFromApplicationSystem(ConstantsSystemGraphics.CLEAR_VIDEO_SystemGraphics.ICON_ICE_PROGRAM_ICON.name()));
        masterFrame.setIconImage(iceIcon.getIcon().getImage());
        taskBarIcon(iceIcon.getIcon().getImage());
        
        
        //  STEP 5) INITIALIZE GLASS PANE
        XVIDGlassPane glass = new XVIDGlassPane();
        masterFrame.setGlassPane(glass);
        glass.setVisible(false);
        
        
        //	STEP 6)	INSTANTIATE MENU SYSTEM
        XVIDMenubar menuBar = new XVIDMenubar();
        XVIDToolbar toolBar = new XVIDToolbar();
        masterFrame.setJMenuBar(menuBar);
        masterFrame.getContentPane().add(toolBar, BorderLayout.NORTH);
        

        //  STEP 7) KICK-OFF GLOBAL INITIALIZATION SEQUENCE
        GlobalInitializationSingleton.getInstance();
        
        
        //	STEP 8) LAUNCH PRELIMINARY ACTION CLASS
        final Object[] input = { ConstantsElements.BUTTON_LABELS[4] }; // Run all actions off EDT
        SwingUtilities.invokeLater(new Runnable() {
        	public void run() {
        		new HomeAction(input);
        	}
        });
		
		log.info("CLEAR VIDEO DEMO SYSTEM STARTED");
	}
	
	/**
	 * SET IceCube Icon for Application - Deal with BACKWARD/FORWARD compatibility breaking changes at Java9
	 * @param image
	 * @return void
	*/
	private static void taskBarIcon(Image image) { 
		
        try {

            // Use reflection to check for the existence of Taskbar class (Java 9+)
            try {
                Class<?> taskbarClass = Class.forName("java.awt.Taskbar");
                Object taskbar = taskbarClass.getMethod("getTaskbar").invoke(null);
                taskbarClass.getMethod("setIconImage", Image.class).invoke(taskbar, image);
            }
            catch (ClassNotFoundException e) {
                // Taskbar class doesn't exist, we're on Java 8
                try {
                    Class<?> applicationClass = Class.forName("com.apple.eawt.Application");
                    Object application = applicationClass.getMethod("getApplication").invoke(null);
                    applicationClass.getMethod("setDockIconImage", Image.class).invoke(application, image);
                }
                catch (ClassNotFoundException ex) {
                    // Not on macOS or not running a version with com.apple.eawt.Application
                }
            }
        }
        catch (Exception e) {
            // unable to set icon image
        }
	}
	
	/**
	 * MAIN EXECUTION BLOCK
	 * @param args
	*/
	public static void main(String[] args) {
		try {
			ClearVideoDemo xcl = new ClearVideoDemo();
			xcl.start();
		}
		catch(Exception e) {
			log.error("Critical Failure!  ABORTING PROGRAM: " + e.getMessage());
	        e.printStackTrace();
	        System.exit(1);
		}
	}
}
