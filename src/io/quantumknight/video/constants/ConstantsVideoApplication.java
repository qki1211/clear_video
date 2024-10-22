package io.quantumknight.video.constants;
/********************************************************************************************
//* Filename: 		ConstantsVideoApplication.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    CONSTANTS CLASS - CLEAR Encrypted Video Demo Application
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

import java.awt.Dimension;

import io.quantumknight.common.swing.webcam.components.WebcamResolution;

public abstract class ConstantsVideoApplication {
	
	public static final Dimension _VIDEO_VGA 			= WebcamResolution.VGA.getSize();
	public static final Dimension _VIDEO_720P 			= WebcamResolution.SevenTwentyP.getSize();
	
	public static final Dimension _VIDEO_RESOLUTION 	= _VIDEO_VGA;
	
	public static final int _MAXIMUM_WIDTH 				= (int)_VIDEO_RESOLUTION.getWidth();
	public static final int _MAXIMUM_HEIGHT 			= (int)_VIDEO_RESOLUTION.getHeight();
	
	public static final boolean _DEBUG_UI 				= false; 		// DEBUG USER INTERFACE COMPONENTS
	public static final boolean _DEBUG_ACTION_MVC 		= false; 		// DEBUG ACTION HANDLERS
	public static final boolean _DEBUG_EVENT_LISTENERS 	= false; 		// DEBUG EVENT LISTENERS
	
}
