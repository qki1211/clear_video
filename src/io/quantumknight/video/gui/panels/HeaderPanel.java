package io.quantumknight.video.gui.panels;
/********************************************************************************************
//* Filename: 		HeaderPanel.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    PANEL - Renders static main-area header text
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

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import io.quantumknight.video.constants.ConstantsApplicationGlobalUI;
import io.quantumknight.video.constants.ConstantsElements;
import io.quantumknight.video.constants.ConstantsEventRegistry;
import io.quantumknight.video.framework.constants.ConstantsColor;
import io.quantumknight.video.framework.constants.ConstantsFonts;
import io.quantumknight.video.framework.layout.TableLayout;
import io.quantumknight.video.framework.layout.TableLayoutConstraints;
import io.quantumknight.video.framework.swing.SwingApplicationInit;
import io.quantumknight.video.framework.swing.SwingApplicationRuntime;
import io.quantumknight.video.framework.swing.TesterJFrame;


public class HeaderPanel extends JPanel {
	
	private static final long serialVersionUID = 6701258595550434389L;
	
	protected String screenName;
	
    /**
     * Constructor.
     *
     */
    public HeaderPanel(String screenName) {
        this.screenName = screenName;
        initializeGUI();
    }

    /**
     * Initialize GUI.
     */
    public void initializeGUI() {
    	
//		int wh = ConstantsApplicationGlobalUI.INITIAL_WINDOW_WIDTH;
	
    	// ESTABLISH LAYOUT FOR STATIC HEADER PANEL AREA
    	double size[][] = { { 	10, 				// [0] LEFT MARGIN
    							300, 				// [1] PROGRAM TITLE
    							TableLayout.FILL, 	// [1] DYNAMIC STATUS AREA
    							10 }, 				// [2] RIGHT MARGIN 
    								
    						{ 	20, 				// [0] TOP MARGIN (UPPER)
    							20, 				// [1] FIXED HEADER AREA - STATIC HEIGHT
    							20 } 				// [2] TOP MARGIN (LOWER)
    	};
    	
    	this.setLayout(new TableLayout(size));
    	
    	// BUILD CONTENT
	    JLabel header = new JLabel(ConstantsElements.LABELS[0]);
	    header.setFont(ConstantsFonts.FONT_VERDANA_14B);
	    header.setBackground(ConstantsColor.COLOR_E0E0E0);
	    header.setForeground(ConstantsColor.COLOR_TEXT_DARK_GREY);
	    SwingApplicationRuntime.setJComponentByName(this.screenName + "|" + ConstantsElements.LABELS[0], header);
	    
    	this.setBorder(BorderFactory.createLineBorder(ConstantsColor.COLOR_ENGLISH_GREY));
    	this.add(header, new TableLayoutConstraints(1, 1, 1, 1, TableLayout.LEFT, TableLayout.CENTER));
    	
    	
    	// SELF REGISTER
    	SwingApplicationRuntime.setJComponentByName(this.screenName + "|" + ConstantsElements.SCREEN_FIELDS[9], this);
    	
        this.setVisible(true);
    }
    
    /**
     * Refresh sub-panels
     *
     * @param Value Object[s]
     * @return void
    */
    public void refreshScreen(Object... obj) {
    		
    	// REFRESH HEADER LABEL TO REFLECT STATUS OF SYSTEM
    	
//    	AES ETC
//		controlPanelLabels[i] = SwingFormUtil.buildLabel(ConstantsElements.LABELS[i + 2], (i == 2 ? true : false));
//		SwingApplicationRuntime.setJComponentByName(this.screenName + "|" + ConstantsElements.LABELS[i + 2], controlPanelLabels[i]);
    	
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
	        
        } 
        catch (Exception e) {
        		e.printStackTrace();
        		System.exit(1);
        }
        
        TesterJFrame frame = new TesterJFrame(HeaderPanel.class.getName());

        try {
        		HeaderPanel thisPanel = new HeaderPanel(ConstantsElements.SYSTEM_SCREENS[1][0]);
	        	
	        	JScrollPane scrollPane = new JScrollPane(thisPanel);
	        	frame.setContentPane(scrollPane);
	        	frame.pack();
	        	
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
