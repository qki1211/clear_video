package io.quantumknight.video.framework.swing;
/********************************************************************************************
//* Filename: 		CommonScreen.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    JFC/SWING FRAMEWORK - SCREEN INTERFACE FOR ALL FRAMEWORK-COMPLIANT JPANELS
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

import javax.swing.JPanel;

public abstract class CommonScreen extends JPanel {

	private static final long serialVersionUID = 7870120120994512041L;
	
	/**
	 * Initialize sub panels
	 * @return void
	 * @throws Exception
	*/	
	public abstract void initializeGUI() throws Exception;
	
	/** 
	 * Refresh screen with real data
	 * @param Object valueObject
	 * @return void
	*/
	public abstract void refreshScreen(Object... valueObjects) throws Exception;
	
	/**
	 * Reset all fields in screen
	 * @return void
	*/
	public abstract void resetAllFields();
	
	/**
	 * Disables all fields in screen
	 * @return void
	*/
	public abstract void setDisabledAll();
	
	/**
	 * Enables all fields in screen
	 * @return void
	*/
	public abstract void setEnabledAll();
	
	/**
	 * Get field by name
	 * @param String fieldName
	 * @return Object
	*/
	public abstract Object getField(String fieldName);
	
	/**
	 * Set field by name
	 * @param String fieldName
	 * @param Object fieldObject
	 * @return void
	*/
	public abstract void setField(String fieldName, Object fieldObject);
	
}
