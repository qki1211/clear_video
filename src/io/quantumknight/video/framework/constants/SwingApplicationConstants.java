package io.quantumknight.video.framework.constants;
/********************************************************************************************
//* Filename: 		SwingApplicationConstants.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    JFC/SWING FRAMEWORK - GENERIC SWING CONSTANTS - UNIVERSAL
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

import javax.swing.JScrollPane;

public abstract class SwingApplicationConstants {

	public static final String GRAPHICS_SPLASHSCRN 						= "splsh.gfx";
	public static final String GRAPHICS_APPLICATION 					= "systm.gfx";
	public static final String AUDIO_APPLICATION 						= "systm.aud";
	
	public static final String IMGKEY_SPLASH_SCREEN 					= "splash";
	
	public static final int TESTER_WINDOW_WIDTH 						= 1024;
	public static final int TESTER_WINDOW_HEIGHT 						= 768;
	
	public static final boolean USE_GLOBAL_EXIT     					= true;     // Use the Global Exist Listener - if not, application must define it's own!
	
	public static final boolean DEBUG 									= false; 	// debug application to console output
	public static final boolean DEBUG_FRAMEWORK 						= false; 	// debug application to console output from framework perspective
	
	public static final String SYSTRAY_DEFAULT_CMD  					= "SysTrayPressActionWindows";
	
	public static final String USER_PROFILE 							= "user_profile"; 

	public static final int JPG_RENDER_QUALITY_MAX 						= 100; 	// 1-to-100
	public static final int JPG_RENDER_QUALITY_HI 						= 85; 	// 1-to-100
	public static final int JPG_RENDER_QUALITY_MED 						= 70; 	// 1-to-100
	public static final int JPG_RENDER_QUALITY_LO 						= 55; 	// 1-to-100
	
	public static final int MASTER_FRAME_HORIZONTAL_SCROLLBAR_POLICY 	= JScrollPane.HORIZONTAL_SCROLLBAR_NEVER;
	public static final int MASTER_FRAME_VERTICAL_SCROLLBAR_POLICY 		= JScrollPane.VERTICAL_SCROLLBAR_NEVER;
	
}
