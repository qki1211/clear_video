package io.quantumknight.video.state;
/********************************************************************************************
//* Filename: 		GlobalInitializationSingleton.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    SINGLETON - Application State Initialization Sequence - Primary
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

import io.quantumknight.video.framework.io.LogManager;
import io.quantumknight.video.framework.io.Logger;
import io.quantumknight.video.util.TimeMarkerNano;


public class GlobalInitializationSingleton {

	private static final Logger log = LogManager.getLogger(GlobalInitializationSingleton.class);
	
	private static GlobalInitializationSingleton _INSTANCE = new GlobalInitializationSingleton(); // Static Initializer - Java Singleton Pattern
	
	private boolean initialized;
	
	
	/**
	 * Private Constructor - Java Singleton Pattern
	 */
	private GlobalInitializationSingleton() {
		super();
		
		// INITIALIZE GLOBAL CACHE
		TimeMarkerNano sw = new TimeMarkerNano();
		
		try {
			sw.setTimeMark(1);
			this.initialized = this.initialize();
			sw.setTimeMark(2);
			
			// LOG RESULT OF GLOBAL CACHE INITIALIZATION
			if (this.initialized) {
				
				log.info("<< STARTUP COMPLETED - SYSTEM ONLINE >>");
			}
			else {
				log.fatal("SYSTEM FAILED TO INITIALIZE - APPLICATION RESTART REQUIRED!");
			}
		}
		catch(Exception e) {
			log.fatal("SYSTEM FAILED TO INITIALIZE - APPLICATION RESTART REQUIRED! : ", e.fillInStackTrace());
		}
	}

	/**
	 * Singleton Accessor - Public
	 * 
	 * @return
	 */
	public static GlobalInitializationSingleton getInstance() {
		return _INSTANCE;
	}

	/**
	 * Atomic Subroutine - Initialize all memory resident singletons,
	 * batch-jobs, and processes
	 * 
	 * @return void
	 * @throws Exception
	 */
	private boolean initialize() throws Exception {
		
		// INDIVIDUAL INITIALIZERS HERE
		VideoDemoSettings vdc = VideoDemoSettings.getInstance();
		
		return vdc.isInitialized();
	}
    
	/**
	 * @return the initialized
	 */
	public boolean isInitialized() {
		return initialized;
	}
}
