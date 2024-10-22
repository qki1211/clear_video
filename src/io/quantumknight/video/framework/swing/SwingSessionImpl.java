package io.quantumknight.video.framework.swing;
/********************************************************************************************
//* Filename: 		SwingSessionImpl.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:	LD JFC/SWING FRAMEWORK - SWING USER SESSION OBJECT - APPLICATION CTXT
//* 
//* 				THIS SESSION PATTERN MIMICS THE SERVLET HTTP-SESSION OBJECT (K/V PAIRS)
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
import java.util.HashMap;

public abstract class SwingSessionImpl implements SwingSession {

	private HashMap<String,Object> attributes;
	private long creationTime;
	private long lastupdated;
	private String uniqueSessionID;
	private int maxInactiveInterval = 30; // minutes
	private boolean valid;
	
	/**
	 * Default Constructor
	 * @param obj
	*/
	public SwingSessionImpl() {
		super();
		this.attributes = new HashMap<String,Object>();
		this.valid = true;
		this.lastupdated = System.currentTimeMillis();
		this.creationTime = this.lastupdated;
		this.uniqueSessionID = new String("JSESSIONID_" + Math.random());
	}
	
	/**
	 * Returns the object bound with the specified name in this session, or null if no object is bound under the name.
	 * @param String name
	 * @return Object
	*/
	public Object getAttribute(String name) {
		this.lastupdated = System.currentTimeMillis();
		Object rval = null;
		if (this.attributes.containsKey(name)) {
			rval = this.attributes.get(name);
		}
		return rval;
	}
	
	/**
	 * Returns an Enumeration of String objects containing the names of all the objects bound to this session.
	 * @return Enumeration
	*/
	public Collection<String> getAttributeNames() {
		this.lastupdated = System.currentTimeMillis();
		Collection<String> names = this.attributes.keySet();
		return names;
	}
	
	/**
	 * Returns the time when this session was created, measured in milliseconds since midnight January 1, 1970 GMT. 
	 * @return long
	*/
	public long getCreationTime() {
		return this.creationTime;
	}
	
	/**
	 * Returns a string containing the unique identifier assigned to this session.
	 * @return String
	*/
	public String getId() {
		return this.uniqueSessionID;
	}
	
	/**
	 * Returns the last time the client sent a request associated with this session, as the number of milliseconds since midnight January 1, 1970 GMT. 
	 * @return long
	*/
	public long getLastAccessedTime() {
		return this.lastupdated;
	}
	
	/**
	 * Returns the maximum time interval, in seconds, that the servlet container will keep this session open between client accesses. 
	 * @return int
	*/
	public int getMaxInactiveInterval() {
		return this.maxInactiveInterval;
	}
	
	/**
	 * Invalidates this session and unbinds any objects bound to it.
	*/
	public synchronized void invalidate() {
		this.valid = false;
	}
	
	/**
	 * Check valid session
	*/
	public boolean isValid() {
		return this.valid;
	}
	
	/**
	 * Removes the object bound with the specified name from this session.
	 * @param String name
	*/
	public synchronized void removeAttribute(String name) {
		this.lastupdated = System.currentTimeMillis();
		if (this.attributes.containsKey(name)) {
			this.attributes.remove(name);
		}
	}
	
	/**
	 * Binds an object to this session, using the name specified. 
	 * @param String name
	 * @param Object value
	*/
	public synchronized void setAttribute(String name, Object value) {
		this.lastupdated = System.currentTimeMillis();
		if (this.attributes.containsKey(name)) {
			this.attributes.remove(name);
		}
		this.attributes.put(name, value);
	}
	
	/**
	 * Specifies the time, in seconds, between client requests before the swing container will invalidate this session. 
	 * @param int interval
	*/
	public synchronized void setMaxInactiveInterval(int interval) {
		this.lastupdated = System.currentTimeMillis();
		this.maxInactiveInterval = interval;
	}
}
