package io.quantumknight.video.framework.layout;
/********************************************************************************************
//* Filename: 		TableLayoutConstants.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    JFC/SWING FRAMEWORK - CUSTOM LAYOUT MANAGER SUB-SYSTEM
//* 				
//* 				Constants class supports modes of operation for Table Layout Manager
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

public interface TableLayoutConstants {

	/** Indicates that the component is left justified in its cell */
	public static final int LEFT = 0;

	/** Indicates that the component is top justified in its cell */
	public static final int TOP = 0;

	/** Indicates that the component is centered in its cell */
	public static final int CENTER = 1;

	/** Indicates that the component is full justified in its cell */
	public static final int FULL = 2;

	/** Indicates that the component is bottom justified in its cell */
	public static final int BOTTOM = 3;

	/** Indicates that the component is right justified in its cell */
	public static final int RIGHT = 3;

	/** Indicates that the row/column should fill the available space */
	public static final double FILL = -1.0;

	/**
	 * Indicates that the row/column should be allocated just enough space to
	 * accomidate the preferred size of all components contained completely within
	 * this row/column.
	 */
	public static final double PREFERRED = -2.0;

	/**
	 * Indicates that the row/column should be allocated just enough space to
	 * accomidate the minimum size of all components contained completely within
	 * this row/column.
	 */
	public static final double MINIMUM = -3.0;

	/** Minimum value for an alignment */
	public static final int MIN_ALIGN = 0;

	/** Maximum value for an alignment */
	public static final int MAX_ALIGN = 3;

}
