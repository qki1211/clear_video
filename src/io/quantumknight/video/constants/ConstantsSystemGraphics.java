package io.quantumknight.video.constants;
/********************************************************************************************
//* Filename: 		ConstantsSystemGraphics.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    Application Constants - Encoded Image Files / System Graphics & Icons
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


public abstract class ConstantsSystemGraphics {
	
	public enum CLEAR_VIDEO_SystemGraphics { 					ICON_ICE_PROGRAM_ICON("icon_256x256.png"), 			// PROGRAM
																IMAGE_ABOUT_XLC("about-clear-750x750.png"),
																ICON_STOP_24_24("x_stop_icon_24_24.png"),
																ICON_START_32_32("play-button_30_30.png");
																
		
		private String fileName;
		
		/**
		 * Enum Constructor - Takes a file name
		 * 
		 * @param fileName
		 */
		private CLEAR_VIDEO_SystemGraphics(String fileName) {
			this.fileName = fileName;
		}

		/**
		 * @return the fileName
		 */
		public String getFileName() {
			return fileName;
		}
	}
}
