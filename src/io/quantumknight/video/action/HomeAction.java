package io.quantumknight.video.action;
/********************************************************************************************
//* Filename: 		HomeAction.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    ACTION - Home Screen - Primary Actions
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

import io.quantumknight.video.constants.ConstantsApplicationGlobalUI;
import io.quantumknight.video.constants.ConstantsElements;
import io.quantumknight.video.constants.ConstantsVideoApplication;
import io.quantumknight.video.framework.io.LogManager;
import io.quantumknight.video.framework.io.Logger;
import io.quantumknight.video.framework.swing.GenericSwingAction;
import io.quantumknight.video.framework.swing.SwingApplicationRuntime;
import io.quantumknight.video.gui.screens.HomeScreen;

public class HomeAction extends GenericSwingAction {
	
	private static final Logger log = LogManager.getLogger(HomeAction.class);
	
	/**
	 * Constructor Triggers Action to Fire!
	 * @param inputs
	*/
	public HomeAction(Object[] inputs) {
		super(inputs, false, true);
	}
	
	/**
	 * Primary Execute Method!
	 * @param Object[] inputs
	 * @return boolean
	*/
	protected boolean execute(Object[] inputs) throws Exception {

		if (ConstantsVideoApplication._DEBUG_ACTION_MVC) { 
			log.debug("HomeAction - ENTERING execute()");
		}
		
	    String cmd = (String)inputs[0];
	    
	    if (cmd.equalsIgnoreCase(ConstantsElements.BUTTON_LABELS[4])) {
	        // Button = "Home" - render main screen
	        this.setup();
	    }
	    else {
	        if (ConstantsVideoApplication._DEBUG_ACTION_MVC) { 
	        	log.error("HomeAction - Command Not Recognized! - " + cmd);
	        }
	        this.setup(); // Default to render main screen in any action error condition
	    }
		
		if (ConstantsVideoApplication._DEBUG_ACTION_MVC) { 
			log.debug("HomeAction - EXITING execute()");
		}
		
		return true;
	}

	/**
	 * Setup Screen Display
	 * @param Object[] inputs
	 * @return void
	 * @throws Exception 
	*/
	private void setup() throws Exception {
		
		if (ConstantsVideoApplication._DEBUG_ACTION_MVC) { 
			log.debug("HomeAction - ENTERING setup()");
		}
		
		//	STEP 1)	ACQUIRE SCREEN
		HomeScreen screen = (HomeScreen)SwingApplicationRuntime.getSystemJPanelScreensByName(ConstantsElements.SYSTEM_SCREENS[1][0]);
		screen.setVisible(true);
		
		
		// START WITH VIDEO OFF
		
		// RESET ALL LABELS AS INACTIVE
		
		
		//	STEP 4) REFRESH CONTAINER WITH THIS SCREEN - (Simulates Card Layout)
		super.displayScreenWithToolbar(screen, ConstantsApplicationGlobalUI.USE_APPLET);
		
		if (ConstantsVideoApplication._DEBUG_ACTION_MVC) { 
			log.debug("HomeAction - EXITING setup()");
		}
	}
}
