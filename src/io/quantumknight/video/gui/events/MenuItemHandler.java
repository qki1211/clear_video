package io.quantumknight.video.gui.events;
/********************************************************************************************
//* Filename: 		MenuItemHandler.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    JMenuItem Event Handler Interface
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

import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import io.quantumknight.video.action.ExitApplicationAction;
import io.quantumknight.video.action.HelpAboutAction;
import io.quantumknight.video.action.PreferencesAction;
import io.quantumknight.video.action.StartVideoAction;
import io.quantumknight.video.action.StopVideoAction;
import io.quantumknight.video.constants.ConstantsElements;
import io.quantumknight.video.constants.ConstantsVideoApplication;
import io.quantumknight.video.framework.interfaces.MenuItemHandlerInterface;
import io.quantumknight.video.framework.io.LogManager;
import io.quantumknight.video.framework.io.Logger;


public class MenuItemHandler implements MenuItemHandlerInterface {
	
	private static final Logger log = LogManager.getLogger(MenuItemHandler.class);
	
	/**
	 * MAIN ITEM SELECTED HANDLING METHOD *************************************
	 * @param JButton button
	 * @param String name
	 * @param String cmd
	 * @return void
	*/
	public void doHandle(JMenuItem item, String name, String cmd) {
		
		/** FILE MENU - START VIDEO FEED **/
		if (name.equalsIgnoreCase(ConstantsElements.BUTTON_LABELS[0])) {
			final Object[] input = { name }; // Run all actions off EDT
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new StartVideoAction(input);
				}
			});
		}
		/** FILE - STOP VIDEO FEED **/
		else if (name.equalsIgnoreCase(ConstantsElements.BUTTON_LABELS[1])) {
			final Object[] input = { name }; // Run all actions off EDT
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new StopVideoAction(input);
				}
			});
		}
	    /** FILE - EXIT PROGRAM **/
	    else if (name.equalsIgnoreCase(ConstantsElements.BUTTON_LABELS[3])) {
	    	final Object[] input = { name }; // Run all actions off EDT
	        SwingUtilities.invokeLater(new Runnable() {
	        	public void run() {
	        		new ExitApplicationAction(input);
	        	}
	        });
	    }
	    /** EDIT - PREFERENCES - MODAL DIALOGUE **/
	    else if (name.equalsIgnoreCase(ConstantsElements.BUTTON_LABELS[4])) {
	    	final Object[] input = { name }; // Run all actions off EDT
	        SwingUtilities.invokeLater(new Runnable() {
	        	public void run() {
	        		new PreferencesAction(input);
	        	}
	        });
	    }
	    /** HELP - ABOUT - MODAL DIALOGUE **/
	    else if (name.equalsIgnoreCase(ConstantsElements.BUTTON_LABELS[2])) {
	    	final Object[] input = { name }; // Run all actions off EDT
	        SwingUtilities.invokeLater(new Runnable() {
	        	public void run() {
	        		new HelpAboutAction(input);
	        	}
	        });
	    }
	    else {
	        if (ConstantsVideoApplication._DEBUG_EVENT_LISTENERS) { 
	        	log.error("JMenuItem Selection not handled - system error");
	        }
	    }
	}
}
