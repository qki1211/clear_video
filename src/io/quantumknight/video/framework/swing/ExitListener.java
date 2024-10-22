package io.quantumknight.video.framework.swing;
/********************************************************************************************
//* Filename: 		ExitListener.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    JFC/SWING FRAMEWORK - WINDOW CLOSING EVENT HANDLER - TERMINATES PROGRAM
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

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import io.quantumknight.video.framework.constants.SwingApplicationConstants;

public class ExitListener extends WindowAdapter {
	
	/**
	 * Handle Window Closing Event -
	 * Conditional logic to provide user with an "Are you Sure" dialog
	 * @param event
	*/
	public void windowClosing(WindowEvent event) {
		
	    //  STEP 1) ACQUIRE SCREEN 
	    MasterJFrame masterFrame = SwingApplicationRuntime.getMasterFrame();
	
	    
	    //  STEP 2) Fire confirm box
	    int rval = JOptionPane.showConfirmDialog(masterFrame,
	                                            "You are about to exit this program!\nAre you sure?",
	                                            "Exit Program?",
	                                            JOptionPane.OK_CANCEL_OPTION,
	                                            JOptionPane.QUESTION_MESSAGE);
	    switch(rval) {
	        case -1:    //key = "X";
	                    masterFrame.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
	                    break;
	                    
	        case 2:     //key = "Cancel";
	                    masterFrame.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE ); 
	                    break;
	                    
	        case 0:     // fire windows exit handler!  clean up working files!
	                    if (SwingApplicationConstants.DEBUG) { System.out.println("PROGRAM TERMINATED SUCCESSFULLY!");}
	                    System.exit(0);
	                    break;
	                    
	        default:    //key = "Not Understood";
	                    break;
	    }
	}
}
