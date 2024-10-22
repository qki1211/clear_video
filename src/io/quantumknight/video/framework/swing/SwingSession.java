package io.quantumknight.video.framework.swing;
/********************************************************************************************
//* Filename: 		SwingSession.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:		LD JFC/SWING FRAMEWORK - SWING USER SESSION OBJECT - APPLICATION CTXT
//* 
//* 					THIS SESSION PATTERN MIMICS THE SERVLET HTTP-SESSION OBJECT (K/V PAIRS)
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

public interface SwingSession {

	/**
	 * Returns the object bound with the specified name in this session, or null if no object is bound under the name.
	 * @param String name
	 * @return Object
	*/
	public Object getAttribute(String name);
	
	/**
	 * Returns an Enumeration of String objects containing the names of all the objects bound to this session.
	 * @return Enumeration
	*/
	public Collection<String> getAttributeNames();
	
	/**
	 * Returns the time when this session was created, measured in milliseconds since midnight January 1, 1970 GMT. 
	 * @return long
	*/
	public long getCreationTime();
	
	/**
	 * Returns a string containing the unique identifier assigned to this session.
	 * @return String
	*/
	public String getId();
	
	/**
	 * Returns the last time the client sent a request associated with this session, as the number of milliseconds since midnight January 1, 1970 GMT. 
	 * @return long
	*/
	public long getLastAccessedTime();
	
	/**
	 * Returns the maximum time interval, in seconds, that the servlet container will keep this session open between client accesses. 
	 * @return int
	*/
	public int getMaxInactiveInterval();
	
	/**
	 * Invalidates this session and unbinds any objects bound to it.
	*/
	public void invalidate();
	
	/**
	 * Check valid session
	*/
	public boolean isValid();
	
	/**
	 * Removes the object bound with the specified name from this session.
	 * @param String name
	*/
	public void removeAttribute(String name);
	
	/**
	 * Binds an object to this session, using the name specified. 
	 * @param String name
	 * @param Object value
	*/
	public void setAttribute(String name, Object value);
	
	/**
	 * Specifies the time, in seconds, between client requests before the swing container will invalidate this session. 
	 * @param int interval
	*/
	public void setMaxInactiveInterval(int interval);
	
}

