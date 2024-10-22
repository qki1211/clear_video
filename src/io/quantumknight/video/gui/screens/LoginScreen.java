package io.quantumknight.video.gui.screens;
/********************************************************************************************
//* Filename: 		LoginScreen.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    JFC/SWING - Application Login Screen
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

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import io.quantumknight.video.constants.ConstantsApplicationGlobalUI;
import io.quantumknight.video.constants.ConstantsElements;
import io.quantumknight.video.constants.ConstantsEventRegistry;
import io.quantumknight.video.framework.constants.ConstantsColor;
import io.quantumknight.video.framework.layout.TableLayout;
import io.quantumknight.video.framework.swing.CommonScreen;
import io.quantumknight.video.framework.swing.SwingApplicationInit;
import io.quantumknight.video.framework.swing.SwingApplicationRuntime;
import io.quantumknight.video.framework.swing.TesterJFrame;


public class LoginScreen extends CommonScreen {
	
	private static final long serialVersionUID = 2821430392729624533L;
	
	private String screenName = ConstantsElements.SYSTEM_SCREENS[0][0];


	/**
	 * Constructor
	*/
	public LoginScreen() throws Exception {
	    super();
	    initializeGUI();
	}

	/**
	 * Initialize sub panels
	 * @return void
	*/  
	public void initializeGUI() throws Exception {
	    
	    // STEP 1) SETUP INFO.CLEARTHOUGHT LAYOUT MANAGER (TABULAR FORMAT)
	    double size[][] = { { TableLayout.FILL }, // COLUMNS Horizontal Width
							{ TableLayout.FILL } // ROWS Vertical Height
	    };
	    this.setLayout(new TableLayout(size));
	    this.setBackground(ConstantsColor.COLOR_CYAN_COLOR);
		
	    JPanel orange = new JPanel();
	    orange.setBackground(Color.ORANGE);
	    
	    
	    // ADD MAJOR COMPONENTS TO LAYOUT MANAGER
	    JScrollPane scrollPanel = new JScrollPane(orange);
	    scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(scrollPanel,"1,1");
	}

	/**
	 * Refresh screen with real data
	 * @param Object valueObject
	 * @return void
	*/
	public void refreshScreen(Object... valueObjects) throws Exception {
	    this.setVisible(true);
	    this.repaint();
	    this.validate();
	}

	/**
	 * Reset all fields in screen
	 * @return void
	*/
	public void resetAllFields() {
		// CONTROL OVER SPECIFIC FIELDS IN SCREEN
	}

	/**
	 * Disables all fields in screen
	 * @return void
	*/
	public void setDisabledAll() {
		// CONTROL OVER SPECIFIC FIELDS IN SCREEN
	}

	/**
	 * Enables all fields in screen
	 * @return void
	*/
	public void setEnabledAll() {
		// CONTROL OVER SPECIFIC FIELDS IN SCREEN
	}

	/**
	 * Disables fields by name
	 * @param String fieldName
	 * @return void
	*/
	public void setDisabled(String fieldName) {
		// CONTROL OVER SPECIFIC FIELDS IN SCREEN
	}

	/**
	 * Enables fields by name
	 * @param String fieldName
	 * @return void
	*/
	public void setEnabled(String fieldName) {
		// CONTROL OVER SPECIFIC FIELDS IN SCREEN
	}

	/**
	 * Get field by name
	 * @param String fieldName
	 * @return Object
	*/
	public Object getField(String fieldName) {
	    return SwingApplicationRuntime.getJComponentByName(this.screenName + "|" + fieldName);
	}

	/**
	 * Set field by name
	 * @param String fieldName
	 * @param Object fieldObject
	 * @return void
	*/
	public void setField(String fieldName, Object fieldObject) {
		// NO IMPLEMENTATION
	}

	/**
	 * Self Executable Test of This Component - Requires BogusFrame.java
	*/
	public static void main(String args[]) {
	    try {
	    
	    	/**
	    	 * Atomic GUI UNIT TEST - 
	    	*/
	    	LoginScreen thisPanel = new LoginScreen(); // Initialize this screen!
	    	
	    	SwingApplicationRuntime.loadApplicationImages();  // LOAD SERIALIZED IMAGES IN JAR FILE
	    	SwingApplicationInit init = new SwingApplicationInit();  // INITIALIZE IN-MEMORY CACHE ELEMENTS
	        init.initScreens(
	        		ConstantsApplicationGlobalUI.APPLICAION_NAME, 
	        		ConstantsElements.SYSTEM_SCREENS, 
	        		ConstantsApplicationGlobalUI.INITIAL_WINDOW_WIDTH, 
	        		ConstantsApplicationGlobalUI.INITIAL_WINDOW_HEIGHT, 
	        		ConstantsApplicationGlobalUI.USE_SPLASH_SCREEN);
	        
	        init.initEventHandling(ConstantsEventRegistry.EVENT_HANDLERS);
	        
	        TesterJFrame frame = new TesterJFrame(thisPanel.getClass().getName());
	    	JScrollPane scrollPane = new JScrollPane(thisPanel);
	    	frame.setContentPane(scrollPane);
	    	frame.pack();
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	        System.exit(1);
	    }
	}
}
