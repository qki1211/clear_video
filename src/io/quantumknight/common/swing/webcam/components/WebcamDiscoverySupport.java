package io.quantumknight.common.swing.webcam.components;
/********************************************************************************************
//* Filename: 		WebcamDiscoverySupport.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    WRAPPER IMPLEMENTATION - SARXOS WEBCAM DISCOVERY SUPPORT
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

public interface WebcamDiscoverySupport {

	/**
	 * Default webcam discovery scan interval in milliseconds.
	 */
	public static final long DEFAULT_SCAN_INTERVAL = 3000;

	/**
	 * Get interval between next discovery scans. Time interval is given in milliseconds.
	 *
	 * @return Time interval between next scans
	 */
	long getScanInterval();

	/**
	 * Check if scan is possible. In some cases, even if driver support devices discovery, there can
	 * be a situation when due to various factors, scan cannot be executed (e.g. devices are busy,
	 * network is unavailable, devices registry not responding, etc). In general this method should
	 * return true.
	 *
	 * @return True if scan possible, false otherwise
	 */
	boolean isScanPossible();
}
