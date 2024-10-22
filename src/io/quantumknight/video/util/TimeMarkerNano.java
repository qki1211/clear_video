package io.quantumknight.video.util;
/********************************************************************************************
//* Filename: 		TimeMarkerNano.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    Stopwatch utility feature - Legacy 2001
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

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class TimeMarkerNano {

	private HashMap<Integer, Long> nanotime;
	
	/**
	 * Constructor - default
	*/
	public TimeMarkerNano() {
		super();
		this.nanotime = new HashMap<Integer, Long>();
	}
	
	/**
	 * Internal method for indexing Timestamps
	 * ----------------------------------------------------
	 * Note about Java Nano Time:
	 * ----------------------------------------------------
	 * System.nanoTime() returns the current value of the running Java Virtual Machine's 
	 * high-resolution time source, in nanoseconds.
	 * 
	 * This method can only be used to measure elapsed time and is not related to any other 
	 * notion of system or wall-clock time. The value returned represents nanoseconds since some fixed 
	 * but arbitrary origin time (perhaps in the future, so values may be negative). 
	 * 
	 * The same origin is used by all invocations of this method in an instance of a Java virtual machine; 
	 * other virtual machine instances are likely to use a different origin.
	 * 
	 * This method provides nanosecond precision, but not necessarily nanosecond resolution 
	 * (that is, how frequently the value changes) - no guarantees are made except that the resolution 
	 * is at least as good as that of currentTimeMillis().
	 * 
	 * Differences in successive calls that span greater than approximately 292 years (263 nanoseconds) 
	 * will not correctly compute elapsed time due to numerical overflow.
	 * 
	 * The values returned by this method become meaningful only when the difference between two such values, 
	 * obtained within the same instance of a Java virtual machine, is computed.
	 * 
	 * For example, to measure how long some code takes to execute:
	 * 
	 *  long startTime = System.nanoTime();
	 *   // ... the code being measured ...
	 *   long elapsedNanos = System.nanoTime() - startTime;
	 *   To compare elapsed time against a timeout, use
	 *   if (System.nanoTime() - startTime >= timeoutNanos) ...
	 *   instead of
	 *    if (System.nanoTime() >= startTime + timeoutNanos) ...
	 *    because of the possibility of numerical overflow.
	 *    Returns:
	 *    the current value of the running Java Virtual Machine's high-resolution time source, in nanoseconds
	 *    Since:  1.5
	 * ----------------------------------------------------
	 * @param int index
	 * @return void
	*/
	public void setTimeMark(int index) {
		this.nanotime.put(index, System.nanoTime());
	}
	
	/**
	 * GET THE SECOND(S) TIME PRECISION BETWEEN START AND STOP POINTS
	 * ----------------------------------------------------
	 * @param index1
	 * @param index2
	 * @return long
	*/
	public long compareTimeSec(int index1, int index2) {
		long result = -1;
		long delayNS = this.compareTimeNano(index1, index2);
		if (delayNS > 0) {
			result = TimeUnit.SECONDS.convert(delayNS, TimeUnit.NANOSECONDS);
		}
		return result;
	}
	
	
	/**
	 * GET THE MILLISECOND TIME PRECISION BETWEEN START AND STOP POINTS
	 * ----------------------------------------------------
	 * @param index1
	 * @param index2
	 * @return long
	*/
	public long compareTimeMilli(int index1, int index2) {
		long result = -1;
		long delayNS = this.compareTimeNano(index1, index2);
		if (delayNS > 0) {
			result = TimeUnit.MILLISECONDS.convert(delayNS, TimeUnit.NANOSECONDS);
		}
		return result;
	}
	
	/**
	 * GET THE NANO-SECOND TIME PRECISION BETWEEN START AND STOP POINTS
	 * ----------------------------------------------------
	 * @param index1
	 * @param index2
	 * @return long
	*/
	public long compareTimeNano(int index1, int index2) {
		long result = -1;
		if ((this.nanotime.containsKey(index1)) && (this.nanotime.containsKey(index2))) {
			
			long start = this.nanotime.get(index1);
			long stop  = this.nanotime.get(index2);
			
			result = (long)stop - start;
		}
		return result;
	}
	
	/**
	 * Internal method for comparing timestamps that returns
	 * a well-formatted String in DAYS - HOURS - MINS - SECS
	 * 
	 * USAGE: (index1 - index2)
	 * ----------------------------------------------------
	 * @param int index1
	 * @param int index2
	 * @return int
	*/
	public String compareTimeFormattedString(int index1, int index2) {
	
		@SuppressWarnings("unused")
		String returnString = null;
		
		long timeMillis = this.compareTimeMilli(index1, index2);
		int timeSec = 0;
		int timeMin = 0;
		int xtraSec = 0;
		int timeHrs = 0;
		int xtraMin = 0;
		int timeDay = 0;
		int xtraHrs = 0;
	
		boolean seconds = false;
		boolean minutes = false;
		boolean hours   = false;
		boolean days    = false;
		
		if (timeMillis > 1000) {
			timeSec = (int)timeMillis/1000;
			seconds = true;
		}
		if (timeSec > 60) {
			timeMin = timeSec/60;
			xtraSec = timeSec%60;
			minutes = true;
		}
		if (timeMin > 60) {
			timeHrs = timeMin/60;
			xtraMin = timeMin%60;
			hours = true;
		}	
		if (timeHrs > 24) {
			timeDay = timeHrs/24;
			xtraHrs = timeHrs%24;
			days = true;
		}
		
		if (days) {
			return (timeDay + " day(s), " + xtraHrs + " hour(s), " + xtraMin + " minutes(s), " + xtraSec + " second(s)");
		}
		else if (hours) {
			return (timeHrs + " hour(s), " + xtraMin + " minutes(s), " + xtraSec + " second(s)");
		}
		else if (minutes) {
			return (timeMin + " minutes(s), " + xtraSec + " second(s)");
		}
		else if (seconds) {
			return (timeSec + " second(s)");
		}
		else {
			return (timeMillis + " milliseconds(s)");
		}
	}
}
