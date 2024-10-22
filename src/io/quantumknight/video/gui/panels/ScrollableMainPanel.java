package io.quantumknight.video.gui.panels;
/********************************************************************************************
//* Filename: 		ScrollableMainPanel.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    PANEL - Renders main scrollable row-data area for Home Screen
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

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

import io.quantumknight.common.swing.webcam.VideoPanel;
import io.quantumknight.video.constants.ConstantsApplicationGlobalUI;
import io.quantumknight.video.constants.ConstantsElements;
import io.quantumknight.video.constants.ConstantsEventRegistry;
import io.quantumknight.video.constants.ConstantsToggleButton;
import io.quantumknight.video.constants.ConstantsVideoApplication;
import io.quantumknight.video.framework.constants.ConstantsColor;
import io.quantumknight.video.framework.layout.TableLayout;
import io.quantumknight.video.framework.layout.TableLayoutConstraints;
import io.quantumknight.video.framework.swing.SwingApplicationInit;
import io.quantumknight.video.framework.swing.SwingApplicationRuntime;
import io.quantumknight.video.framework.swing.SwingFormUtil;
import io.quantumknight.video.framework.swing.TesterJFrame;
import io.quantumknight.video.gui.elements.ToggleSwitch;


public class ScrollableMainPanel extends JPanel {
	
	private static final long serialVersionUID = 6701258595550434389L;
	
	protected String screenName;
	
	private VideoPanel videoPanel;
	
    /**
     * Constructor.
     *
     */
    public ScrollableMainPanel(String screenName, VideoPanel videoPanel) {
        this.screenName = screenName;
        this.videoPanel = videoPanel;
        initializeGUI();
    }

    /**
     * Initialize GUI.
     */
    public void initializeGUI() {
    	
    	
		// ESTABLISH LAYOUT FOR SCROLLABLE MAIN DISPLAY AREA
		double size[][] = { { 	20, 					// [0] LEFT MARGIN
								260, 					// [1] CONTROL PANEL AREA
								20, 					// [2] MARGIN
								110,					// [3] REMOVE KEY TOGGLE LABEL
								40, 					// [4] REMOVE KEY TOGGLE BUTTON
								20, 					// [5] MARGIN
								90,						// [6] BYPASS ENCRYPTION TOGGLE LABEL
								40, 					// [7] BYPASS ENCRYPTION TOGGLE BUTTON
								TableLayout.FILL, 		// [8] MAIN VIDEO PANEL AREA
								20 }, 					// [9] RIGHT MARGIN 
				
							{ 	40, 					// [0] TOP MARGIN 	
								TableLayout.FILL, 		// [1] FILL AREA
								20 } 					// [2] BOT MARGIN 
		};
		
		
		this.setLayout(new TableLayout(size));
		this.setBackground(ConstantsColor.COLOR_F0F0F0);
		
		// INITIALIZE SUB-HEADING JLABELS
		JLabel bypassDecryptLabel = SwingFormUtil.buildLabel(ConstantsElements.LABELS[17], false);
		
		// INITIALIZE MAIN TOGGLE SWITCHES
		ToggleSwitch bypassDecryptToggle = this.generateToggleSwitch(bypassDecryptLabel, ConstantsToggleButton.ToggleButtonType.BYPASS);
		
		this.add(bypassDecryptLabel, new TableLayoutConstraints(3, 0, 3, 0, TableLayout.LEFT, TableLayout.CENTER));
		this.add(bypassDecryptToggle, new TableLayoutConstraints(4, 0, 4, 0, TableLayout.LEFT, TableLayout.CENTER));
		
		
		
		// INITIALIZE CONTROL PANEL
		ControlPanel controlPanel = new ControlPanel(this.screenName);
		this.add(controlPanel, new TableLayoutConstraints(1, 1, 1, 1, TableLayout.CENTER, TableLayout.CENTER));
		

		// VIDEO PANEL
		if (this.videoPanel != null) {
			this.videoPanel.setPreferredSize(ConstantsVideoApplication._VIDEO_720P);
			this.add(this.videoPanel, new TableLayoutConstraints(3, 1, 8, 1, TableLayout.CENTER, TableLayout.CENTER));
		}
		else {
			JPanel grey = new JPanel();
			grey.setBackground(ConstantsColor.COLOR_E0E0E0);
			grey.setPreferredSize(ConstantsVideoApplication._VIDEO_720P);
			this.add(grey, new TableLayoutConstraints(3, 1, 8, 1, TableLayout.FULL, TableLayout.FULL));
		}
		
		
		// SELF REGISTER
		SwingApplicationRuntime.setJComponentByName(this.screenName + "|" + ConstantsElements.SCREEN_FIELDS[9], this);
    	
		
        this.setVisible(true);
    }
    
    /**
     * ATOMIC SUBROUTINE - USE SARXOS WEBCAM PANEL
    */
    protected void addSarxosWebcamPanel() {
    	
    	Webcam webcam = Webcam.getDefault();
		
		if (webcam != null) {

			webcam.setCustomViewSizes(ConstantsVideoApplication._VIDEO_RESOLUTION);
			webcam.setViewSize(ConstantsVideoApplication._VIDEO_RESOLUTION);
			
			WebcamPanel panel = new WebcamPanel(webcam);
			panel.setFPSDisplayed(true);
			panel.setDisplayDebugInfo(false);
			panel.setImageSizeDisplayed(false);
			panel.setMirrored(true);
			
			panel.start();
			
			this.add(panel, new TableLayoutConstraints(3, 1, 8, 1, TableLayout.CENTER, TableLayout.CENTER));
		}
		else {
			System.out.println("COULDN'T FIND WEBCAM");
		}
    }
    
    /**
     * ATOMIC SUBROUTINE - Generate ToggleSwitch for Key Removal
     * @param jlabel
     * @param ConstantsToggleButton.ToggleButtonType toggleType
     * @return ToggleSwitch
    */
    private ToggleSwitch generateToggleSwitch(JLabel label, ConstantsToggleButton.ToggleButtonType toggleType) {
    	
    	List<Component> components = new ArrayList<Component>();
 		components.add(label);
 		ToggleSwitch ts = new ToggleSwitch(components, toggleType);
 		ts.setPreferredSize(new Dimension(40,18));
    	
    	return ts;
    }
    
    /**
     * Refresh sub-panels
     *
     * @param Value Object[s]
     * @return void
    */
    public void refreshScreen(Object... obj) {
    		
		if (obj != null) {
			// DATA REFRESH
		}
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
        
        TesterJFrame frame = new TesterJFrame(ScrollableMainPanel.class.getName());

        try {
        	
        		VideoPanel videoPanel = null;
        		ScrollableMainPanel thisPanel = new ScrollableMainPanel(ConstantsElements.SYSTEM_SCREENS[1][0], videoPanel);
	        	
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
