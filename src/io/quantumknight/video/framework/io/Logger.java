package io.quantumknight.video.framework.io;
/********************************************************************************************
//* Filename: 		Logger.java
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

import io.quantumknight.video.framework.io.LogManager.Level;

public class Logger {
	
	private String className;
	
	/**
	 * FACTORY PATTERN - PACKAGE-LEVEL INSTANTIATION CONTROL
	 * @param className
	 * @return
	*/
	protected static Logger generate(String className) {
		return new Logger(className);
	}
	
	/**
	 * CONSTRUCTOR
	 * @param className
	*/
	private Logger(String className) {
		super();
		this.className  = className;
	}
	
	/**
	 * LOG.debug : Message Only
	 * @param message
	*/
	public void debug(String message) {
		debug(message, null);
	}
	
	/**
	 * LOG.debug : Message plus Exception Stack Trace
	 * @param message
	 * @param t
	*/
	public void debug(String message, Throwable t) {
		LogManager.write(message, t, Level.DEBUG, this.className);
	}
	
	/**
	 * LOG.info : Message Only
	 * @param message
	*/
	public void info(String message) {
		info(message, null);
	}
	
	/**
	 * LOG.info : Message plus Exception Stack Trace
	 * @param message
	 * @param t
	*/
	public void info(String message, Throwable t) {
		LogManager.write(message, t, Level.INFO, this.className);
	}
	
	/**
	 * LOG.warn : Message Only
	 * @param message
	*/
	public void warn(String message) {
		warn(message, null);
	}
	
	/**
	 * LOG.warn : Message plus Exception Stack Trace
	 * @param message
	 * @param t
	*/
	public void warn(String message, Throwable t) {
		LogManager.write(message, t, Level.WARN, this.className);
	}
	
	/**
	 * LOG.error : Message Only
	 * @param message
	*/
	public void error(String message) {
		error(message, null);
	}
	
	/**
	 * LOG.error : Message plus Exception Stack Trace
	 * @param message
	 * @param t
	*/
	public void error(String message, Throwable t) {
		LogManager.write(message, t, Level.ERROR, this.className);
	}
	
	/**
	 * LOG.fatal : Message Only
	 * @param message
	*/
	public void fatal(String message) {
		fatal(message, null);
	}
	
	/**
	 * LOG.fatal : Message plus Exception Stack Trace
	 * @param message
	 * @param t
	*/
	public void fatal(String message, Throwable t) {
		LogManager.write(message, t, Level.FATAL, this.className);
	}
}
