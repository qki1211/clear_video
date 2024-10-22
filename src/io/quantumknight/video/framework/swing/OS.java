package io.quantumknight.video.framework.swing;
/********************************************************************************************
//* Filename: 		OS.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    Java OS Detection - (Simple)
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

import static io.quantumknight.video.framework.swing.OSEnv._LINUX_COMMON_SHELL;
import static io.quantumknight.video.framework.swing.OSEnv._MACOS_SHELL;
import static io.quantumknight.video.framework.swing.OSEnv._OS_AIX;
import static io.quantumknight.video.framework.swing.OSEnv._OS_FREE_BSD;
import static io.quantumknight.video.framework.swing.OSEnv._OS_HP_UX;
import static io.quantumknight.video.framework.swing.OSEnv._OS_LINUX;
import static io.quantumknight.video.framework.swing.OSEnv._OS_MACOS;
import static io.quantumknight.video.framework.swing.OSEnv._OS_NET_BSD;
import static io.quantumknight.video.framework.swing.OSEnv._OS_OPEN_BSD;
import static io.quantumknight.video.framework.swing.OSEnv._OS_SOLARIS;
import static io.quantumknight.video.framework.swing.OSEnv._OS_SUNOS;
import static io.quantumknight.video.framework.swing.OSEnv._OS_WINDOWS;
import static io.quantumknight.video.framework.swing.OSEnv._UNIX_COMMON_COMMAND;
import static io.quantumknight.video.framework.swing.OSEnv._UNIX_COMMON_SHELL;
import static io.quantumknight.video.framework.swing.OSEnv._WINDOWS_COMMAND;
import static io.quantumknight.video.framework.swing.OSEnv._WINDOWS_COMMAND_ADMIN;
import static io.quantumknight.video.framework.swing.OSEnv._WINDOWS_SHELL;
import static io.quantumknight.video.framework.swing.OSEnv._WINDOWS_SHELL_ADMIN;
import static io.quantumknight.video.framework.swing.OSEnv._WINDOWS_VERB_ADMIN;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public abstract class OS {
	
	private static final OperatingSystem os = OperatingSystem.resolve();
	
	public enum OperatingSystem {
		
        WINDOWS	(_OS_WINDOWS),
        MAC 	(_OS_MACOS),
        LINUX   (_OS_LINUX), 
        OTHER 	(_OS_FREE_BSD, _OS_SUNOS, _OS_SOLARIS, _OS_FREE_BSD, _OS_OPEN_BSD, _OS_NET_BSD, _OS_AIX, _OS_HP_UX);
		
        /** Prefixes returned from the "os.name" system property. */
		private final Collection<String> prefixes;
        
        /**
         * ENUM CONSTRUCTOR : Creates an enum constant that will respond to the specified OS name prefixes.
         * @param prefixes Valid prefixes containing the operating system name
        */
        private OperatingSystem(final String... prefixes) {
        	this.prefixes = Collections.unmodifiableList(Arrays.stream(prefixes).collect(Collectors.toList()));
        }
        
        /**
         * ATOMIC SUBROUTINE - Resolve the name of the operating system.
         * @return  OperatingSystem - Resolved operating system based on "os.name" property
        */
        public static OperatingSystem resolve() {
        	final String prop = System.getProperty(OSEnv._JAVA_OS_NAME);
            return Arrays.stream(values()).filter(os -> os.prefixes.stream().anyMatch(p -> prop.startsWith(p))).findFirst().orElse(OTHER);
        }
    }
	
	/**
	 * PUBLIC ACCESSOR - IS THIS JVM RUNNING FROM A WINDOWS COMMAND PROMPT?
	 * @return boolean
	*/
	public static final boolean isWindowsCMDPrompt() {
		
		boolean rval = false;
		
		if (isWindowsOS()) {
			
			String windir = System.getenv(OSEnv._OS_WIN_COMMAND_PROMPT);
			if ((windir != null) && (System.console() == null)) {
				rval = true;
			}
		}
		
		return rval;
	}
	
	/**
	 * PUBLIC ACCESSOR - IS THIS JVM RUNNING ON WINDOWS OS?
	 * @return boolean
	*/
	public static final boolean isWindowsOS() {
		
		boolean rval = false;
		
		String osName = System.getProperty(OSEnv._JAVA_OS_NAME);
		if ((osName != null) && (osName.startsWith(OSEnv._OS_WINDOWS))) {
			rval = true;
		}
		
		return rval;
	}
	
	/**
	 * PUBLIC ACCESSOR - IS THIS JVM RUNNING ON WINDOWS OS?
	 * @return boolean
	*/
	public static final boolean isMacOS() {
		
		boolean rval = false;
		
		String osName = System.getProperty(OSEnv._JAVA_OS_NAME);
		if ((osName != null) && (osName.startsWith(OSEnv._OS_MACOS))) {
			rval = true;
		}
		
		return rval;
	}
	
	/**
	 * PUBLIC ACCESSOR - RETURN CURRENT DETECTED OS - Simple / Non-Native
	 * @return OperatingSystem
	*/
	public static final OperatingSystem getDetectedOSSimple() {
		
		return os;
	}
	
	/**
	 * PUBLIC ACCESSOR - Match OS to a command string to be executed by a newly spawned shell.
	 * ------------------------------------------------------------------------------------------
	 * @param command Command to convert
	 * @return String[] Shell command equivalent
	 * @throws Exception
	*/
	public static String[] toShellCommand(final String command) throws Exception {
		
		return toShellCommand(command, false);
	}
	
	/**
	 * PUBLIC ACCESSOR - Match OS to a command string to be executed by a newly spawned shell.
	 * ------------------------------------------------------------------------------------------
	 * @param command Command to convert
	 * @param prompt user for elevation if not already admin
	 * @return String[] Shell command equivalent
	 * @throws Exception
	*/
	public static String[] toShellCommand(final String command, boolean promptUser) throws Exception {
		
		String[] rval = null;
		
		switch (OS.getDetectedOSSimple()) {
		
			case WINDOWS: 			// WINDOWS OS
									if (promptUser) {
										rval = new String[] { 	_WINDOWS_SHELL_ADMIN,
																_WINDOWS_COMMAND_ADMIN,
																String.format(_WINDOWS_VERB_ADMIN, 
																command) };
																break;
									}
									else {
										rval = new String[] { 	_WINDOWS_SHELL,
																_WINDOWS_COMMAND,
																command };
																break;
									}
									
			case MAC:				// MACOS
									rval = new String[] { 		_MACOS_SHELL,
																_UNIX_COMMON_COMMAND,
																command };
																break;
									
			case LINUX: 			// LINUX OS 
									rval = new String[] { 		_LINUX_COMMON_SHELL,
																_UNIX_COMMON_COMMAND,
																command };
																break;
															
			case OTHER: 			// OTHER LINUX FLAVOR 
									rval = new String[] { 		_UNIX_COMMON_SHELL,
																_UNIX_COMMON_COMMAND,
																command };
																break;
															
			default: 				// UNKNOWN OS
									throw new IllegalStateException("Unsupported operating system " + System.getProperty(OSEnv._JAVA_OS_NAME));
		}
		
		return rval;
	}
}
