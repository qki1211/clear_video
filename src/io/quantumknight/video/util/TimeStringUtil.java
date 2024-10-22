package io.quantumknight.video.util;
/********************************************************************************************
//* Filename: 		TimeStringUtil.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    Common date and time string formatting utility
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

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.LocalDate;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import io.quantumknight.video.framework.constants.TimeDateConstants;


public abstract class TimeStringUtil {

	private static final int _PACIFIC_TIME_ZONE 					= -8; // (UTC-08:00) Pacific Time (US & Canada)
	
    private static final DateFormat _YM_SDF 						= new SimpleDateFormat(TimeDateConstants._YEAR_MONTH_FORMAT);
    private static final DateFormat _MM_SDF 						= new SimpleDateFormat(TimeDateConstants._MONTH_FORMAT);
    private static final DateFormat _MMMMM_SDF 						= new SimpleDateFormat(TimeDateConstants._MONTH_FULL_NAME_LONG_FORMAT);
    private static final DateFormat _DD_SDF							= new SimpleDateFormat(TimeDateConstants._DAY_FORMAT);
    private static final DateFormat _YYYY_SDF 						= new SimpleDateFormat(TimeDateConstants._YEAR_FORMAT);
    private static final SimpleDateFormat _SDF 						= new SimpleDateFormat(TimeDateConstants._US_DATE_FORMAT);
	
	/**
	 * Utility Function - Convert between two date format Strings
	 * @param dateStringInput
	 * @param inputFormat
	 * @param outputFormat
	 * @return String
	*/
	public static String convertBetweenDateFormats(String dateStringInput, String inputFormat, String outputFormat) {
		String rval = null;
		if ((dateStringInput != null) && (inputFormat != null) && (outputFormat != null)) {
			try {
				SimpleDateFormat input = new SimpleDateFormat(inputFormat);
				SimpleDateFormat output = new SimpleDateFormat(outputFormat);
				Date dateIN = input.parse(dateStringInput);
				rval = output.format(dateIN);
			}
			catch(Exception e) {
				// DIGEST
			}
		}
		return rval;
	}
	
	/**
	 * Utility Function - Convert Date Strings into Millis
	 * @param dateTimeString
	 * @param format
	 * @return long
	*/
	public static long dateStringToMillis(String dateTimeString, String format) {
		long rval = 0;
		if (dateTimeString != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				Date d = sdf.parse(dateTimeString);
				rval = d.getTime();
			}
			catch(Exception e) {
				// DIGEST
			}
		}
		return rval;
	}
	
	/**
	 * Utility Function - Convert Date Strings into Millis
	 * @param dateUSFormat
	 * @return long
	*/
	public static long dateStringToMillisUSFormat(String dateUSFormat) {
		return dateStringToMillis(dateUSFormat, TimeDateConstants._US_DATE_FORMAT);
	}
	
	/**
	 * Public Utility - Convert TS to String
	 * @param in
	 * @return String
	*/
	public static String toString(Timestamp in, String format) {
		String rval = null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			rval = sdf.format(in);
		}
		catch(Exception e) {
			rval = null;
		}
		return rval;
	}
	
	/**
	 * Public Utility - Convert number of seconds to "00:00:00" format String
	 * @param seconds
	 * @return String
	*/
	public static String secondsToTimeString(int seconds) {
        
		int hours = seconds / 3600;
        seconds = seconds % 3600;
        
        int minutes = seconds / 60;
        seconds = seconds % 60;
        
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}
	
	/**
	 * Utility Function - Convert milliseconds to Months!
	 * @param long Milliseconds
	 * @return long
	*/
	public static int millisecondsToMonths(long milliseconds) {
		int rval = 0;
		if (milliseconds > 0) {
			Calendar c = Calendar.getInstance(); 
			rval = c.get(Calendar.MONTH);
		}
		return rval;
	}
	
	/**
	 * Utility feature to consolidate instance of SimpleDateFormat in system
	 * Exposes SDF.Format Method()
	 * @param ts
	 * @return String
	*/
	public static String format(Timestamp ts) {
		return _SDF.format(ts);
	}
	
	/**
	 * Utility feature to consolidate instance of SimpleDateFormat in system
	 * Exposes SDF.Parse Method()
	 * @param dateString
	 * @return java.util.Date
	 * @throws ParseException
	*/
	public static Date parse(String dateString) throws ParseException {
		return _SDF.parse(dateString);
	}
	
	/**
	 * Utility feature to return the number of days between two dates
	 * @param Date d1
	 * @param Date d2
	 * @return int - number of days
	*/
	public static int daysBetween(Date d1, Date d2){
		return (int)((d2.getTime() - d1.getTime()) / TimeDateConstants._ONE_DAY);
	}
	
	/**
	 * Utility feature to return the number of days between two dates
	 * @param Timestamp d1
	 * @return Timestamp - Next Day
	*/
	public static Timestamp nextCalendarTimestamp(Timestamp t1){
		long next = t1.getTime() + TimeDateConstants._ONE_DAY;
		return new Timestamp(next);
	}
	
	/**
	 * Utility feature to return the time with 30 minutes added
	 * @param Timestamp t1
	 * @return Timestamp - Time after 30 minutes
	*/
	public static Timestamp addThirtyMinutesToTime(Timestamp t1){
		long next = t1.getTime() + TimeDateConstants._HALF_AN_HOUR;
		return new Timestamp(next);
	}

	/**
	 * Utility feature to return the time with 24 hours added
	 * @param Timestamp t1
	 * @return Timestamp - add one day to time
	*/
	public static Timestamp addOneDayToTime(Timestamp t1){
		long next = t1.getTime() + TimeDateConstants._ONE_DAY;
		return new Timestamp(next);
	}
	
	/**
	 * Utility for returning PST calibrated millisecond time from string
	 * Note: Granularity is [DAY]
	 * @param String date
	 * @return long
	*/
	public static long getPSTTimeMillis(Timestamp javaDate) {
		long returnTimeMillis = 0; // Method designed to digest a Parse Exception and return the number 0 on parse failure
		try {
			String[] pacificIDs = TimeZone.getAvailableIDs((int)(_PACIFIC_TIME_ZONE * TimeDateConstants._ONE_HOUR));
			SimpleTimeZone pst = new SimpleTimeZone((int)(_PACIFIC_TIME_ZONE * TimeDateConstants._ONE_HOUR), pacificIDs[0]);
			Calendar pstCalendar = new GregorianCalendar(pst);
			pstCalendar.setTime(javaDate);
			returnTimeMillis = pstCalendar.getTimeInMillis();
		}
		catch(Exception e) {
			// System.err.println("Could not parse date [" + date + "] - sorting failure in program");
		}
		return returnTimeMillis;
	}
	
	/**
	 * Utility Function - Parse [YEAR and QUARTER] to milliseconds
	 * @param fromYear
	 * @param fromQuarter
	 * @return LONG
	 * @throws Exception
	*/
	public static long getYearQuarterMillis(String fromYear, String fromQuarter) throws Exception {
		
		DateTimeFormatter formatter = DateTimeFormat.forPattern(TimeDateConstants._YEAR_FORMAT);
		DateTime year = formatter.parseDateTime(fromYear);

		switch(Integer.parseInt(fromQuarter)) {
			
			case 1:				// Q1 - DEFAULTS TO JANUARY FIRST - START OF DAY
								break;
								
			case 2: 			// Q2 - DEFAULTS TO APRIL FIRST - START OF DAY
								year.plusMonths(3);
								break;
								
			case 3:				// Q3 - DEFAULTS TO JULY FIRST - START OF DAY
								year.plusMonths(6);
								break;
								
			case 4: 			// Q4 - DEFAULTS TO OCTOBER FIRST - START OF DAY
								year.plusMonths(9);
								break;
								
			default:			// NO ADJUSTMENT TO QUARTER FILTER
								break;
		
		}
		return year.getMillis();
	}
	
	/**
	 * Utility Function - Parse [YEAR and QUARTER] to milliseconds
	 * @param fromYear
	 * @param fromQuarter
	 * @return LONG
	 * @throws Exception
	*/
	public static long getYearQuarterMillis(int fromYear, int fromQuarter) throws Exception {
		
		DateTime year = new DateTime(fromYear, (fromQuarter - 1) * 3 + 1, 1, 0, 0);

		return year.getMillis();
	}
	
	/**
	 * Utility Function - Get millisecond time for a period when adding NN number of calendar quarters
	 * @param startingTime
	 * @param quartersToAdd
	 * @return long
	 * @throws Exception
	*/
	public static long addCalendarQuartersToTime(Timestamp startingTime, int quartersToAdd) throws Exception {
		DateTime dateTime = new DateTime(startingTime);
		int monthsToAdd = (quartersToAdd * 3);
		DateTime later = dateTime.plusMonths(monthsToAdd);
		return later.getMillis();
	}
	
	/**
	 * Utility Function - Get millisecond time for a period when adding NN number of calendar months
	 * @param startingTime
	 * @param monthsToAdd
	 * @return long
	 * @throws Exception
	*/
	public static long addCalendarMonthsToTime(Timestamp startingTime, int monthsToAdd) throws Exception {
		DateTime dateTime = new DateTime(startingTime);
		DateTime later = dateTime.plusMonths(monthsToAdd);
		return later.getMillis();
	}

	/**
	 * Utility Function - Parse [TIMESTEMP] to Calendar Quarter
	 * @param timestamp
	 * @return int 
	 * @throws Exception
	*/
	public static int getCalendarQuarterFromTimestamp(Timestamp timestamp) throws Exception {
		int quarter = 0;
		LocalDate localDate = new LocalDate(timestamp);
		int month = localDate.getMonthOfYear();
		if (month <= 3) {
			quarter = 1;
		}
		else if ((month >= 4) && (month <= 6)) {
			quarter = 2;
		}
		else if ((month >= 7) && (month <= 9)) {
			quarter = 3;
		}
		else if ((month >= 10) && (month <= 12)) {
			quarter = 4;
		}
		else {
			quarter = -1;
		}
		return quarter;
	}
	
	/**
	 * Utility - Get YEAR from Timestamp
	 * @param timestamp
	 * @return int
	 * @throws Exception
	*/
	public static int getYearFromTimestamp(Timestamp timestamp) throws Exception {
		DateTime year = new DateTime(timestamp.getTime());
		return year.getYear();
	}
	
	/**
	 * Atomic Utility for returning PST calibrated time in a time formatted string
	 * @param systemTimeMillis
	 * @param timeFormat
	 * @return String
	*/
	public static String getPSTTimeString(long systemTimeMillis, String timeFormat) {
		String returnString = null;
		try {
			String[] pacificIDs = TimeZone.getAvailableIDs((int)(_PACIFIC_TIME_ZONE * TimeDateConstants._ONE_HOUR));
			SimpleTimeZone pst = new SimpleTimeZone((int)(_PACIFIC_TIME_ZONE * TimeDateConstants._ONE_HOUR), pacificIDs[0]);
			Calendar pstCalendar = new GregorianCalendar(pst);
			pstCalendar.setTimeInMillis(systemTimeMillis);
			SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
			returnString = sdf.format(pstCalendar.getTime());
		}
		catch(Exception e) {
			// e.printStackTrace();
		}
		return returnString;
	}
	
	/**
	 * Check if the same date
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Boolean isSameDate(Timestamp date1, Timestamp date2) {
		Boolean isSameDate = null;
		if(date1 == null && date2 != null) {
			isSameDate = Boolean.FALSE;
		} else if(date1 != null && date2 == null) {
			isSameDate = Boolean.FALSE;
		} else if(date1 != null && date2 != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TimeDateConstants.YEAR_MONTH_DATE_FORMAT);
			String date1String = simpleDateFormat.format(date1);
			if(date1String != null && date1String.trim().length() != 0 && date1String.equals(simpleDateFormat.format(date2))) {
				isSameDate = Boolean.TRUE;
			}
		}
		return isSameDate;
	}
	
	/**
	 * get time in date format
	 * @param dateString
	 * @return Timestamp
	 * @throws ParseException
	 */
	public static Timestamp getTimestamp(String dateString) throws ParseException {
		Date inputDate = new SimpleDateFormat(TimeDateConstants.YEAR_MONTH_DATE_FORMAT).parse(dateString);
		return new Timestamp(inputDate.getTime());
	}
	
	/**
	 * get time in date format
	 * @param dateString
	 * @return Timestamp
	 * @throws ParseException
	 */
	public static Timestamp getTimestamp(String dateString, String format) throws ParseException {
		Date inputDate = new SimpleDateFormat(format).parse(dateString);
		return new Timestamp(inputDate.getTime());
	}
	
	/**
	 * Derive a specific timestamp (for a timezone) with inputs MM/dd/YYYY and 4-digit 24-hour time. E.G; (1730)
	 * @param dateStringMMDDYYY
	 * @param hoursMinutes24
	 * @return Timestamp
	 * @throws ParseException
	*/
	public static Timestamp getTimestampFromDateTime(String dateStringMMDDYYY, String hoursMinutes24) throws ParseException {
		
		Timestamp timestamp = getTimestamp(dateStringMMDDYYY, TimeDateConstants._LEGACY_SQL_SERVER_DATE_PRECISION_STR);
		
		DateTime dt = new DateTime(timestamp);
    	
		if (hoursMinutes24.length() == 4) {
			int hours = Integer.parseInt(hoursMinutes24.substring(0,2));
			int mins = Integer.parseInt(hoursMinutes24.substring(2,4));
			dt = dt.withHourOfDay(hours).withMinuteOfHour(mins).withSecondOfMinute(0).withMillisOfSecond(0);
		}
		
		return new Timestamp(dt.getMillis());
	}
	
	
	/**
	 * get time in date format
	 * @param input
	 * @return Timestamp
	 * @throws ParseException
	 */
	public static Timestamp getDateInDateFormat(Date input) throws ParseException {
		Timestamp timestamp = null;
		if(input != null) {
			timestamp = getTimestampInDateFormat(new Timestamp(input.getTime()));
		}
		return timestamp;
	}

	/**
	 * get time in date format
	 * @param timestamp
	 * @return Timestamp
	 * @throws ParseException
	 */
	public static Timestamp getTimestampInDateFormat(Timestamp timestamp) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TimeDateConstants.YEAR_MONTH_DATE_FORMAT);
		String dateString = simpleDateFormat.format(timestamp);
		Date dateInDate = simpleDateFormat.parse(dateString);
		return new Timestamp(dateInDate.getTime());
	}

    /**
     * Utility - Get Current date (today date)
     *  
     * @return Date
     * @throws ParseException
     */
    public static Date getCurrentDate() throws ParseException {
        Date currentDate = _SDF.parse(_SDF.format(new Date()));

        return currentDate;
    }

    /**
     * Utility - Get next month timestamp from now (today date)
     * 
     * @return Timestamp
     */
    public static Timestamp getNextMonthTimeStampFromNow() {
        LocalDateTime now = LocalDateTime.now();
        now = now.plusMonths(1);

        Timestamp timestamp = Timestamp.valueOf(now);
        return timestamp;
    }

    /**
     * Utility - Get next month timestamp from now (today date)
     * 
     * @return Timestamp
     */
    public static Timestamp getCurrentTimestamp() {
        LocalDateTime now = LocalDateTime.now();

        Timestamp timestamp = Timestamp.valueOf(now);
        return timestamp;
    }

    /**
     * Utility feature to return the number of days between two dates
     * @param Timestamp t1
     * @param Timestamp t2
     * @return int - number of days
    */
    public static int daysBetween(Timestamp t1, Timestamp t2) {

        return (int) ((t1.getTime() - t2.getTime()) / TimeDateConstants._ONE_DAY);
    }

    /**
     * Utility feature to consolidate instance of SimpleDateFormat in system
     * Exposes _YM_SDF.format(ts) Method() - Format is yyyy-MM
     * TO-DO - MOVE THIS METHOD TO TimeStringUtil in LD_COMMON PROJECT
     * 
     * @param Timestamp
     * @return yearMonthString
    */
    public static String getYearMonthFromTimestamp(Timestamp ts) throws ParseException {
        String yearMonth = _YM_SDF.format(ts);
        return yearMonth;
    }
    
    /**
     * Utility feature to consolidate instance of SimpleDateFormat in system.
     * 
     * @param Timestamp
     * @return yearMonthString
    */
    public static String getMonth(Timestamp ts) throws ParseException {
        return _MM_SDF.format(ts);
    }
    
    /**
     * Utility feature to get current month of year
     * 
     * @param Timestamp
     * @return int
    */
    public static int getCurrentMonth() throws ParseException, NumberFormatException {
    	Timestamp ts = new Timestamp(System.currentTimeMillis());
        return Integer.valueOf(_MM_SDF.format(ts));
    }
    
    /**
     * Utility feature to consolidate instance of SimpleDateFormat in system.
     * 
     * @param Timestamp
     * @return yearMonthString
    */
    public static String getMonthFullNameFromTimestamp(Timestamp ts) throws ParseException {
        return _MMMMM_SDF.format(ts);
    }
    
    /**
     * Utility feature to consolidate instance of SimpleDateFormat in system.
     * 
     * @param Timestamp
     * @return yearMonthString
    */
    public static String getDayFromTimestamp(Timestamp ts) throws ParseException {
        return _DD_SDF.format(ts);
    }
    
    /**
     * Utility feature to consolidate instance of SimpleDateFormat in system.
     * 
     * @param Timestamp
     * @return yearMonthString
    */
    public static String getYearStringFromTimestamp(Timestamp ts) throws ParseException {
        return _YYYY_SDF.format(ts);
    }

    /**
     * Utility - Check whether the given timestamp is current month timestamp
     * @param Timestamp
     * @return boolean
    */
    public static boolean isCurrentMonth(Timestamp ts) {
        boolean isCurrenMonth = false;
        String thisMonth = YearMonth.now().toString();

        try {
            return getYearMonthFromTimestamp(ts).equals(thisMonth);
        }
        catch (ParseException e) {
            return isCurrenMonth;
        }
    }
    
	/**
	 * ATOMIC SUBROUTINE - Convenience method for converting given datetime into alternate timezone
	 * @param Timestamp dateTime
	 * @param TimeZone fromTimezone
	 * @param TimeZone toTimezone
	 * @return Timestamp
	 * @throws Exception
	*/
	public static Timestamp convertTimeZone(Timestamp dateTime, TimeZone fromTimezone, TimeZone toTimezone) throws Exception {
		
		// CONVERT THE FROM_TIME TO ZONEDDATETIME
		ZoneId fromZone = ZoneId.of(fromTimezone.getID());
		String dateString = TimeStringUtil.toString(dateTime, TimeDateConstants._ISO_8601_FORMAT);
		java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern(TimeDateConstants._ISO_8601_FORMAT);
		LocalDateTime localtDateAndTime = LocalDateTime.parse(dateString, formatter);
		ZonedDateTime fromDateTime = ZonedDateTime.of(localtDateAndTime, fromZone );

		// USE FROM_ZONEDDATETIME TO CONVERT IT TO TO_TOMEZONE
		ZonedDateTime toDateTime = fromDateTime.withZoneSameInstant(ZoneId.of(toTimezone.getID()));
		Timestamp toTime = Timestamp.valueOf(toDateTime.toLocalDateTime());
		
		return toTime;
	}

    /**
     * Utility - To get number of days in the current month   
     * @return long
     */
    public static long getLengthOfCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        long days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return days;
    }

    /**
     * Utility - To get day suffix to make it human readable date format
     * @return long
     */
    public static String getDateSuffix(int date) {

        if (date >= 11 && date <= 13) {
            return "th";
        }
        switch (date % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }
    
    /**
     * PUBLIC ACCESSOR - Create USA California Timezone formatted String
     * @param javaDate
     * @return String
     * @throws Exception
    */
    public static String getCaliforniaUSAFormattedDateString(Timestamp javaDate) throws Exception {
    	
    	long currentTimeMillis = getPSTTimeMillis(javaDate);
    	
    	StringBuffer sb = new StringBuffer();
    	sb.append(getMonthFullNameFromTimestamp(new Timestamp(currentTimeMillis)));
    	sb.append(" ");
    	
    	int date = Integer.valueOf(getDayFromTimestamp(new Timestamp(currentTimeMillis)));
    	String suffix = getDateSuffix(date);
    	sb.append(date + suffix);
    	sb.append(", ");
    	
    	sb.append(getYearFromTimestamp(new Timestamp(currentTimeMillis)));
    	
    	return sb.toString();
    }
    
    /**
     * PUBLIC ACCESSOR - Get a java.util.Date() object set [NN] seconds in the future - used for creating expiry TTL
     * @param ttlSeconds
     * @return Date
     * @throws Exception
    */
    public static Date getFutureTTLDateObjectSeconds(int ttlSeconds) throws Exception {
    	Date expiration = new Date();
		long expTimeMillis = ((expiration.getTime()) + (ttlSeconds * TimeDateConstants._ONE_SECOND));
		expiration.setTime(expTimeMillis);
		return expiration;
    }
    
    /**
     * PUBLIC ACCESSOR - MEASURE THE NUMBER OF HOURS BETWEEN START TIME AND END TIME
     * @param startTime
     * @param endTime
     * @param String timeZone
     * @return int
     * @throws Exception
    */
    public static int getHoursBetween(long startTime, long endTime, TimeZone timeZone) throws Exception {
    	
    	DateTimeZone tz = DateTimeZone.forTimeZone(timeZone); // TimeZone  	
//    	DateTimeZone tz = DateTimeZone.forID( timeZone ); // This is TimeZone Display.  E.G:  "America/Los_Angeles" or "Atlantic/Reykjavik"
    	
    	DateTime start = new DateTime( startTime, tz );
    	DateTime end = new DateTime( endTime, tz );
    	
    	Hours hoursObject = Hours.hoursBetween( start, end );
    	return hoursObject.getHours();
    }
    
    /**
     * PUBLIC ACCESSOR - MEASURE THE NUMBER OF DAYS BETWEEN START TIME AND END TIME
     * @param startTime
     * @param endTime
     * @param String timeZone
     * @return int
     * @throws Exception
    */
    public static int getDaysBetween(long startTime, long endTime, TimeZone timeZone) throws Exception {
    	
    	DateTimeZone tz = DateTimeZone.forTimeZone(timeZone); // TimeZone  	
    	
    	DateTime start = new DateTime( startTime, tz );
    	DateTime end = new DateTime( endTime, tz );
    	
    	Days daysObject = Days.daysBetween( start, end );
    	return daysObject.getDays();
    }
    
    /**
     * PUBLIC ACCESSOR - MEASURE THE NUMBER OF MONTHS BETWEEN START TIME AND END TIME
     * @param startTime
     * @param endTime
     * @param timeZone
     * @return
     * @throws Exception
    */
    public static int getMonthsBetween(long startTime, long endTime, TimeZone timeZone) throws Exception {
    	
    	DateTimeZone tz = DateTimeZone.forTimeZone(timeZone); // TimeZone  	
    	
    	DateTime start = new DateTime( startTime, tz );
    	DateTime end = new DateTime( endTime, tz );
    	
    	Months monthsObject = Months.monthsBetween( start, end );
    	return monthsObject.getMonths();
    }
    
    /**
     * PUBLIC ACCESSOR - MEASURE THE NUMBER OF MINUTES BETWEEN START TIME AND END TIME
     * @param startTime
     * @param endTime
     * @param String timeZone
     * @return int
     * @throws Exception
    */
    public static int getMinutesBetween(long startTime, long endTime, TimeZone timeZone) throws Exception {
    	
    	DateTimeZone tz = DateTimeZone.forTimeZone(timeZone); // TimeZone  	
//    	DateTimeZone tz = DateTimeZone.forID(timeZone);
    	
    	DateTime start = new DateTime( startTime, tz );
    	DateTime end = new DateTime( endTime, tz );
    	
    	Minutes minutesObject = Minutes.minutesBetween( start, end );
    	return minutesObject.getMinutes();
    }
    
    /**
     * PUBLIC ACCESSOR - PARSE DISPLAY NAME OF TIMEZONE FROM ISO 8601 Formatted Time String
     * @param timeString
     * @return TimeZone 
     * @throws Exception
    */
    public static TimeZone parseISO8601TimezoneID(String timeString) throws Exception {
    	String zone = timeString.substring(timeString.length()-6, timeString.length());
    	TimeZone tz = TimeZone.getTimeZone("GMT" + zone);
    	return tz;
    }
    
	/**
	 * ATOMIC SUBROUTINE - CURRENT YEAR
	 * @return String
	*/
	public static String getCurrentYear() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		return String.valueOf(year);
	}
		
	
	
	
	/** ** ** ** ** ** ** ** **   		[ 	FIRSTS 	 ]  	** ** ** ** ** ** ** ** ** ** ** ** ** ** **/
	
	
	/**
	 * PUBLIC ACCESSOR - GET FIRST MILLISECOND OF YEAR (LOCAL TIME ZONE ADJUSTED)
	 * @return long
	 * @throws Exception
	*/
	public static long getFirstMillisecondOfYear(long timestamp) throws Exception {
		
    	DateTime dt = new DateTime(timestamp, DateTimeZone.getDefault());
    	
		DateTime firstDayOfYear = dt.dayOfYear().withMinimumValue(); 					// DAY OF YEAR
		DateTime firstHourOfDay = firstDayOfYear.hourOfDay().withMinimumValue();
		DateTime firstMinuteOfHour = firstHourOfDay.minuteOfHour().withMinimumValue();
		DateTime firstSecondOfMinute = firstMinuteOfHour.secondOfMinute().withMinimumValue();
		DateTime firstMilliOfSecond = firstSecondOfMinute.millisOfSecond().withMinimumValue();
		
		return firstMilliOfSecond.getMillis();
	}
	
	/**
	 * PUBLIC ACCESSOR - GET FIRST MILLISECOND OF MONTH (LOCAL TIME ZONE ADJUSTED)
	 * @return long
	 * @throws Exception
	*/
	public static long getFirstMillisecondOfMonth(long timestamp) throws Exception {
		
    	DateTime dt = new DateTime(timestamp, DateTimeZone.getDefault());
    	
		DateTime firstDayOfMonth = dt.dayOfMonth().withMinimumValue(); 					// DAY OF MONTH
		DateTime firstHourOfDay = firstDayOfMonth.hourOfDay().withMinimumValue();
		DateTime firstMinuteOfHour = firstHourOfDay.minuteOfHour().withMinimumValue();
		DateTime firstSecondOfMinute = firstMinuteOfHour.secondOfMinute().withMinimumValue();
		DateTime firstMilliOfSecond = firstSecondOfMinute.millisOfSecond().withMinimumValue();
		
		return firstMilliOfSecond.getMillis();
	}
	
	/**
	 * PUBLIC ACCESSOR - GET FIRST MILLISECOND OF A DAY (LOCAL TIME ZONE ADJUSTED)
	 * @return long
	 * @throws Exception
	*/
	public static long getFirstMillisecondOfDay(long timestamp) throws Exception {
		
    	DateTime dt = new DateTime(timestamp, DateTimeZone.getDefault());
    	
		DateTime firstHourOfDay = dt.hourOfDay().withMinimumValue();					// HOUR OF DAY
		DateTime firstMinuteOfHour = firstHourOfDay.minuteOfHour().withMinimumValue();
		DateTime firstSecondOfMinute = firstMinuteOfHour.secondOfMinute().withMinimumValue();
		DateTime firstMilliOfSecond = firstSecondOfMinute.millisOfSecond().withMinimumValue();
		
		return firstMilliOfSecond.getMillis();
	}
	
	/**
	 * PUBLIC ACCESSOR - GET FIRST MILLISECOND OF CURRENT MONTH (LOCAL TIME ZONE ADJUSTED)
	 * @return long
	 * @throws Exception
	*/
	public static long getFirstMillisecondOfCurrentMonth() throws Exception {
		
		DateTime date = new DateTime(System.currentTimeMillis(), DateTimeZone.getDefault());

		return getFirstMillisecondOfMonth(date.getMillis());
	}
	
	/**
	 * PUBLIC ACCESSOR - GET FIRST MILLISECOND OF CURRENT YEAR (LOCAL TIME ZONE ADJUSTED)
	 * @return long
	 * @throws Exception
	*/
	public static long getFirstMillisecondOfCurrentYear() throws Exception {
		
		DateTime date = new DateTime(System.currentTimeMillis(), DateTimeZone.getDefault());

		return getFirstMillisecondOfYear(date.getMillis());
	}
	
	
	/** ** ** ** ** ** ** ** **   		[ 	LASTS 	 ]  	** ** ** ** ** ** ** ** ** ** ** ** ** ** **/
	
	/**
	 * PUBLIC ACCESSOR - GET LAST MILLISECOND OF A GIVEN TIMESTAMP
	 * @return long
	 * @throws Exception
	*/
	public static long getLastSecondDay(long timestamp) throws Exception {
		
    	DateTime dt = new DateTime(timestamp, DateTimeZone.getDefault());
    	
		DateTime lastHourOfDay = dt.hourOfDay().withMaximumValue();						// HOUR OF DAY
		DateTime lastMinuteOfHour = lastHourOfDay.minuteOfHour().withMaximumValue();
		DateTime lastSecondOfMinute = lastMinuteOfHour.secondOfMinute().withMaximumValue();
		
		return lastSecondOfMinute.getMillis();
	}
	
	/**
	 * PUBLIC ACCESSOR - GET THE LAST MILLISECOND OF A MONTH
	 * @param timestamp
	 * @param timeZone
	 * @return long
	 * @throws Exception
	*/
	public static long getLastMillisecondMonth(long timestamp, TimeZone timeZone) throws Exception {
		
		DateTimeZone tz = DateTimeZone.forTimeZone(timeZone); // TimeZone 
		
    	DateTime dt = new DateTime(timestamp, tz);
    	
		DateTime lastDayOfMonth = dt.dayOfMonth().withMaximumValue(); 					// DAY OF MONTH
		DateTime lastHourOfDay = lastDayOfMonth.hourOfDay().withMaximumValue();
		DateTime lastMinuteOfHour = lastHourOfDay.minuteOfHour().withMaximumValue();
		DateTime lastSecondOfMinute = lastMinuteOfHour.secondOfMinute().withMaximumValue();
		DateTime lastMilliOfSecond = lastSecondOfMinute.millisOfSecond().withMaximumValue();
		
		return lastMilliOfSecond.getMillis();
	}
	
	/**
	 * PUBLIC ACCESSOR - GET LAST MILLISECOND OF CURRENT MONTH (LOCAL TIME ZONE ADJUSTED)
	 * @return long
	 * @throws Exception
	*/
	public static long getLastMillisecondOfCurrentMonth() throws Exception {
		
		DateTime date = new DateTime(System.currentTimeMillis(), DateTimeZone.getDefault());

		return getLastMillisecondMonth(date.getMillis(), DateTimeZone.getDefault().toTimeZone());
	}
	
	/**
	 * PUBLIC ACCESSOR - GET THE LAST DAY OF A MONTH
	 * @param timestamp
	 * @param timeZone
	 * @return long
	 * @throws Exception
	*/
	public static int getLastDayMonth(long timestamp, TimeZone timeZone) throws Exception {
		
		DateTimeZone tz = DateTimeZone.forTimeZone(timeZone); // TimeZone 
		
    	DateTime dt = new DateTime(timestamp, tz);
    	
		DateTime lastDayOfMonth = dt.dayOfMonth().withMaximumValue(); 					// DAY OF MONTH
		
		return lastDayOfMonth.getDayOfMonth();
	}
	
	
	/**
	 * PUBLIC ACCESSOR - GET THE LAST DAY OF THE CURRENT MONTH
	 * @return int
	 * @throws Exception
	*/
	public static int getLastDayOfCurrentMonth() throws Exception {
		
		DateTime date = new DateTime(System.currentTimeMillis(), DateTimeZone.getDefault());

		return getLastDayMonth(date.getMillis(), DateTimeZone.getDefault().toTimeZone());
	}
	
	
	/** ** ** ** ** ** ** ** **   			[ 	NEXTS 	 ]  		** ** ** ** ** ** ** ** ** ** ** ** ** ** **/
	
	/**
	 * PUBLIC ACCESSOR - FIND NEXT MONDAY DATE WITH OPTION TO ADD PLUS WEEKS AND CUSTOM DT FORMAT
	 * @param plusWeeks
	 * @param format
	 * @return String
	 * @throws Exception
	*/
	public static String getNextMondayDate(int plusWeeks, String format) throws Exception {
		
		DateTime today = new DateTime(System.currentTimeMillis(), DateTimeZone.getDefault());
		DateTime monday = today.dayOfWeek().setCopy(1);
		
		DateTime rval = null;
		if (today.isBefore(monday)) {
			rval = today.plusWeeks(plusWeeks < 1 ? 0 : (plusWeeks - 1)).dayOfWeek().setCopy(1);
		}
		else {
			rval = today.plusWeeks(plusWeeks < 1 ? 1 : plusWeeks).dayOfWeek().setCopy(1);
		}
		
		return toString(new Timestamp(rval.getMillis()), format);
	}
	
	
	/** ** ** ** ** ** ** ** **   		[ 	VALIDATIONS 	 ]  	** ** ** ** ** ** ** ** ** ** ** ** ** ** **/
	
	/**
	 * PUBLIC ACCESSOR - GET THE DAY OF A MONTH FOR A TIMESTAMP
	 * @param timestamp
	 * @param timeZone
	 * @return boolean
	 * @throws Exception
	*/
	public static boolean isTimesInCurrentMonth(long timestamp, TimeZone timeZone) throws Exception {
		
		boolean rval = false;
		
		// COMPARE TIME
    	DateTimeZone tz1 = DateTimeZone.forTimeZone(timeZone); // TimeZone 
    	DateTime compare = new DateTime(timestamp, tz1);
		Calendar cal = Calendar.getInstance();
		cal.setTime(compare.toDate());
		
		int compareYear = cal.get(Calendar.YEAR);
		int compareMonth = cal.get(Calendar.MONTH);
		
		// CURRENT TIME
		DateTimeZone tz2 = DateTimeZone.forTimeZone(timeZone); // TimeZone 
    	DateTime current = new DateTime(System.currentTimeMillis(), tz2);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(current.toDate());
		
		int currentYear = cal2.get(Calendar.YEAR);
		int currentMonth = cal2.get(Calendar.MONTH);
		
		if ((compareYear == currentYear) && (compareMonth == currentMonth)) {
			rval = true;
		}
		
		return rval;
	}
	
	/**
	 * PUBLIC ACCESSOR - IS TODAY THE LAST DAY OF THE CURRENT MONTH
	 * @param timeZone
	 * @return boolean 
	 * @throws Exception
	*/
	public static boolean isTodayLastDayOfMonth(TimeZone timeZone) throws Exception {
		
		boolean rval = false;
		
		int todayDayOfMonth = getCurrentDayOfCurrentMonth(timeZone);
		int lastDayCurrentMonth = getLastDayOfCurrentMonth();
		
		if (todayDayOfMonth == lastDayCurrentMonth) {
			rval =true;
		}
		
		return rval;
	}
	
	/**
	 * PUBLIC ACCESSOR - GET THE DAY OF A MONTH FOR A TIMESTAMP
	 * @param timestamp
	 * @param timeZone
	 * @return int
	 * @throws Exception
	*/
	public static int getDayOfMonth(long timestamp, TimeZone timeZone) throws Exception {
		
    	DateTimeZone tz = DateTimeZone.forTimeZone(timeZone); // TimeZone 
    	DateTime date = new DateTime(timestamp, tz );
    	
		Calendar cal = Calendar.getInstance();
		cal.setTime(date.toDate());
		
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * PUBLIC ACCESSOR - GET THE DAY OF A WEEK FOR A TIMESTAMP
	 * ----------------------------------------------------------------------
	 * SUNDAY: 		1
	 * MONDAY: 		2
	 * TUESDAY: 	3
	 * WEDNESDAY: 	4
	 * THURSDAY: 	5
	 * FRIDAY: 		6
	 * SATURDAY: 	7
	 * ----------------------------------------------------------------------
	 * @param timestamp
	 * @param timeZone
	 * @return
	 * @throws Exception
	*/
	public static int getDayOfWeek(long timestamp, TimeZone timeZone) throws Exception {
		
		DateTimeZone tz = DateTimeZone.forTimeZone(timeZone); // TimeZone 
    	DateTime date = new DateTime(timestamp, tz );
    	
		Calendar cal = Calendar.getInstance();
		cal.setTime(date.toDate());
		
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	/**
	 * PUBLIC ACCESSOR - GET THE CURRENT DAY OF THE CURRENT MONTH
	 * @param timeZone
	 * @return int
	 * @throws Exception
	*/
	public static int getCurrentDayOfCurrentMonth(TimeZone timeZone) throws Exception {
		
    	DateTimeZone tz = DateTimeZone.forTimeZone(timeZone); // TimeZone 
    	DateTime date = new DateTime(System.currentTimeMillis(), tz );
    	
		Calendar cal = Calendar.getInstance();
		cal.setTime(date.toDate());
		
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * UNIT TEST - SELF EXECUTABLE
	 * @param args
	*/
	public static void main(String[] args) {
		
		try {
			
			// TEST FIRSTS AND LASTS
			String arbitraryDate = "07-15-2020";
			
			long t1 = getFirstMillisecondOfCurrentMonth();
			long t2 = getFirstMillisecondOfCurrentYear();
			long t3 = getLastMillisecondOfCurrentMonth();
			
			System.out.println("First Millisecond of Current Month: " + new Timestamp(t1));
			System.out.println("First Millisecond of Current Year: " + new Timestamp(t2));
			System.out.println("Last Millisecond of Current Month: " + new Timestamp(t3));
			System.out.println("Last DAY of Current Month: " + getLastDayOfCurrentMonth());
			
			long arbitraryDay = dateStringToMillisUSFormat(arbitraryDate);
			System.out.println("Arbitrary Date: " + new Timestamp(arbitraryDay));
			
			
			long t4 = getFirstMillisecondOfDay(arbitraryDay);
			System.out.println("First Millisecond of Arbitrary Date: " + new Timestamp(t4));
			
			long t5 = getLastSecondDay(arbitraryDay);
			System.out.println("Last second of Arbitrary Date: " + new Timestamp(t5));
			
			int t6 = getLastDayMonth(arbitraryDay, DateTimeZone.getDefault().toTimeZone());
			System.out.println("Last day of month for Arbitrary Date: " + t6);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
