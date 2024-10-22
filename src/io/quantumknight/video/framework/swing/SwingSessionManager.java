package io.quantumknight.video.framework.swing;
/********************************************************************************************
//* Filename: 		SwingSessionManager.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    JFC/SWING FRAMEWORK - CONTAINER FOR SWING SESSIONS - APP CONTEXT
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

import java.util.Collection;

public class SwingSessionManager {

	private SwingSession userSession = null;
	
	/**
	 * Default Constructor
	*/
	public SwingSessionManager() {
		super();
	}
	
	/**
	 * Return an existing session / Create a new session -
	 * Note: This will only allow for ONE session per swing client
	 * @param String singleUserIPAddress
	 * @return SwingSession
	*/
	public SwingSession getSession() {
	
		if (this.userSession == null) {
			this.userSession = new NewSwingSession();
		}
		return this.userSession;
	}
	
	/**
	 * Return a list of all sessions in map
	 * @return 
	*/
	public Collection<String> getSessionNames() {
		return null;
	}
	
	/**
	 * Remove stale sessions - called by Maintenance Threads
	 * @param sessionID
	*/
	public synchronized void removeSession(String sessionID) {
		/**
		if (this.sessionObjects.containsKey(sessionID)) {
			this.sessionObjects.remove(sessionID);
		}
		**/
	}
	
	/**
	 * Inner Class - Create a new Swing Session
	*/
	private class NewSwingSession extends SwingSessionImpl {
		/**
		 * Inner Constructor
		*/
		private NewSwingSession() {
			super();
		}
	}
}
