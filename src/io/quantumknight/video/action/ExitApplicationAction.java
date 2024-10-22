package io.quantumknight.video.action;
/********************************************************************************************
//* Filename: 		ExitApplicationAction.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    ACTION - Terminate Application
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

import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import io.quantumknight.video.constants.ConstantsElements;
import io.quantumknight.video.constants.ConstantsVideoApplication;
import io.quantumknight.video.framework.io.LogManager;
import io.quantumknight.video.framework.io.Logger;
import io.quantumknight.video.framework.swing.GenericSwingAction;
import io.quantumknight.video.framework.swing.MasterJFrame;
import io.quantumknight.video.framework.swing.SwingApplicationRuntime;


public class ExitApplicationAction extends GenericSwingAction {
	
	private static final Logger log = LogManager.getLogger(ExitApplicationAction.class);
	
	/**
	 * Constructor Triggers Action to Fire!
	 * @param inputs
	*/
	public ExitApplicationAction(Object[] inputs) {
		super(inputs, false, true);
	}
	
	/**
	 * Primary Execute Method!
	 * @param Object[] inputs
	 * @return boolean
	*/
	protected boolean execute(Object[] inputs) throws Exception {

		if (ConstantsVideoApplication._DEBUG_ACTION_MVC) { 
			log.debug("ExitApplicationAction - ENTERING execute()");
		}
		
	    String cmd = (String)inputs[0];
	    
		if (cmd.equalsIgnoreCase(ConstantsElements.BUTTON_LABELS[3])) {
			this.doExit(inputs);
		}
	    else {
	        if (ConstantsVideoApplication._DEBUG_ACTION_MVC) {
	        	log.error("ExitApplicationAction - Command Not Recognized! - " + cmd);
	        }
	    }
		
		if (ConstantsVideoApplication._DEBUG_ACTION_MVC) { 
			log.debug("ExitApplicationAction - EXITING execute()");
		}
		
		return true;
	}
	
	/**
	 * Prompt for Shutdown / Exit System
	 * @param inputs
	 * @throws Exception
	*/
	private void doExit(Object[] inputs) throws Exception {
		
		log.debug("ExitApplicationAction - ENTERING - doExit()");
		
		// KILL VIDEO FEED IF ITS RUNNING
		StopVideoAction.stopFeed(true);
		
		
		// FIRE COMMON FRAMEWORK CLOSING EVENT
		MasterJFrame masterFrame = SwingApplicationRuntime.getMasterFrame();
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(
			    new WindowEvent(masterFrame, WindowEvent.WINDOW_CLOSING));
		
		log.debug("ExitApplicationAction - EXITING - doExit()");
	}
}
