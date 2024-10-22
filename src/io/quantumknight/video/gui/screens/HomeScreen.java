package io.quantumknight.video.gui.screens;
/********************************************************************************************
//* Filename: 		HomeScreen.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    JFC/SWING - Application Home Screen
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

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import io.quantumknight.common.swing.webcam.VideoPanel;
import io.quantumknight.video.constants.ConstantsApplicationGlobalUI;
import io.quantumknight.video.constants.ConstantsElements;
import io.quantumknight.video.constants.ConstantsEventRegistry;
import io.quantumknight.video.framework.constants.ConstantsColor;
import io.quantumknight.video.framework.layout.TableLayout;
import io.quantumknight.video.framework.layout.TableLayoutConstraints;
import io.quantumknight.video.framework.swing.CommonScreen;
import io.quantumknight.video.framework.swing.SwingApplicationInit;
import io.quantumknight.video.framework.swing.SwingApplicationRuntime;
import io.quantumknight.video.framework.swing.TesterJFrame;
import io.quantumknight.video.gui.panels.HeaderPanel;
import io.quantumknight.video.gui.panels.ScrollableMainPanel;


public class HomeScreen extends CommonScreen {

	private static final long serialVersionUID = 807901002176882437L;
	
	private String screenName = ConstantsElements.SYSTEM_SCREENS[1][0];
	
	private JScrollPane scrollPane;
    
	/**
	 * Constructor
	*/
	public HomeScreen() throws Exception {
	    super();
	    initializeGUI();
	}
	
	/**
	 * Initialize sub panels
	 * @return void
	*/  
	public void initializeGUI() throws Exception {
	    
	    // SETUP INFO.CLEARTHOUGHT LAYOUT MANAGER (TABULAR FORMAT)
    	// Note: Default Panel Size is (800 X 600)
    	double size[][] = { { 	10,						// [0] LEFT MARGIN - CYAN BACKGROUND
    							TableLayout.FILL, 		// [1] MAIN DISPLAY AREA (SCALABLE)
    							10 }, 					// [2] RIGHT MARGIN - CYAN BACKGROUND
    							
    						{ 	60, 					// [0] FIXED HEADER AREA - STATIC HEIGHT
    							TableLayout.FILL, 		// [1] MAIN CONTENT AREA (SCALABLE / SCROLLABLE)
    							10 } 					// [2] BOTTOM MARGIN
    	};
	    this.setLayout(new TableLayout(size));
	    this.setBackground(ConstantsColor.COLOR_E0E0E0);
		
	    // CONSTRUCT MAJOR COMPONENTS
	    HeaderPanel header = new HeaderPanel(this.screenName);
	    
	    
	    // ADD STATIC BORDERS AND HEADER
	    JPanel[] borders = new JPanel[3];
	    for (int i = 0; i < borders.length; i++) {
	    	borders[i] = new JPanel();
	    	borders[i].setBackground(ConstantsColor.COLOR_ADMIN_CYAN_COLOR);
	    }
	    
		this.add(header, 				new TableLayoutConstraints(0, 0, 2, 0, TableLayout.FULL, TableLayout.FULL));
		this.add(borders[0], 			new TableLayoutConstraints(0, 0, 0, 2, TableLayout.FULL, TableLayout.FULL));
		this.add(borders[1], 			new TableLayoutConstraints(2, 0, 2, 2, TableLayout.FULL, TableLayout.FULL));
		this.add(borders[2], 			new TableLayoutConstraints(0, 2, 2, 2, TableLayout.FULL, TableLayout.FULL));
		
		// ADD DYNAMIC CONTENT
		this.addScrollPaneToView();
		
	}

	/**
	 * ATOMIC SUBROUTINE - ADD SCROLLPANE TO VIEW
	 * @param Object valueObject
	 * @throws Exception
	*/
	private void addScrollPaneToView(Object... valueObjects) throws Exception { 
		
		VideoPanel videoPanel = null;
		
		if ((valueObjects != null) && (valueObjects.length == 1)) {
			if (valueObjects[0] instanceof VideoPanel) {
				videoPanel = (VideoPanel)valueObjects[0];
			}
		}
		
		ScrollableMainPanel smp = new ScrollableMainPanel(this.screenName, videoPanel);
		
		this.scrollPane = new JScrollPane(smp);
		this.scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    
		SwingApplicationRuntime.setJComponentByName(this.screenName + "|" + ConstantsElements.SCREEN_FIELDS[1], this.scrollPane);
		
		this.add(this.scrollPane, new TableLayoutConstraints(1, 1, 1, 1, TableLayout.FULL, TableLayout.FULL));
	}
	
	/**
	 * Refresh screen with real data
	 * @param Object valueObject
	 * @return void
	*/
	public void refreshScreen(Object... valueObjects) throws Exception {
		
		// REMOVE OLD SCROLLPANE
		this.remove(this.scrollPane);
		
		// ADD NEW SCROLLPANE
		this.addScrollPaneToView(valueObjects);
		
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
	    	HomeScreen thisPanel = new HomeScreen(); // Initialize this screen!
	    	
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

