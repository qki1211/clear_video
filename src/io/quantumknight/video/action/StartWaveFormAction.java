package io.quantumknight.video.action;
/********************************************************************************************
//* Filename: 		StartWaveFormAction.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    ACTION - Enable Wave-Form Mode
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
import io.quantumknight.video.threads.StrengthModulatorWorker;

public class StartWaveFormAction extends GenericSwingAction {
	
	private static final Logger log = LogManager.getLogger(StartWaveFormAction.class);
	
	/**
	 * Constructor Triggers Action to Fire!
	 * @param inputs
	*/
	public StartWaveFormAction(Object[] inputs) {
		super(inputs, false, true);
	}
	
	/**
	 * Primary Execute Method!
	 * @param Object[] inputs
	 * @return boolean
	*/
	protected boolean execute(Object[] inputs) throws Exception {

		if (ConstantsVideoApplication._DEBUG_ACTION_MVC) { 
			log.debug("StartWaveFormAction - ENTERING execute()");
		}
		
	    String cmd = (String)inputs[0];
	    
	    if (cmd.equalsIgnoreCase(ConstantsElements.BUTTON_LABELS[12])) {
	    	 this.start();
	    }
	    else {
	        if (ConstantsVideoApplication._DEBUG_ACTION_MVC) { 
	        	log.error("StartWaveFormAction - Command Not Recognized! - " + cmd);
	        }
	    }
		
		if (ConstantsVideoApplication._DEBUG_ACTION_MVC) { 
			log.debug("StartWaveFormAction - EXITING execute()");
		}
		
		return true;
	}
	
	/**
	 * Start Wave Form
	 * @throws Exception
	*/
	private void start() throws Exception {
		
		log.debug("StartWaveFormAction - ENTERING start()");
		
		HomeScreen screen = (HomeScreen)SwingApplicationRuntime.getSystemJPanelScreensByName(ConstantsElements.SYSTEM_SCREENS[1][0]);
		
		// ACTIVATE LOCAL WEBCAM IF AVAILABLE
		VideoDemoSettings xv = VideoDemoSettings.getInstance();
		
		if (xv.isVideoStarted()) {
			
			if (xv.isEncryptionActivated()) {
				
				
				if (xv.getModulatorThread() == null) {
					
					// ACTIVATE WAVEFORM UPDATE THREAD - CHANGES ENCRYPTION STRENGTH DYNAMICALLY ACCORDING TO MODULATION INTERVAL
					StrengthModulatorWorker runnable = new StrengthModulatorWorker();
					Thread thread = new Thread(runnable);
					thread.setDaemon(false);
					thread.setPriority(Thread.NORM_PRIORITY);
					thread.setName("WAVEFORM");
					runnable.setThread(thread);
					thread.start();
					
					xv.setModulatorThread(runnable);
					
				}
				else {
					JOptionPane.showMessageDialog(screen, "Wave-Form mode already active", "Already Activated", JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(screen, "Please start encryption prior to engaging Wave-Form Mode", "Encryption Inactive", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			JOptionPane.showMessageDialog(screen, "Please start the video feed before changing encryption settings", "Video Feed Offline", JOptionPane.ERROR_MESSAGE);
		}
		
		log.debug("StartWaveFormAction - EXITING start()");
	}
}
