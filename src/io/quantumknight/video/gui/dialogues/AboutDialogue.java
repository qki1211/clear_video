package io.quantumknight.video.gui.dialogues;
/********************************************************************************************
//* Filename: 		AboutDialogue.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    Modal Dialogue - About Application
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
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import io.quantumknight.video.constants.ConstantsApplicationGlobalUI;
import io.quantumknight.video.constants.ConstantsElements;
import io.quantumknight.video.constants.ConstantsEventRegistry;
import io.quantumknight.video.constants.ConstantsSystemGraphics;
import io.quantumknight.video.framework.constants.ConstantsColor;
import io.quantumknight.video.framework.constants.ConstantsFonts;
import io.quantumknight.video.framework.layout.TableLayout;
import io.quantumknight.video.framework.layout.TableLayoutConstraints;
import io.quantumknight.video.framework.swing.CommonDialog;
import io.quantumknight.video.framework.swing.ImageTile;
import io.quantumknight.video.framework.swing.SwingApplicationInit;
import io.quantumknight.video.framework.swing.SwingApplicationRuntime;
import io.quantumknight.video.framework.swing.SwingFormUtil;


public class AboutDialogue extends CommonDialog {
	
	private static final long serialVersionUID = 5935370631912633586L;
	
	/**
	 * Dialogue Constructor
	 * @param masterFrame
	 */
	public AboutDialogue(JFrame masterFrame) {
		super(masterFrame, ConstantsElements.LABELS[0], ModalityType.APPLICATION_MODAL);
		
		try {

			// INIT MAIN PANEL AREA
			JPanel mainPanel = this.createMainPanelArea();
			
			// ADD ALL ELEMENTS TO PANEL
			mainPanel = this.addElements(mainPanel);
			
			// PUT PANEL INTO MODEL DIALOGUE
			Container c = getContentPane();
			c.setBackground(ConstantsColor.COLOR_WHITE_COLOR);
			c.add(mainPanel, BorderLayout.CENTER);
			
			
			// ADD [CLOSE] BUTTON TO BOTTOM
			JPanel buttonPane = new JPanel();
			JButton close = SwingFormUtil.buildButtons(ConstantsElements.SYSTEM_SCREENS[1][0], ConstantsElements.SCREEN_FIELDS[21], ConstantsElements.BUTTON_LABELS[7]);
			buttonPane.add(close);
			getContentPane().add(buttonPane, BorderLayout.PAGE_END);
			
			
			
			// FRAME FEATURES FOR SIZE, LOCATION, AND DISPOSAL
			setResizable(false);
			pack();
			setLocationRelativeTo(masterFrame);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
			
			// ADD THIS DIALOGUE TO REFERENCEABLE PANELS IN SCREEN
			SwingApplicationRuntime.setJComponentByName(ConstantsElements.SYSTEM_SCREENS[1][0] + "|" + ConstantsElements.SCREEN_FIELDS[4], this);
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
		
		// ADD HEADER
		mainPanel.add(SwingFormUtil.buildLabel(ConstantsElements.LABELS[11], true), new TableLayoutConstraints(1, 0, 1, 0, TableLayout.CENTER, TableLayout.CENTER));
		
		// VERSION LABEL
		
		JPanel xcclogoPanel = new JPanel();
		ImageIcon xccLogo = SwingApplicationRuntime.getGraphicFromApplicationSystem(ConstantsSystemGraphics.CLEAR_VIDEO_SystemGraphics.IMAGE_ABOUT_XLC.name());
		JLabel versionLabel = SwingFormUtil.buildLabel(ConstantsApplicationGlobalUI.APPLICAION_NAME,false);
		versionLabel.setFont(ConstantsFonts.FONT_TAHOMA_12B);
		versionLabel.setForeground(ConstantsColor.COLOR_TEXT_BLACK);
		

		StringBuffer sb = new StringBuffer()
		.append("Open Source - Not For Resale")
		.append("")
		.append("")
		.append("");
		
		JLabel description = SwingFormUtil.buildLabel(sb.toString(),false);
		description.setFont(ConstantsFonts.FONT_TAHOMA_12);
		description.setForeground(ConstantsColor.COLOR_TEXT_BLACK);
		
		// ESTABLISH LAYOUT FOR ABOUT DETAILS
    	double size[][] 	= { { 	80, 				// [0] LEFT MARGIN
    								480, 				// [1] CONTENT AREA
    								80}, 				// [2] RIGHT MARGIN
    								
    							{ 	5, 					// [0] TOP MARGIN
    								480, 				// [1] IMAGE
    								16, 				// [2] MARGIN 
    								30, 				// [3] VERSION LABEL
    								30, 				// [4] TEXT CONTENT
    								30}  				// [5] BOT MARGIN
    	};
    	xcclogoPanel.setLayout(new TableLayout(size));

    	
    	
    	xcclogoPanel.add(new ImageTile(xccLogo), new TableLayoutConstraints(1, 1, 1, 1, TableLayout.FULL, TableLayout.FULL));
    	xcclogoPanel.add(versionLabel, new TableLayoutConstraints(1, 3, 1, 3, TableLayout.CENTER, TableLayout.TOP));
    	xcclogoPanel.add(description, new TableLayoutConstraints(1, 4, 1, 4, TableLayout.CENTER, TableLayout.CENTER));
    	
    	
    	xcclogoPanel.setBackground(ConstantsColor.COLOR_WHITE_COLOR);
		
		mainPanel.add(xcclogoPanel, new TableLayoutConstraints(1, 1, 1, 1, TableLayout.FULL, TableLayout.FULL));
		
		return mainPanel;
	}
	
	/**
	 * ATOMIC SUBROUTINE - Create and size main JPanel Area
	 * @return JPanel
	*/
	private JPanel createMainPanelArea() {
		
	    	double size[][] = { { 	0,					// [0] LEFT MARGIN
	    							640, 				// [1] LABEL AREA
	    							0 }, 				// [2] RIGHT MARGIN
	    							
	    						{ 	0, 					// [0] TOP MARGIN
	    							640, 				// [1] HEADER BODY SEPARATOR MARGIN
	    							0 } 				// [2] BOTTOM MARGIN
	    	};
	    	JPanel mainArea = new JPanel();    	
	    	mainArea.setLayout(new TableLayout(size));
	    	mainArea.setBackground(ConstantsColor.COLOR_WHITE_COLOR);
	    	return mainArea;
	}
	
	/**
     * UNIT TEST - SELF EXECUTABLE
     * @param args
    */
    public static void main(String[] args) {

        try {
        	
        	SwingApplicationInit init = new SwingApplicationInit();
	        init.initScreens(
	        		ConstantsApplicationGlobalUI.APPLICAION_NAME, 
	        		ConstantsElements.SYSTEM_SCREENS, 
	        		ConstantsApplicationGlobalUI.INITIAL_WINDOW_WIDTH, 
	        		ConstantsApplicationGlobalUI.INITIAL_WINDOW_HEIGHT, 
	        		ConstantsApplicationGlobalUI.USE_SPLASH_SCREEN,
	        		ConstantsApplicationGlobalUI.LOAD_SYSTEM_AUDIO);
	        init.initEventHandling(ConstantsEventRegistry.EVENT_HANDLERS);
	        
        		AboutDialogue dialog = new AboutDialogue(new JFrame());
	        	dialog.setSize(640, 500);
	        	dialog.setVisible(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
