package io.quantumknight.video.action;
/********************************************************************************************
//* Filename: 		PreferencesAction.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    ACTION - Preferences - Controls modal dialogue
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

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import io.quantumknight.video.constants.ConstantsApplicationGlobalUI;
import io.quantumknight.video.constants.ConstantsElements;
import io.quantumknight.video.constants.ConstantsVideoApplication;
import io.quantumknight.video.framework.io.LogManager;
import io.quantumknight.video.framework.io.Logger;
import io.quantumknight.video.framework.swing.GenericSwingAction;
import io.quantumknight.video.framework.swing.SwingApplicationRuntime;
import io.quantumknight.video.gui.dialogues.PreferencesDialogue;
import io.quantumknight.video.gui.screens.HomeScreen;


public class PreferencesAction extends GenericSwingAction {
	
	private static final Logger log = LogManager.getLogger(PreferencesAction.class);
	
	/**
	 * Constructor Triggers Action to Fire!
	 * @param inputs
	*/
	public PreferencesAction(Object[] inputs) {
		super(inputs, false, true);
	}
	
	/**
	 * Primary Execute Method!
	 * @param Object[] inputs
	 * @return boolean
	*/
	protected boolean execute(Object[] inputs) throws Exception {

		if (ConstantsVideoApplication._DEBUG_ACTION_MVC) { 
			log.debug("PreferencesAction - ENTERING execute()");
		}
		
	    String cmd = (String)inputs[0];
	    
	    if (cmd.equalsIgnoreCase(ConstantsElements.BUTTON_LABELS[4])) {
	        // Button = "Display Preferences" - render preferences dialogue
	        this.displayPreferencesDialogue();
	    }
	    else if (cmd.equalsIgnoreCase(ConstantsElements.BUTTON_LABELS[5])) {
	        // Button = "OK" - Save User Preferences & Dispose Dialogue
	        this.ok();
	    }
	    else if (cmd.equalsIgnoreCase(ConstantsElements.BUTTON_LABELS[6])) {
	        // Button = "Cancel" - Dispose Dialogue
	        this.cancel();
	    }
	    else {
	        if (ConstantsVideoApplication._DEBUG_ACTION_MVC) { 
	        		log.error("PreferencesAction - Command Not Recognized! - " + cmd);
	        }
	    }
		
		if (ConstantsVideoApplication._DEBUG_ACTION_MVC) { 
			log.debug("PreferencesAction - EXITING execute()");
		}
		
		return true;
	}
	
	/**
	 * Display Modal Preferences Dialogue
	 * @throws Exception
	*/
	private void displayPreferencesDialogue() throws Exception {
		
		log.debug("PreferencesAction - ENTERING displayPreferencesDialogue()");
		
		JFrame masterFrame = (JFrame)super.getMasterFrame(ConstantsApplicationGlobalUI.USE_APPLET);
		PreferencesDialogue pd = new PreferencesDialogue(masterFrame);
		pd.setVisible(true);
		
		log.debug("PreferencesAction - EXITING displayPreferencesDialogue()");
	}
	
	/**
	 * Close / Dispose of Modal Preferences Dialogue + SAVE settings
	 * @throws Exception
	*/
	@SuppressWarnings("unchecked")
	private void ok() throws Exception {
		
		log.debug("PreferencesAction - ENTERING ok()");
		
		JFrame masterFrame = (JFrame)super.getMasterFrame(ConstantsApplicationGlobalUI.USE_APPLET);
		
		HomeScreen screen = (HomeScreen)SwingApplicationRuntime.getSystemJPanelScreensByName(ConstantsElements.SYSTEM_SCREENS[1][0]);
		PreferencesDialogue pd = (PreferencesDialogue)screen.getField(ConstantsElements.SCREEN_FIELDS[5]);
		
		JComboBox<String> cb = (JComboBox<String>)screen.getField(ConstantsElements.SCREEN_FIELDS[28]);
		String selected = (String)cb.getSelectedItem();
		
		// VALIDATE SELECTED ITEM
		boolean valid = true;
		
		StringBuffer errMsg = new StringBuffer();
		if (selected == null) {
			errMsg.append("Please select your preferred local IP Address");
			valid = false;
		}
		
		
		if (valid) {
			
			// CLOSE / DISPOSE DIALOGUE
			if (pd != null) {
				pd.setVisible(false);
				pd.dispose();
				pd = null;
			}
			
			// NULLIFY THE REGISTRY OF THE ELEMENT
			SwingApplicationRuntime.setJComponentByName(ConstantsElements.SYSTEM_SCREENS[1][0] + "|" + ConstantsElements.SCREEN_FIELDS[5], null);
	    	screen.validate();
	    	screen.repaint();
	    	
	    	masterFrame.validate();
	    	masterFrame.repaint();
	    	
			
			//  REFRESH SCREEN
			super.displayScreenWithToolbar(screen, ConstantsApplicationGlobalUI.USE_APPLET);
			
		}
		else {
			JOptionPane.showMessageDialog(masterFrame, errMsg.toString(), "Add Contact", JOptionPane.ERROR_MESSAGE);
		}
		
		
		log.debug("PreferencesAction - EXITING ok()");
	}
	
	/**
	 * Close / Dispose of Modal Preferences Dialogue without saving
	 * @throws Exception
	*/
	private void cancel() throws Exception {
		
		log.debug("PreferencesAction - ENTERING cancel()");
		
		HomeScreen screen = (HomeScreen)SwingApplicationRuntime.getSystemJPanelScreensByName(ConstantsElements.SYSTEM_SCREENS[1][0]);
		PreferencesDialogue pd = (PreferencesDialogue)screen.getField(ConstantsElements.SCREEN_FIELDS[5]);
		
		// CLOSE / DISPOSE DIALOGUE
		if (pd != null) {
			pd.setVisible(false);
			pd.dispose();
			pd = null;
		}
		
		// NULLIFY THE REGISTRY OF THE ELEMENT
		SwingApplicationRuntime.setJComponentByName(ConstantsElements.SYSTEM_SCREENS[1][0] + "|" + ConstantsElements.SCREEN_FIELDS[5], null);
		
		log.debug("PreferencesAction - EXITING cancel()");
	}
}
