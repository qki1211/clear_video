package io.quantumknight.video.constants;
/********************************************************************************************
//* Filename: 		ConstantsElements.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    Application Constants for Screens, Panels, Labels, & Buttons - 
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

public abstract class ConstantsElements {

	/** ALL SCREENS IN EDIT APPLICATION - SCREENS PRE-LOAD AT APPLICATION STARTUP SEQUENCE **/
	public static final String[][] SYSTEM_SCREENS   = {	/**  0**/	{ 	"LoginScreen", "io.quantumknight.video.gui.screens.LoginScreen"},
														/**  1**/ 	{ 	"HomeScreen",  "io.quantumknight.video.gui.screens.HomeScreen"}
	};
	
	/** STATIC DEFINITION OF ALL SINGLETON-ACCESSIBLE PANELS REGISTERED WITHIN SCREENS **/
	public static final String[] SCREEN_FIELDS 		= { /** 0**/		"HomeScreen.selfPanel",
												        /** 1**/		"HomeScreen.scrollableMainPanel",
												        /** 2**/		"HomeScreen.scrollableMainPanel.ControlPanel",
												        /** 3**/		"",
												        /** 4**/		"HomeScreen.aboutDialogue",
												        /** 5**/		"HomeScreen.preferencesDialogue",
												        /** 6**/		"HomeScreen.generateLicenseDialogue",
												        /** 7**/		"",
												        /** 8**/		"",
												        /** 9**/		"Export.busyDialogue",
												        /**10**/		"",
												        /**11**/		"",
												        /**12**/		"HomeScreen.controlpanel.strength.jslider",
												        
												        /**13**/		"HomeScreen.controlpanel.wavefrequence.textfield.jtextfield",
												        /**14**/		"HomeScreen.controlpanel.wavefrequence.textfield.label",
												        
												        /**15**/		"ControlPanel.AESTOGGLE",
												        /**16**/		"",
												        
												        /**17**/		"",
												        
												        /**18**/		"GenerateLicenseDialogue.ok",
												        /**19**/		"GenerateLicenseDialogue.cancel",
												        
												        /**20**/		"",
												        /**21**/		"AboutDialogue.master.close",
												        /**22**/		"AboutDialogue.bbb",
												        /**23**/		"AboutDialogue.ccc",
												        /**24**/		"AboutDialogue.ddd",
												        /**25**/		"",
												        /**26**/		"PreferencesDialogue.master.ok",
												        /**27**/		"PreferencesDialogue.master.cancel",
												        /**28**/		"PreferencesDialogue.master.ipaddress",
												        /**29**/		"PreferencesDialogue.ddd",
												        /**30**/		"PreferencesDialogue.eee",
	};
	
	/** STATIC DEFINITION OF ALL JLABELS IN APPLICATION **/
	public static final String[] LABELS 			= {	/** 0**/		"CLEAR Encrypted Video Preview",
												        /** 1**/		"Remove Key",
												        /** 2**/		"Encrypt Video Feed",
												        /** 3**/		"AES 256 Standard",
												        /** 4**/		"Strength:",
												        /** 5**/		"Max",
												        /** 6**/		" 10,240 bit",
												        /** 7**/		" 512 bit",
												        /** 8**/		"Min",
												        /** 9**/		"Strength Modulator",
												        /**10**/		"Frequency (sec)",
												        /**11**/		"Decrypt Time (avg)",
												        /**12**/		"Encrypt Time (avg)",
												        /**13**/		"0 sec",
												        /**14**/		"0 ms-e",
												        /**15**/		"0 ms-d",
												        /**16**/		"User Preferences",
												        /**17**/		"Remove Key",
												        /**18**/		"Bypass Decrypt",
												        /**19**/		"AES Encrypt"
	};
	
	
	/** STATIC DEFINITION OF ALL JBUTTON LABELS IN APPLICATION **/
	public static final String[] BUTTON_LABELS 		= { /**  0**/		"Start Video",					// 0 - Toolbar + File Menu
														/**  1**/		"Stop Video",					// 1 - Toolbar + File Menu
														/**  2**/		"About",						// 2 - In Help Menu
														/**  3**/		"Exit",							// 3 - In File Menu
														/**  4**/		"Preferences...",				// 4 - In Edit Menu
														/**  5**/		"OK",							// 5 - SHARED - OK - Button - Preferences Dialogue + Call Dialogue
														/**  6**/		"Cancel", 						// 6 - SHARED - Cancel - Button - Preferences Dialogue + Call Dialogue
														/**  7**/		"Close",						// 7 - In About Dialogue - Close Modal
														/**  8**/		"EnableEncryption",				// 8 - Enable Encryption
														/**  9**/		"DisableEncryption",			// 9 - Disable Encryption
														/** 10**/		"BypassDecryption",				// 10- ByPass Decryption
														/** 11**/		"ResumeDecryption",				// 11- Resume Decryption
														/** 12**/		"StartWaveFormMode",			// 12- Start WaveForm Mode
														/** 13**/		"StopWaveFormMode",				// 13- Stop WaveForm Mode
														/** 14**/		"AesEnableEncryption",			// 14- Start AES Encryption
														/** 15**/		"AesDisableEncryption",			// 15- Stop AES Encryption
	};

	/** STATIC DEFINITION OF ALL BUTTON / ICON MAPPINGS IN APPLICATION **/
	public static final String[][] _BUTTONS 		= {	/**  0**/		{ BUTTON_LABELS[0], ConstantsSystemGraphics.CLEAR_VIDEO_SystemGraphics.ICON_START_32_32.name() },
														/**  1**/		{ BUTTON_LABELS[1], ConstantsSystemGraphics.CLEAR_VIDEO_SystemGraphics.ICON_STOP_24_24.name() },
	};
	
	/** STATIC DEFINITION OF ALL JMENU NAMES IN APPLICATION **/
	public static final String[] _MENU_NAMES 		= { /**  0**/		"File",
														/**  1**/		"Edit",
														/**  2**/		"Help"
	};
	
	
	public static final String[] PANELS 			= { 				"MAIN_PANEL_HEADER",
																		"MAIN_PANEL_SCROLLABLE_AREA"
	};
	
	
	/** OTHER STATIC DEFINITIONS  **/
	public static final String DEFAULT_COMOBOBOX_VAL = 					"<Select>";
	
}
