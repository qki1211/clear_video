package io.quantumknight.video.action;
/********************************************************************************************
//* Filename: 		HelpAboutAction.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    ACTION - About the Application Information - Controls modal dialogue
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

import javax.swing.JFrame;

import io.quantumknight.video.constants.ConstantsApplicationGlobalUI;
import io.quantumknight.video.constants.ConstantsElements;
import io.quantumknight.video.constants.ConstantsVideoApplication;
import io.quantumknight.video.framework.io.LogManager;
import io.quantumknight.video.framework.io.Logger;
import io.quantumknight.video.framework.swing.GenericSwingAction;
import io.quantumknight.video.framework.swing.SwingApplicationRuntime;
import io.quantumknight.video.gui.dialogues.AboutDialogue;
import io.quantumknight.video.gui.screens.HomeScreen;


public class HelpAboutAction extends GenericSwingAction {
	
	private static final Logger log = LogManager.getLogger(HelpAboutAction.class);
	
	/**
	 * Constructor Triggers Action to Fire!
	 * @param inputs
	*/
	public HelpAboutAction(Object[] inputs) {
		super(inputs, false, true);
	}
	
	/**
	 * Primary Execute Method!
	 * @param Object[] inputs
	 * @return boolean
	*/
	protected boolean execute(Object[] inputs) throws Exception {

		if (ConstantsVideoApplication._DEBUG_ACTION_MVC) { 
			log.debug("HelpAboutAction - ENTERING execute()");
		}
		
	    String cmd = (String)inputs[0];
	    
	    if (cmd.equalsIgnoreCase(ConstantsElements.BUTTON_LABELS[2])) {
	    		// Button = "Display About" - render about dialogue
	        this.displayAboutDialogue();
	    }
	    else if (cmd.equalsIgnoreCase(ConstantsElements.BUTTON_LABELS[7])) {
    			// Button = "Close" - Dispose Dialogue
	    		this.close();
	    }
	    else {
	        if (ConstantsVideoApplication._DEBUG_ACTION_MVC) { 
	        		log.error("HelpAboutAction - Command Not Recognized! - " + cmd);
	        }
	    }
		
		if (ConstantsVideoApplication._DEBUG_ACTION_MVC) { 
			log.debug("HelpAboutAction - EXITING execute()");
		}
		
		return true;
	}

	/**
	 * Display Modal About Dialogue
	 * @throws Exception
	*/
	private void displayAboutDialogue() throws Exception {
		
		log.debug("HelpAboutAction - ENTERING displayAboutDialogue()");
		
		JFrame masterFrame = (JFrame)super.getMasterFrame(ConstantsApplicationGlobalUI.USE_APPLET);
		AboutDialogue ad = new AboutDialogue(masterFrame);
		ad.setVisible(true);
		
		log.debug("HelpAboutAction - EXITING displayAboutDialogue()");
	}
	
	/**
	 * Close / Dispose of Modal About Dialogue
	 * @throws Exception
	*/
	private void close() throws Exception {
		
		log.debug("HelpAboutAction - ENTERING close()");
		
		HomeScreen screen = (HomeScreen)SwingApplicationRuntime.getSystemJPanelScreensByName(ConstantsElements.SYSTEM_SCREENS[1][0]);
		AboutDialogue ad = (AboutDialogue)screen.getField(ConstantsElements.SCREEN_FIELDS[4]);
		
		// CLOSE / DISPOSE DIALOGUE
		if (ad != null) {
			ad.setVisible(false);
			ad.dispose();
			ad = null;
		}
		
		// NULLIFY THE REGISTRY OF THE ELEMENT
		SwingApplicationRuntime.setJComponentByName(ConstantsElements.SYSTEM_SCREENS[1][0] + "|" + ConstantsElements.SCREEN_FIELDS[4], null);

		
		log.debug("HelpAboutAction - EXITING close()");
	}
}
