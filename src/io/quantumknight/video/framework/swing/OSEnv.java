package io.quantumknight.video.framework.swing;
/********************************************************************************************
//* Filename: 		OSEnv.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    Unified variable for switching between UNIX / WINDOWS [ALL APPLICATIONS]
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

public abstract class OSEnv {
	
	public static final String _JAVA_OS_NAME						= "os.name";
	
	public static final String _OS_WINDOWS 							= "Windows";
	public static final String _OS_WIN_COMMAND_PROMPT				= "windir";
	
	public static final String _OS_MACOS 							= "Mac";
	public static final String _OS_LINUX 							= "Linux";
	public static final String _OS_SUNOS 							= "SunOS";
	public static final String _OS_SOLARIS 							= "Solaris";
	public static final String _OS_FREE_BSD 						= "FreeBSD";
	public static final String _OS_OPEN_BSD 						= "OpenBSD";
	public static final String _OS_NET_BSD 							= "NetBSD";
	public static final String _OS_AIX 								= "AIX";
	public static final String _OS_HP_UX 							= "HP-UX";
	
	public static final String _WINDOWS_SHELL 						= "cmd.exe";
	public static final String _WINDOWS_COMMAND 					= "/c";
	
	public static final String _WINDOWS_SHELL_ADMIN 				= "powershell";
	public static final String _WINDOWS_COMMAND_ADMIN 				= "-Command";
	public static final String _WINDOWS_VERB_ADMIN 					= "Start-Process cmd.exe -WindowStyle hidden -Verb RunAs -ArgumentList '/c, \"%s\"'";
	
	public static final String _MACOS_SHELL 						= "/bin/bash";
	public static final String _LINUX_COMMON_SHELL 					= "bash";
	public static final String _UNIX_COMMON_SHELL 					= "/bin/sh";
	public static final String _UNIX_COMMON_COMMAND 				= "-c";
	
}
