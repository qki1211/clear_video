package io.quantumknight.video.util;
/********************************************************************************************
//* Filename: 		BinaryNumberUtil.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    UTILITY - CONVERT INTEGERS TO BINARY AND VICE-VERSA
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

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;


public abstract class BinaryNumberUtil {
	
	/**
	 * CONVERT BYTE ARRAY TO BOOLEAN
	 * @param array
	 * @return boolean
	 * @throws Exception
	*/
	public static boolean byteArrayToBoolean(byte[] array) throws Exception {
		int number = ByteBuffer.wrap(array).getInt();
		boolean rval = false;
		if ((number >= 0) || (number <= 1)) {
			rval = (number == 1 ? true : false);
		}
		else {
			throw new Exception("BOOLEAN VALUE MUST BE [0] or [1]");
		}
		return rval;
	}
	
	/**
	 * CONVERT BYTE ARRAY TO INT
	 * @param array
	 * @return int
	 * @throws Exception
	*/
	public static int byteArrayToInt(byte[] array) throws Exception {
		return ByteBuffer.wrap(array).getInt();
	}
	
	/**
	 * CONVERT INTEGER TO BYTE ARRAY - 32 BIT
	 * @param number
	 * @return
	 * @throws Exception
	 */
	public static byte[] intToByteArray32(int number) throws Exception {
		return intToByteArray(number, 4);
	}
	
	/**
	 * CONVERT INTEGER TO BYTE ARRAY - DEFINE BINARY SIZE
	 * @param number
	 * @param byteSize
	 * @return byte[] 
	 * @throws Exception
	*/
	public static byte[] intToByteArray(int number, int byteSize) throws Exception {
		byte[] rval = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(ByteBuffer.allocate(byteSize).putInt( number ).array());
		baos.flush();
		rval = baos.toByteArray();
		baos.reset();
		baos.close();
		return rval;
	}
}
