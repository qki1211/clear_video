package io.quantumknight.video.gui.panels;
/********************************************************************************************
//* Filename: 		ControlPanel.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    PANEL - Renders Encryption Controls
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

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;

import io.quantumknight.video.constants.ConstantsApplicationGlobalUI;
import io.quantumknight.video.constants.ConstantsElements;
import io.quantumknight.video.constants.ConstantsEventRegistry;
import io.quantumknight.video.constants.ConstantsToggleButton;
import io.quantumknight.video.framework.constants.ConstantsColor;
import io.quantumknight.video.framework.layout.TableLayout;
import io.quantumknight.video.framework.layout.TableLayoutConstraints;
import io.quantumknight.video.framework.swing.MasterEventRouter;
import io.quantumknight.video.framework.swing.SwingApplicationInit;
import io.quantumknight.video.framework.swing.SwingApplicationRuntime;
import io.quantumknight.video.framework.swing.SwingFormUtil;
import io.quantumknight.video.framework.swing.TesterJFrame;
import io.quantumknight.video.gui.elements.ToggleSwitch;
import io.quantumknight.video.gui.screens.HomeScreen;
import io.quantumknight.video.state.VideoDemoSettings;


public class ControlPanel extends JPanel {
	
	private static final long serialVersionUID = -1857313476630085300L;
	
	protected String screenName;
	
    /**
     * Constructor.
     *
     */
    public ControlPanel(String screenName) {
        this.screenName = screenName;
        initializeGUI();
    }

    /**
     * Initialize GUI.
     */
    public void initializeGUI() {
    	
    	
    	// PHOTOSHOP PANEL DIMENSIONS [260 X 720] AT 72DPI : 
	
    	// ESTABLISH LAYOUT FOR STATIC HEADER PANEL AREA
    	double size[][] = { { 	15, 				// [0] LEFT MARGIN
    							90, 				// [1] LEFT PANEL
    							50, 				// [2] CENTER SLIDER AREA
    							90, 				// [3] RIGHT PANEL
    							15 }, 				// [4] RIGHT MARGIN 
    							
    						{ 	 5, 				// [0] TOP MARGIN (UPPER)
    							30, 				// [1] UPPER MAIN ENCRYPTION TOGGLE
    							10, 				// [2] MARGIN - SPACE
    							30, 				// [3] UPPER AES ENCRYPTION TOGGLE
    							10, 				// [4] MARGIN - SPACE
    							30, 				// [5] STRENGTH READOUT AREA
    							10, 				// [6] MARGIN - SPACE
    							
    							
    							30, 				// [5] MAXIMUM LABEL AREA
    							300, 				// [6] CENTRAL SLIDER AREA
    							30, 				// [7] MINIMUM LABEL AREA
    							15, 				// [8] MARGIN - SPACE
    							40, 				// [9] WAVE-FORM ACTIVATION CONTROL
    							15, 				// [10] MARGIN - SPACE 
    							40, 				// [11] WAVE-FORM FREQUENCY FIELD
    							15, 				// [12] MARGIN - SPACE
    							40, 				// [13] ENCRYPT TIME - DATA AREA
    							15, 				// [14] MARGIN - SPACE
    							40, 				// [15] DECRYPT TIME - DATA AREA
    							15,					// [16] MARGIN - SPACE
    							40,					// [17] AES TOGGLE
    							15} 				// [18] BOT MARGIN (LOWER)
    	};
    	
    	this.setLayout(new TableLayout(size));
    	
    	this.setBackground(ConstantsColor.COLOR_E0E0E0);
    	
    	
    	// GENERATE STATIC LABELS FOR CONTROL PANEL
    	JLabel[] controlPanelLabels = this.generateStaticLabels();
    	
    	
    	// GENERATE TEXT FIELDS
    	JTextField waveFreqTextField = SwingFormUtil.buildTextField(ConstantsElements.SYSTEM_SCREENS[1][0], ConstantsElements.SCREEN_FIELDS[13], "");
    	waveFreqTextField.setPreferredSize(new Dimension(40, 30));
    	
    	
	    // GENERATE ENCRYPTION STRENGTH SLIDER
    	JSlider encryptionStrength = this.generateStrengthSlider();
    	
    	
    	// GENERATE TOGGLE SWITCHES
    	ToggleSwitch waveFormActivationToggle = this.generateToggleWaveFormActivationSwitch(controlPanelLabels[7], waveFreqTextField);
    	ToggleSwitch primaryEncryptionToggle = this.generateToggleEncryptionSwitch(controlPanelLabels, encryptionStrength, waveFormActivationToggle);
	    ToggleSwitch aesEncryptionToggle = this.generateAesEncryptionSwitch(controlPanelLabels, encryptionStrength, waveFormActivationToggle);
    	
    	// ADD LABELS INTO LAYOUT
	    boolean showAESControls = false;
	    if (showAESControls) {
	    	this.add(controlPanelLabels[1], new TableLayoutConstraints(1,  3, 2,  3, TableLayout.LEFT, TableLayout.CENTER));			// "AES 256 Standard"
	    }
    	
	    this.add(controlPanelLabels[0], new TableLayoutConstraints(1,  1, 2,  1, TableLayout.LEFT, TableLayout.CENTER));			// "Encrypt Video Feed"
    	this.add(controlPanelLabels[2], new TableLayoutConstraints(1,  5, 3,  5, TableLayout.LEFT, TableLayout.CENTER));			// "Strength:"
    	this.add(controlPanelLabels[3], new TableLayoutConstraints(1,  7, 1,  7, TableLayout.LEFT, TableLayout.CENTER));			// "Max"
    	this.add(controlPanelLabels[4], new TableLayoutConstraints(3,  7, 3,  7, TableLayout.LEFT, TableLayout.CENTER));			// "10,240 bit"
    	this.add(controlPanelLabels[5], new TableLayoutConstraints(3,  9, 3,  9, TableLayout.LEFT, TableLayout.CENTER));			// "512 bit"
    	this.add(controlPanelLabels[6], new TableLayoutConstraints(1,  9, 1,  9, TableLayout.LEFT, TableLayout.CENTER));			// "Min"
    	this.add(controlPanelLabels[7], new TableLayoutConstraints(1, 11, 3, 11, TableLayout.LEFT, TableLayout.CENTER));			// "Activate Wave-Form"
    	this.add(controlPanelLabels[8], new TableLayoutConstraints(1, 13, 2, 13, TableLayout.LEFT, TableLayout.CENTER));			// "Wave Freq (sec)"
    	this.add(controlPanelLabels[10], new TableLayoutConstraints(1, 15, 2, 15, TableLayout.LEFT, TableLayout.CENTER));			// "Encrypt Time (avg)"
    	this.add(controlPanelLabels[9], new TableLayoutConstraints(1, 17, 2, 17, TableLayout.LEFT, TableLayout.CENTER));			// "Decrypt Time (avg)"

    	this.add(controlPanelLabels[13], new TableLayoutConstraints(3, 17, 3, 17, TableLayout.RIGHT, TableLayout.CENTER));			// "0 ms" - encrypt time
       	this.add(controlPanelLabels[12], new TableLayoutConstraints(3, 15, 3, 15, TableLayout.RIGHT, TableLayout.CENTER));			// "0 ms" - decrypt time

    	
    	// ADD TOGGLE SWITCHES INTO LAYOUT
    	this.add(primaryEncryptionToggle, new TableLayoutConstraints(3, 1, 3, 1, TableLayout.RIGHT, TableLayout.CENTER));			// Primary Encryption Toggle
    	if (showAESControls) {
    		this.add(aesEncryptionToggle, new TableLayoutConstraints(3, 3, 3, 3, TableLayout.RIGHT, TableLayout.CENTER));				// Switch to AES mode Toggle
    	}
    	
    	this.add(waveFormActivationToggle, new TableLayoutConstraints(3, 11, 3, 11, TableLayout.RIGHT, TableLayout.CENTER));		// Wave-Form Toggle
    	
    	// ADD TEXTFIELDS INTO LAYOUT
    	this.add(waveFreqTextField, new TableLayoutConstraints(3, 13, 3, 13, TableLayout.RIGHT, TableLayout.CENTER));				// Wave Freq (sec) text field
    	
 
    	// ADD SLIDER INTO LAYOUT
    	this.add(encryptionStrength, new TableLayoutConstraints(2,7,2,9, TableLayout.FULL, TableLayout.FULL));						// Strength Slider (vertical)
    	
    	this.setBorder(BorderFactory.createLineBorder(ConstantsColor.COLOR_ENGLISH_GREY));
    	
    	
    	// SELF REGISTER
    	SwingApplicationRuntime.setJComponentByName(this.screenName + "|" + ConstantsElements.SCREEN_FIELDS[2], this);
    	
    	
        this.setVisible(true);
    }
    
    /**
     * ATOMIC SUBROUTINE - Generate ToggleSwitch for main encryption on-off
     * ------------------------------------------------------------------------------------------
     * Note:  Contains sub-components for primary strength JLabel
     * ------------------------------------------------------------------------------------------
     * @param waveFreqLabel
     * @param JSlider encryptionStrength
     * @param ToggleSwitch waveFormActivationToggle
     * @return ToggleSwitch
    */
    private ToggleSwitch generateToggleEncryptionSwitch(JLabel[] controlPanelLabels, JSlider encryptionStrength, ToggleSwitch waveFormActivationToggle) {
    	
    	// ADD COMPONENTS THAT WILL ADJUST BASED ON THE TOGGLE BUTTON (INACTIVE / ACTIVATE)
    	List<Component> components = new ArrayList<Component>();
    	for (int i = 0; i < controlPanelLabels.length; i++) {
    		if (i > 0) {
    			if (controlPanelLabels[i] != null) {
    				components.add(controlPanelLabels[i]);
    			}
    		}
    	}
 		
    	components.add(encryptionStrength);
    	components.add(waveFormActivationToggle);
    	
 		ToggleSwitch ts = new ToggleSwitch(components, ConstantsToggleButton.ToggleButtonType.MAIN);
 		ts.setPreferredSize(new Dimension(40,18));
    	
    	return ts;
    }
    
    private ToggleSwitch generateAesEncryptionSwitch(JLabel[] controlPanelLabels, JSlider encryptionStrength, ToggleSwitch waveFormActivationToggle) {
    	
    	// ADD COMPONENTS THAT WILL ADJUST BASED ON THE TOGGLE BUTTON (INACTIVE / ACTIVATE)
    	List<Component> components = new ArrayList<Component>();
    	for (int i = 0; i < controlPanelLabels.length; i++) {
    		if (i > 0) {
    			if ((i < 1) || (i > 2)) {  // omit strength and AES labels
    				
    				if (i < 12) { // omit timing in ms labels
        				if (controlPanelLabels[i] != null) {
            				components.add(controlPanelLabels[i]);
            			}
    				}
    			}
    		}
    	}
 		
    	components.add(encryptionStrength);
    	components.add(waveFormActivationToggle);
    	
 		ToggleSwitch ts = new ToggleSwitch(components, ConstantsToggleButton.ToggleButtonType.AES);
 		ts.setPreferredSize(new Dimension(40,18));
    	
 		SwingApplicationRuntime.setJComponentByName(this.screenName + "|" + ConstantsElements.SCREEN_FIELDS[15], ts);
 		
    	return ts;
    }
    
    /**
     * ATOMIC SUBROUTINE - Generate ToggleSwitch for Wave-Form Mode Activation
     * ------------------------------------------------------------------------------------------
     * Note:  Contains sub-components for Wave Frequency Modulation TextField / Wave Freq JLabel
     * ------------------------------------------------------------------------------------------
     * @param waveFreqLabel
     * @param JTextField waveFreqTextField
     * @return ToggleSwitch
    */
    private ToggleSwitch generateToggleWaveFormActivationSwitch(JLabel waveFreqLabel, JTextField waveFreqTextField) {
    	
    	// ADD COMPONENTS THAT WILL ADJUST BASED ON THE TOGGLE BUTTON (INACTIVE / ACTIVATE)
    	List<Component> components = new ArrayList<Component>();
 		components.add(waveFreqLabel);
 		components.add(waveFreqTextField);
 		ToggleSwitch ts = new ToggleSwitch(components, ConstantsToggleButton.ToggleButtonType.WAVEFORM);
 		ts.setPreferredSize(new Dimension(40,18));
    	
    	return ts;
    }
    
    /**
     * ATOMIC SUBROUTINE - Generate Static JLabel Objects for Control Panel
     * ------------------------------------------------------------------------------------------
     * [ 0 ]	"Encrypt Video Feed"		// 12 Point Verdana Regular
     * [ 1 ]	"AES 256 Standard"			// 12 Point Verdana Regular
     * [ 2 ]	"Strength:"					// 14 Point Verdana BOLD
     * [ 3 ]	"Max"						// 12 Point Verdana Regular
     * [ 4 ]	"10,240 bit"				// 12 Point Verdana Regular
     * [ 5 ]	"512 bit"					// 12 Point Verdana Regular
     * [ 6 ]	"Min"						// 12 Point Verdana Regular
     * [ 7 ]	"Activate Wave-Form"		// 12 Point Verdana Regular
     * [ 8 ]	"Wave Freq (sec)"			// 12 Point Verdana Regular
     * [ 9 ]	"Decrypt Time (avg)"		// 12 Point Verdana Regular
     * [ 10 ]	"Encrypt Time (avg)"		// 12 Point Verdana Regular
     * 
     * [ 11 ]	"0 ms"						// 12 Point Verdana Regular
     * [ 12 ]	"0 ms"						// 12 Point Verdana Regular
     * ------------------------------------------------------------------------------------------
     * @return JLabel[]
    */
    private JLabel[] generateStaticLabels() {
    	
    	JLabel[] controlPanelLabels = new JLabel[15];
    	
    	for (int i = 0; i < controlPanelLabels.length - 1; i++) {
    		controlPanelLabels[i] = SwingFormUtil.buildLabel(ConstantsElements.LABELS[i + 2], (i == 2 ? true : false));
    		SwingApplicationRuntime.setJComponentByName(this.screenName + "|" + ConstantsElements.LABELS[i + 2], controlPanelLabels[i]);
    		
    		if (i > 10) {
    			controlPanelLabels[i].setText("0 ms");
    		}
    	}

    	return controlPanelLabels;
    }
    
    /**
     * ATOMIC SUBROUTINE - Generate JSlider (primary) for encryption strength control setting - vertical
     * @return JSlider
    */
    private JSlider generateStrengthSlider() {
    	
    	VideoDemoSettings xv = VideoDemoSettings.getInstance();
    	
    	JSlider encryptionStrength = new JSlider(JSlider.VERTICAL, 1, 5, xv.getModulatorSetting());
    	encryptionStrength.addChangeListener(MasterEventRouter.getInstance());
    	encryptionStrength.setMajorTickSpacing(1);
    	encryptionStrength.setPaintTicks(true);
    	encryptionStrength.setBackground(ConstantsColor.COLOR_DARK_GREY);
    	
    	SwingApplicationRuntime.setJComponentByName(this.screenName + "|" + ConstantsElements.SCREEN_FIELDS[12], encryptionStrength);
    	
    	return encryptionStrength;
    }
    
    /**
     * Refresh sub-panels
     *
     * @param Value Object[s]
     * @return void
    */
    public void refreshScreen(Object... obj) {
    	
    	VideoDemoSettings xv = VideoDemoSettings.getInstance();
    	
    	HomeScreen screen = (HomeScreen)SwingApplicationRuntime.getSystemJPanelScreensByName(ConstantsElements.SYSTEM_SCREENS[1][0]);
    	JLabel encryptionTimeMS = (JLabel)screen.getField(ConstantsElements.LABELS[14]);
    	JLabel decryptionTimeMS = (JLabel)screen.getField(ConstantsElements.LABELS[15]);
    	
    	encryptionTimeMS.setText(xv.getEncryptionTime() + " ms");
    	decryptionTimeMS.setText(xv.getDecryptionTime() + " ms");  // this is the encryption label
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
        
        TesterJFrame frame = new TesterJFrame(ControlPanel.class.getName());

        try {
        		ControlPanel thisPanel = new ControlPanel(ConstantsElements.SYSTEM_SCREENS[1][0]);
	        	
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
