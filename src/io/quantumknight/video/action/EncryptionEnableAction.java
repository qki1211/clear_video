package io.quantumknight.video.action;
/********************************************************************************************
//* Filename: 		EncryptionEnableAction.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    ACTION - Activate Encryption
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

import javax.swing.JOptionPane;

import io.quantumknight.video.constants.ConstantsElements;
import io.quantumknight.video.constants.ConstantsVideoApplication;
import io.quantumknight.video.framework.io.LogManager;
import io.quantumknight.video.framework.io.Logger;
import io.quantumknight.video.framework.swing.GenericSwingAction;
import io.quantumknight.video.framework.swing.SwingApplicationRuntime;
import io.quantumknight.video.gui.screens.HomeScreen;
import io.quantumknight.video.state.VideoDemoSettings;

public class EncryptionEnableAction extends GenericSwingAction {
	
	private static final Logger log = LogManager.getLogger(EncryptionEnableAction.class);
	
	/**
	 * Constructor Triggers Action to Fire!
	 * @param inputs
	*/
	public EncryptionEnableAction(Object[] inputs) {
		super(inputs, false, true);
	}
	
	/**
	 * Primary Execute Method!
	 * @param Object[] inputs
	 * @return boolean
	*/
	protected boolean execute(Object[] inputs) throws Exception {

		if (ConstantsVideoApplication._DEBUG_ACTION_MVC) { 
			log.debug("EncryptionEnableAction - ENTERING execute()");
		}
		
	    String cmd = (String)inputs[0];
	    
	    if (cmd.equalsIgnoreCase(ConstantsElements.BUTTON_LABELS[8])) {
	    	 this.enable();
	    }
	    else {
	        if (ConstantsVideoApplication._DEBUG_ACTION_MVC) { 
	        	log.error("EncryptionEnableAction - Command Not Recognized! - " + cmd);
	        }
	    }
		
		if (ConstantsVideoApplication._DEBUG_ACTION_MVC) { 
			log.debug("EncryptionEnableAction - EXITING execute()");
		}
		
		return true;
	}
	
	/**
	 * Display / Render Modal Dialogue
	 * @throws Exception
	*/
	private void enable() throws Exception {
		
		log.debug("EncryptionEnableAction - ENTERING enable()");
		
		HomeScreen screen = (HomeScreen)SwingApplicationRuntime.getSystemJPanelScreensByName(ConstantsElements.SYSTEM_SCREENS[1][0]);
		
		// ACTIVATE LOCAL WEBCAM IF AVAILABLE
		VideoDemoSettings xv = VideoDemoSettings.getInstance();
		
		if (xv.isVideoStarted()) {
			
			if (!xv.isEncryptionActivated()) {
				
				xv.setEncryptionActivated(true);
			}
			else {
				JOptionPane.showMessageDialog(screen, "Encryption is already active", "Encryption Already Active", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			JOptionPane.showMessageDialog(screen, "Please start the video feed before enabling encryption", "Video Feed Offline", JOptionPane.ERROR_MESSAGE);
		}
		
		log.debug("EncryptionEnableAction - EXITING enable()");
	}
}
