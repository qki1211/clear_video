package io.quantumknight.video.threads;
/********************************************************************************************
//* Filename: 		VideoPanelUpdateWorker.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    WORKER THREAD (UN-SAFE) - Runnable Object - Repaints JPanel w/ video data
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

import java.awt.image.BufferedImage;

import com.clear.cryptosystem.CLEAR;
import com.clear.cryptosystem.CLEARMode;
import com.clear.cryptosystem.CLEARResult;
import com.clear.cryptosystem.CLEARStream;

import io.quantumknight.common.swing.webcam.VideoPanel;
import io.quantumknight.video.business.FetchPixelDataBO;
import io.quantumknight.video.business.ParsePixelDataBO;
import io.quantumknight.video.constants.ConstantsElements;
import io.quantumknight.video.framework.swing.SwingApplicationRuntime;
import io.quantumknight.video.gui.panels.ControlPanel;
import io.quantumknight.video.gui.screens.HomeScreen;
import io.quantumknight.video.state.VideoDemoSettings;

public class VideoPanelUpdateWorker implements Runnable {
	
	private boolean alive = false;
	
	/**
	 * MAIN EXECUTION BLOCK 
	*/
	public void run() {
		
		System.out.println("WORKER THREAD STARTING - UPDATE VIDEO PANEL");
		
		this.alive = true;
		
		// INITIALIZE CLEAR STREAM INTERFACE
		CLEARStream crypto = null;
		try {
			crypto = CLEAR.clearStream();
		}
		catch(Exception ex) {
			System.err.println("COULD NOT INITIALIZE CLEAR");
			this.alive = false;
		}
		
		// GET REFERENCE TO VIDEO PANEL SO THAT IT CAN BE REPAINTED IN A CONTINUOUS UPDATE LOOP
		VideoDemoSettings settings = VideoDemoSettings.getInstance();
		VideoPanel videoPanel = settings.getMainVideoDisplayPanel();
		HomeScreen screen = (HomeScreen)SwingApplicationRuntime.getSystemJPanelScreensByName(ConstantsElements.SYSTEM_SCREENS[1][0]);  // SCREEN REFERENCE FOR UPDATING TIMERS
		ControlPanel controlPanel = (ControlPanel)screen.getField(ConstantsElements.SCREEN_FIELDS[2]);
		
		int exceptionCount = 0;
		
		
		
		while (this.alive) {
			
			try {
				
				
				/** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **/
				/** 																															  **/
				/** 								FETCH THE NEXT VIDEO IMAGE / PIXEL DATA (RAW) 												  **/
				/** 																															  **/
				/** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **/
				
				
				// WHILE THE CAMERA IS ON - GRAB EACH FRAME OF RAW PIXEL DATA HERE AS A BYTE ARRAY
				byte[] rawframe = FetchPixelDataBO.getNextPixelData();
				
				long encryptionTime = 0L;
				long decryptionTime = 0L;
				long encryptionJob  = settings.getEncryptingJobId();
				long decryptionJob  = settings.getDecryptingJobId();
				
				
				if (settings.isEncryptionActivated()) {
					
					
					/** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **/
					/** 																															  **/
					/** 												ENCRYPT THE FRAME 															  **/
					/** 																															  **/
					/** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **/
					
					// STOPWATCH!   START
					long encryptStart = System.currentTimeMillis(); 
					
					// ENCRYPT WITH CLEAR CRYPTO-SYSTEM
					CLEARResult encryptResult = crypto.encryptContinue(   // ENCRYPT THE FRAME
												rawframe, 
												encryptionJob, 
												CLEARMode.StreamingEncryptionSysGenKeyMode.STREAMING_SYSTEM_GEN_KEY_HEADERLESS, 
												false
					);
					
					rawframe = encryptResult.getCipherText();	// ENCRYPTED RESULT
					
					// STOPWATCH!   STOP!   -->  COMPUTE THE TIME IT TAKES TO ENCRYPT A FRAME  ( NOW  -  MINUS  - START )
					encryptionTime = System.currentTimeMillis() - encryptStart;
					
					
					/** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **/
					/** 																															  **/
					/** 												DECRYPT THE FRAME 															  **/
					/** 																															  **/
					/** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **/
					
					if (!settings.isBypassDecryption()) {   // CONDITIONALLY DO NOT DECRYPT - SHOW THE USER WHAT ENCRYPTION LOOKS LIKE
						
						
						// STOPWATCH!   START
						long decryptStart = System.currentTimeMillis();
						
						// DECRYPT WITH CLEAR CRYPTO-SYSTEM
						CLEARResult decryptResult = crypto.decryptContinue(  // DECRYPT THE FRAME
													rawframe, 
													decryptionJob, 
													CLEARMode.StreamingDecryptionMode.STREAMING_KEY_HEADERLESS, 
													false
						);
						
						rawframe = decryptResult.getClearText();  // DECRYPTED RESULT
						
						// STOPWATCH!   STOP!   -->  COMPUTE THE TIME IT TAKES TO DECRYPT A FRAME  ( NOW  -  MINUS  - START )
						decryptionTime = System.currentTimeMillis() - decryptStart;
					}
					
					/** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **/
				}
				
				
				// UPDATE UI WITH TIMING
				settings.setEncryptionTime(encryptionTime);
				settings.setDecryptionTime(decryptionTime);
				controlPanel.refreshScreen();
				
				
				/** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **/
				/** 																															  **/
				/** 										DISPLAY IMAGE INTO THE MAIN VIDEO PANEL 											  **/
				/** 																															  **/
				/** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **/
				
				
				// CONVERT RAW BINARY TO JAVA BUFFERED IMAGE OBJECT FOR DISPLAY INTO A JFRAME PANEL
				BufferedImage image = ParsePixelDataBO.convert(rawframe);
				
				// RENDER DISPLAY INTO PANEL
				if (videoPanel != null) {
					videoPanel.refreshScreen(image);
				}
				else {
					break;
				}
			}
			catch(Exception ex) {
				if (exceptionCount > 10) {
					ex.printStackTrace();
					kill9();
				}
			}
		}
		
		System.out.println("VIDEO PLAYER THREAD TERMINATED.");
		
		// PURGE CYCLICAL CALLBACK REFERENCE
		settings.setVidepPanelRefreshThread(null);
	}
	
	public void kill9() {
		this.alive = false;
	}
}
