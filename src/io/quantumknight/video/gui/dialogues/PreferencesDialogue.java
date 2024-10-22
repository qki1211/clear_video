package io.quantumknight.video.gui.dialogues;
/********************************************************************************************
//* Filename: 		PreferencesDialogue.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    Modal Dialogue - User Preferences
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

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import io.quantumknight.video.constants.ConstantsElements;
import io.quantumknight.video.framework.constants.ConstantsColor;
import io.quantumknight.video.framework.layout.TableLayout;
import io.quantumknight.video.framework.layout.TableLayoutConstraints;
import io.quantumknight.video.framework.swing.CommonDialog;
import io.quantumknight.video.framework.swing.SwingApplicationRuntime;
import io.quantumknight.video.framework.swing.SwingFormUtil;


public class PreferencesDialogue extends CommonDialog {
	
	private static final long serialVersionUID = -8582063810328160316L;

	/**
	 * Dialogue Constructor
	 * @param masterFrame
	 */
	public PreferencesDialogue(JFrame masterFrame) {
		super(masterFrame, ConstantsElements.LABELS[15], ModalityType.APPLICATION_MODAL);
		
		try {

			// INIT MAIN PANEL AREA
			JPanel mainPanel = this.createMainPanelArea();
			
			// ADD ALL ELEMENTS TO PANEL
			mainPanel = this.addElements(mainPanel);
			
			// PUT PANEL INTO MODEL DIALOGUE
			getContentPane().add(mainPanel, BorderLayout.CENTER);
			
			
			// ADD [OK / CANCEL] BUTTONS TO BOTTOM
			JPanel buttonPane = new JPanel();
			JButton ok = SwingFormUtil.buildButtons(ConstantsElements.SYSTEM_SCREENS[1][0], ConstantsElements.SCREEN_FIELDS[26], ConstantsElements.BUTTON_LABELS[5]);
			JButton cancel = SwingFormUtil.buildButtons(ConstantsElements.SYSTEM_SCREENS[1][0], ConstantsElements.SCREEN_FIELDS[27], ConstantsElements.BUTTON_LABELS[6]);
			buttonPane.add(ok);
			buttonPane.add(cancel);
			getContentPane().add(buttonPane, BorderLayout.PAGE_END);
			
			
			// FRAME FEATURES FOR SIZE, LOCATION, AND DISPOSAL
			setResizable(false);
			pack();
			setLocationRelativeTo(masterFrame);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
			
			// ADD THIS DIALOGUE TO REFERENCEABLE PANELS IN SCREEN
			SwingApplicationRuntime.setJComponentByName(ConstantsElements.SYSTEM_SCREENS[1][0] + "|" + ConstantsElements.SCREEN_FIELDS[5], this);
		}
		catch(Exception ex) {
			this.dispose();
		}
	}
	
	/**
	 * ATOMIC SUBROUTINE - ADD ELEMENTS TO MAIN PANEL AREA
	 * @param mainPanel
	 * @return JPanel
	*/
	private JPanel addElements(JPanel mainPanel) throws Exception {
		
		JLabel heading = SwingFormUtil.buildLabel(ConstantsElements.LABELS[15], true);

		mainPanel.add(heading, new TableLayoutConstraints(0, 1, 4, 1, TableLayout.CENTER, TableLayout.CENTER));
		
		
//		
//		
//		// INITIALIZE WEB CAM AND PLACE INTO SCREEN
//		Webcam webcam = Webcam.getDefault();
//		webcam.setViewSize(new Dimension(WebcamResolution.VGA.getWidth(), WebcamResolution.VGA.getHeight())); // VGA IS BAD - US HDEF
//		
//		WebcamPanel panel = new WebcamPanel(webcam);
//		panel.setFPSDisplayed(true);
//		panel.setDisplayDebugInfo(false);
//		panel.setImageSizeDisplayed(false);
//		panel.setMirrored(true);
//		
//		
//		mainPanel.add(panel, new TableLayoutConstraints(3, 3, 3, 3, TableLayout.CENTER, TableLayout.CENTER));
//		
		
		return mainPanel;
	}
	

	/**
	 * ATOMIC SUBROUTINE - Create and size main JPanel Area
	 * @return JPanel
	*/
	private JPanel createMainPanelArea() {
		
	    	// Note: Total Panel Size is (400 X 150)
	    	double size[][] = { { 	10,					// [0] LEFT MARGIN
	    							100, 				// [1] LABEL AREA
	    							10, 				// [2] MARGIN
	    							320, 				// [3] COMBOBOX FIELD
	    							80 }, 				// [4] RIGHT MARGIN
	    							
	    						{ 	10, 				// [0] TOP MARGIN
	    							40, 				// [1] HEADING
	    							10, 				// [2] MARGIN
	    							280, 				// [3] COMBOBOX FIELD
	    							10 } 				// [4] BOTTOM MARGIN
	    	};
	    	JPanel mainArea = new JPanel();    	
	    	mainArea.setLayout(new TableLayout(size));
	    	mainArea.setBackground(ConstantsColor.COLOR_E0E0E0);
	    	
	    	return mainArea;
	}
	
	/**
     * UNIT TEST - SELF EXECUTABLE
     * @param args
    */
    public static void main(String[] args) {

        try {
        	PreferencesDialogue dialog = new PreferencesDialogue(new JFrame());
        	dialog.setSize(400, 280);
        	dialog.setVisible(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
