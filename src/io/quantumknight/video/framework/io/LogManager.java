package io.quantumknight.video.framework.io;
/********************************************************************************************
//* Filename: 		LogManager.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    UTILITY - CONSOLE LOG OUTPUT
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

import io.quantumknight.video.framework.constants.TimeDateConstants;
import io.quantumknight.video.util.TimeStringUtil;

public abstract class LogManager {
	
	public static enum Level { DEBUG, INFO, WARN, ERROR, FATAL };
	
	/**
	 * FACTORY PATTERN - GENERATE A NEW LOGGER
	 * @param Class<?> clazz
	 * @return Logger
	*/
	public static Logger getLogger(Class<?> clazz) {
		return Logger.generate(clazz.getName());
	}
	
	/**
	 * LOG OUTPUT
	 * ------------------------------------------------------------------------------------
	 * VARIABLE CONFIGURABLE OUTPUT
	 * ------------------------------------------------------------------------------------
	 * > TYPE 1:  CONSOLE OUTPUT
	 * > TYPE 2:  LOCAL LOG FILE
	 * > TYPE 3:  WINDOWS NT EVENT LOG
	 * > TYPE 4:  EXTERNAL SERVER
	 * ------------------------------------------------------------------------------------
	 * 04-08-2021 5:25:22 PM INFO  (CLASS.java:Line Number) - MESSAGE + STACK TRACE
	 * ------------------------------------------------------------------------------------ 
	 * @param String message
	 * @param Throwable t - Stack Trace Detail
	 * @param Level - log level
	 * @param className
	*/
	protected static void write(String message, Throwable t, Level level, String className) {
		
		// REV 1 : ONLY INCLUDES CONSOLE OUTPUT
		String time = TimeStringUtil.getPSTTimeString(System.currentTimeMillis(), TimeDateConstants._ISO_8601_FORMAT);
		String prefix = "";
		
		switch(level) {
		
			case DEBUG 	: 	prefix = "DEBUG"; 	break;
			case INFO 	: 	prefix = "INFO"; 	break;
			case WARN 	: 	prefix = "WARN"; 	break;
			case ERROR 	: 	prefix = "ERROR"; 	break;
			case FATAL 	: 	prefix = "FATAL"; 	break;
		}
		
		System.out.println(time + " " + prefix + " (" + className + ":?) - " + message);
		if (t != null) {
			t.printStackTrace();
		}
		
	}
}
