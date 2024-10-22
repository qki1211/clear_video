package io.quantumknight.video.gui.events;
/********************************************************************************************
//* Filename: 		ButtonHandler.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    JButton & JRadio-Button Event Handler Interface
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

import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

import io.quantumknight.video.action.HelpAboutAction;
import io.quantumknight.video.action.PreferencesAction;
import io.quantumknight.video.action.StartVideoAction;
import io.quantumknight.video.action.StopVideoAction;
import io.quantumknight.video.constants.ConstantsElements;
import io.quantumknight.video.constants.ConstantsVideoApplication;
import io.quantumknight.video.framework.interfaces.ButtonHandlerInterface;
import io.quantumknight.video.framework.io.LogManager;
import io.quantumknight.video.framework.io.Logger;


public class ButtonHandler implements ButtonHandlerInterface {
	
	private static final Logger log = LogManager.getLogger(ButtonHandler.class);
	
	/**
	 * MAIN BUTTON-PRESS HANDLING METHOD *************************************
	 * @param JButton button
	 * @param String name
	 * @param String cmd
	 * @return void
	*/
	public void doHandle(JButton button, String name, String cmd) {
		
		/** TOOLBAR ICON - START VIDEO FEED **/
		if (name.equalsIgnoreCase(ConstantsElements.BUTTON_LABELS[0])) {
			final Object[] input = { name }; // Run all actions off EDT
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new StartVideoAction(input);
				}
			});
		}
		/** TOOLBAR ICON - STOP VIDEO FEED **/
		else if (name.equalsIgnoreCase(ConstantsElements.BUTTON_LABELS[1])) {
			final Object[] input = { name }; // Run all actions off EDT
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new StopVideoAction(input);
				}
			});
		}
		/** SAVE PREFERENCES [OK] BUTTON -  **/
		else if (name.equalsIgnoreCase(ConstantsElements.BUTTON_LABELS[5]) && cmd.equalsIgnoreCase(ConstantsElements.SCREEN_FIELDS[26])) {
			final Object[] input = { name }; // Run all actions off EDT
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new PreferencesAction(input);
				}
			});
		}
		/** CLOSE PREFERENCES [CANCEL] BUTTON -  **/
		else if (name.equalsIgnoreCase(ConstantsElements.BUTTON_LABELS[6]) && cmd.equalsIgnoreCase(ConstantsElements.SCREEN_FIELDS[27])) {
			final Object[] input = { name }; // Run all actions off EDT
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new PreferencesAction(input);
				}
			});
		}
		/** CLOSE HELP/ABOUT DIALOGUE [CLOSE] BUTTON -  **/
		else if (name.equalsIgnoreCase(ConstantsElements.BUTTON_LABELS[7]) && cmd.equalsIgnoreCase(ConstantsElements.SCREEN_FIELDS[21])) {
			final Object[] input = { name }; // Run all actions off EDT
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new HelpAboutAction(input);
				}
			});
		}
		else {
	        if (ConstantsVideoApplication._DEBUG_EVENT_LISTENERS) { 
	        	log.error("JButton Press not handled - system error");
	        }
	    }
	}

	/**
	 * MAIN JTOGGLE-BUTTON-PRESS HANDLING METHOD *****************************
	 * @param JToggleButton button
	 * @param String name
	 * @param String cmd
	 * @return void
	*/
	public void doHandle(JToggleButton button, String name, String cmd) {

		// JTOGGLE NOT IMPLEMENTED
		if (ConstantsVideoApplication._DEBUG_EVENT_LISTENERS) { 
        	log.debug("JToggleButton Press not handled - system error");
        }
	}
}
