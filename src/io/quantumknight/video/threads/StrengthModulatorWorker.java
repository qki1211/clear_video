package io.quantumknight.video.threads;
/********************************************************************************************
//* Filename: 		StrengthModulatorWorker.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    WORKER THREAD (UN-SAFE) - Update / refresh waveform setting
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
import javax.swing.JSlider;
import javax.swing.JTextField;

import io.quantumknight.video.constants.ConstantsEncryptionStrength;
import io.quantumknight.video.constants.ConstantsElements;
import io.quantumknight.video.framework.constants.TimeDateConstants;
import io.quantumknight.video.framework.swing.SwingApplicationRuntime;
import io.quantumknight.video.gui.screens.HomeScreen;
import io.quantumknight.video.state.VideoDemoSettings;

public class StrengthModulatorWorker implements Runnable {
	
	private Thread thread; // holding thread for forced interrupt
	
	private boolean alive = false;
	
	/**
	 * MAIN EXECUTION BLOCK 
	*/
	public void run() {
		
		System.out.println("WORKER THREAD STARTING - WAVE-FORM UPDATE");
		
		this.alive = true;
		
		
		// GET REFERENCE TO SWING PANELS SO THAT IT CAN BE REPAINTED IN A CONTINUOUS UPDATE LOOP
		VideoDemoSettings xv = VideoDemoSettings.getInstance();
		HomeScreen screen = (HomeScreen)SwingApplicationRuntime.getSystemJPanelScreensByName(ConstantsElements.SYSTEM_SCREENS[1][0]);  // SCREEN REFERENCE FOR UPDATING TIMERS

		JSlider encryptionStrength = (JSlider)screen.getField(ConstantsElements.SCREEN_FIELDS[12]);
		JTextField waveFreqTextField = (JTextField)screen.getField(ConstantsElements.SCREEN_FIELDS[13]);
		
		float previousDelayTimeSec = 0.0f;
		
		boolean movingUpward = false;
		
		while (this.alive) {
			
			try {
				
				
				// GET THE WAVE-FORM INTERVAL SETTING IN THE SCREEN
				// ------------------------------------------------------------------------
				
				String value = waveFreqTextField.getText();
				float delayTimeSec = 1.5f;  // EVERY 1.5 SECONDS
				if (value != null) {
					try {
						delayTimeSec = Float.parseFloat(value);
					}
					catch(NumberFormatException nfe) { }
				}
				
				// UPPER / LOWER-BOUND SAFEGUARDS
				// ------------------------------------------------------------------------
				if (delayTimeSec < 0.1f) {
					JOptionPane.showMessageDialog(screen, "This demo prevents WaveForm from changing in less than 100 Milliseconds", "Minimum 100 MS", JOptionPane.ERROR_MESSAGE);
					delayTimeSec = .1f;
				}
				if (delayTimeSec > 120f) {
					JOptionPane.showMessageDialog(screen, "This demo prevents WaveForm from exceeding 2 minutes / cycle", "Maximum 2 Minutes", JOptionPane.ERROR_MESSAGE);
					delayTimeSec = 120f;
				}
				
				
				// UPDATE TEXT FIELD AND SET VALUE
				if (delayTimeSec != previousDelayTimeSec) {
					waveFreqTextField.setText("" + delayTimeSec);
					waveFreqTextField.repaint();
				}
				previousDelayTimeSec = delayTimeSec;
				
				
				
				// WAIT FOR THE NUMBER OF SECONDS IN THE FORM FIELD
				// ------------------------------------------------------------------------
				try {
					Thread.sleep((long)(TimeDateConstants._ONE_SECOND * delayTimeSec));
				}
				catch(InterruptedException ioe) {
					kill9();
					break;
				}
				
				// PROGRAMMATICALLY UPDATE THE JSLIDER SETTING TO MODULATE WAVEFORM
				int currentSliderValue = encryptionStrength.getValue();
				
				if (currentSliderValue == ConstantsEncryptionStrength.getStrengthsCount()) {
					movingUpward = false;
				}
				else if (currentSliderValue == 1) {
					movingUpward = true;
				}
				
				
				// INCREMENT OR DECREMENT ACCORDING TO DIRECTION
				if (movingUpward) {
					currentSliderValue++;
				}
				else {
					currentSliderValue--;
				}
				
				encryptionStrength.setValue(currentSliderValue);
				encryptionStrength.repaint();
				
			}
			catch(Exception ex) {
				ex.printStackTrace();
				kill9();
			}
		}
		
		// PURGE CYCLICAL CALLBACK REFERENCE
		xv.setModulatorThread(null);
		
		System.out.println("WORKER THREAD TERMINATED.");
	}
	
	
	/**
	 * @param thread the thread to set
	 */
	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public void kill9() {
		this.thread.interrupt();
		this.alive = false;
	}
}
