package io.quantumknight.video.framework.constants;
/********************************************************************************************
//* Filename: 		TimeDateConstants.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    Standardized Time and Date Strings
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

public abstract class TimeDateConstants {

	public static final String _STP_DATA_DATE_FORMAT 					= "yyyyMMdd";
	
	public static final String _LEGACY_SQL_SERVER_DATE_PRECISION_STR 	= "MM/dd/yyyy";						// SINGLE DATE-PRECISION FORMAT FOR LEGACY APPLICATIONS
	public static final String _SALESFORCE_DATE_FORMAT 					= "yyyy-MM-dd";						// APPLICATION SPECIFIC DATE TIME
	
	public static final String _MONTH_YEAR 								= "MM/yyyy"; 						// E.G;  11/2020
	
	public static final String _US_DATE_FORMAT 							= "MM-dd-yyyy";						// E.G.; 12-11-2016
	
	public static final String _YEAR_FORMAT 							= "yyyy";
	public static final String _MONTH_FORMAT 							= "MM";
	public static final String _MONTH_FULL_NAME_LONG_FORMAT 			= "MMMMM";
	public static final String _DAY_FORMAT 								= "dd";
	public static final String _YEAR_MONTH_FORMAT 						= "yyyy-MM";
	
	public static final String _LDX_SOW_CONTRACT_DISPLAY_DATE 			= "MMMMM dd, yyyy"; 				// E.G; September 30, 2020
	public static final String _LDX_CONTRACT_NAME_FORMAT_DATETIME 		= "MMddyy"; 						// E.G; 091020
	
	public static final String _FILENAME_TO_SECOND 						= "yyyy-MM-dd-HH-mm-ss"; 			// E.G; 2022_04_14_15_11_45
	
	public static final String _ISO_8601_FORMAT 						= "yyyy-MM-dd'T'HH:mm:ss'Z'";
	
	public static final String _ISO_8601_COINBASE_API_FORMAT 			= "yyyy-MM-dd'T'HH:mm:ss";
																		   
	public static final String _ISO_8601_TSHEETS_API_FORMAT 			= "yyyy-MM-dd'T'HH:mm:ssXXX";
	public static final String YEAR_MONTH_DATE_HOUR_MIN_SEC_FORMAT 		= "yyyy-MM-dd HH:mm:ss";			
	public static final String YEAR_MONTH_DATE_FORMAT 					= "yyyy-MM-dd";	
	
	public static final String _LDX_INVOICE_DISPLAY_FORMAT 				= "MMM d, YYYY"; 					// E.G:  Sep 8, 2020
	
	public static final long _ONE_SECOND 								= 1000L; // 1 seconds
	public static final long _ONE_MINUTE 								= (_ONE_SECOND * 60L);
	public static final long _HALF_AN_HOUR   							= (_ONE_MINUTE * 30L);
	public static final long _ONE_HOUR   								= (_ONE_MINUTE * 60L);
	public static final long _ONE_DAY 	 								= (_ONE_HOUR * 24L);
	
}
