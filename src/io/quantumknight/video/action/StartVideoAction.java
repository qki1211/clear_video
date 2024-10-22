package io.quantumknight.video.action;
/********************************************************************************************
//* Filename: 		StartVideoAction.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    ACTION - Open web-cam and start video playback
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

import com.github.sarxos.webcam.Webcam;

import io.quantumknight.common.swing.webcam.VideoPanel;
import io.quantumknight.video.constants.ConstantsElements;
import io.quantumknight.video.constants.ConstantsVideoApplication;
import io.quantumknight.video.framework.io.LogManager;
import io.quantumknight.video.framework.io.Logger;
import io.quantumknight.video.framework.swing.GenericSwingAction;
import io.quantumknight.video.framework.swing.OS;
import io.quantumknight.video.framework.swing.SwingApplicationRuntime;
import io.quantumknight.video.gui.screens.HomeScreen;
import io.quantumknight.video.state.VideoDemoSettings;
import io.quantumknight.video.threads.VideoPanelUpdateWorker;

public class StartVideoAction extends GenericSwingAction {
	
	private static final Logger log = LogManager.getLogger(StartVideoAction.class);
	
	/**
	 * Constructor Triggers Action to Fire!
	 * @param inputs
	*/
	public StartVideoAction(Object[] inputs) {
		super(inputs, false, true);
	}
	
	/**
	 * Primary Execute Method!
	 * @param Object[] inputs
	 * @return boolean
	*/
	protected boolean execute(Object[] inputs) throws Exception {

		if (ConstantsVideoApplication._DEBUG_ACTION_MVC) { 
			log.debug("StartVideoAction - ENTERING execute()");
		}
		
	    String cmd = (String)inputs[0];
	    
	    if (cmd.equalsIgnoreCase(ConstantsElements.BUTTON_LABELS[0])) {
	    	 this.startFeed();
	    }
	    else {
	        if (ConstantsVideoApplication._DEBUG_ACTION_MVC) { 
	        	log.error("StartVideoAction - Command Not Recognized! - " + cmd);
	        }
	    }
		
		if (ConstantsVideoApplication._DEBUG_ACTION_MVC) { 
			log.debug("StartVideoAction - EXITING execute()");
		}
		
		return true;
	}
	
	/**
	 * Display / Render Modal Dialogue
	 * @throws Exception
	*/
	private void startFeed() throws Exception {
		
		log.debug("StartVideoAction - ENTERING startFeed()");
		
		
		HomeScreen screen = (HomeScreen)SwingApplicationRuntime.getSystemJPanelScreensByName(ConstantsElements.SYSTEM_SCREENS[1][0]);
		
		if (!OS.isMacOS()) {
			
			// ACTIVATE LOCAL WEBCAM IF AVAILABLE
			VideoDemoSettings xv = VideoDemoSettings.getInstance();
			
			if (!xv.isVideoStarted()) {
				
				// CONNECT TO WEBCAM
				Webcam cam = xv.getActiveLocalMirrorWebcam();
				if (cam != null) {
					
					// EXISTING CAM - MIGHT ALREADY BE RUNNING
					if (!cam.isOpen()) {
						cam.open();
					}
				}
				else {
					
					// INITIALIZE NEW WEBCAM
					Webcam webcam = Webcam.getDefault();
					
					if (webcam != null) {
						webcam.setCustomViewSizes(ConstantsVideoApplication._VIDEO_RESOLUTION);
						webcam.setViewSize(ConstantsVideoApplication._VIDEO_RESOLUTION);
						webcam.open();
						xv.setActiveLocalMirrorWebcam(webcam);
					}
				}
				
				// STORE PANEL IN APPLICATION INSTANCE SINGLETON
				VideoPanel videoPanel = new VideoPanel(screen.getName());
				xv.setMainVideoDisplayPanel(videoPanel);
				screen.refreshScreen(videoPanel);
				
				// ACTIVATE PANEL REFRESH THREAD - PUSHES PIXEL DATA FROM CAMERA TO SWING JPANEL
				VideoPanelUpdateWorker runnable = new VideoPanelUpdateWorker();
				Thread thread = new Thread(runnable);
				thread.setDaemon(false);
				thread.setPriority(Thread.MAX_PRIORITY);
				thread.setName("PIXEL GRABBER");
				thread.start();
				
				xv.setVidepPanelRefreshThread(runnable);
				
				
				// SET BLOCKING LOGIC TO PREVENT DUPLICATE STARTS
				xv.setVideoStarted(true);
			}
			else {
				JOptionPane.showMessageDialog(screen, "Video feed already started", "Already Started", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			JOptionPane.showMessageDialog(screen, "MacOS has a permissions issue with Sarxos Webcam driver after OSX Catalina\n\nPlease update Sarxos Driver or use Windows or Linux system instead!", "Unable to start video feed", JOptionPane.ERROR_MESSAGE);
		}
		
		log.debug("StartVideoAction - EXITING startFeed()");
	}
}
