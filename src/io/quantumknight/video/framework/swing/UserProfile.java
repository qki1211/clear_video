package io.quantumknight.video.framework.swing;
/********************************************************************************************
//* Filename: 		UserProfile.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:	JFC/SWING FRAMEWORK - USER PROFILE INTERFACE - MULTI APPLICATION
//* 
//* 				Designed to be extensible, this interface allows for multiple
//* 				applications to maintain different types of user profile objects
//* 				while still using the core LD Authentication and multi-threading
//* 				functionality.
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

public interface UserProfile extends java.io.Serializable {

	/**
	 * @return
	*/
	public int getAddressID();
	
	/**
	 * @return
	*/
	public boolean isAuthenticated();
	
	/**
	 * @return
	*/
	public String getEmail();
	
	/**
	 * @return
	*/
	public String getFirstName();
	
	/**
	 * @return
	*/
	public String getHttpSessionID();
	
	/**
	 * @return
	*/
	public String getIPAddress();
	
	/**
	 * @return
	*/
	public String getLanguagePref();
	
	/**
	 * @return
	*/
	public String getLastName();
	
	/**
	 * @return
	*/
	public String getPassword();
	
	/**
	 * @return
	*/
	public int getSecurityRoleID();
	
	/**
	 * @return
	*/
	public String getUserID();
	
	/**
	 * @param i
	*/
	public void setAddressID(int i);
	
	/**
	 * @param b
	*/
	public void setAuthenticated(boolean b);
	
	/**
	 * @param string
	*/
	public void setEmail(String string);
	
	/**
	 * @param string
	*/
	public void setFirstName(String string);
	
	/**
	 * @param string
	*/
	public void setHttpSessionID(String string);
	
	/**
	 * @param string
	*/
	public void setIPAddress(String string);
	
	/**
	 * @param string
	*/
	public void setLanguagePref(String string);
	
	/**
	 * @param string
	*/
	public void setLastName(String string);
	
	/**
	 * @param string
	*/
	public void setPassword(String string);
	
	/**
	 * @param i
	*/
	public void setSecurityRoleID(int i);
	
	/**
	 * @param string
	*/
	public void setUserID(String string);
	
	/**
	 * @return
	 */
	public String getApplicationID();
	
	/**
	 * @param string
	 */
	public void setApplicationID(String string);
	
	/**
	 * @return the userNameEmail
	 */
	public String getUserNameEmail();
	
	/**
	 * @param userNameEmail the userNameEmail to set
	 */
	public void setUserNameEmail(String userNameEmail);
	
}
