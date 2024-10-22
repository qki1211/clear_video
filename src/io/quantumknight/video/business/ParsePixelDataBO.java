package io.quantumknight.video.business;
/********************************************************************************************
//* Filename: 		ParsePixelDataBO.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    BUSINESS OBJECT - PARSE PIXELS INTO 2D DISPLAY - JAVA BUFFERED IMAGE OBJECT
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

import java.awt.image.BufferedImage;
import java.util.Arrays;

import io.quantumknight.video.constants.ConstantsVideoApplication;
import io.quantumknight.video.framework.swing.BufferedImageSerializer;
import io.quantumknight.video.util.BinaryNumberUtil;

public abstract class ParsePixelDataBO {
	
	/**
	 * PARSE / RENDER DISPLAY VIDEO BYTES - CONVERT TO JAVA BUFFERED IMAGE
	 * @param rawVideoBytes
	 * @throws Exception
	*/
	public static BufferedImage convert(byte[] pixelDisplay2D) throws Exception {
		
		byte[] alphaBinary 	= Arrays.copyOfRange(pixelDisplay2D, 0, 4);
		byte[] widthBinary 	= Arrays.copyOfRange(pixelDisplay2D, 4, 8);
		byte[] heightBinary = Arrays.copyOfRange(pixelDisplay2D, 8, 12);
		byte[] pixels 		= Arrays.copyOfRange(pixelDisplay2D, 12, pixelDisplay2D.length);
		
		boolean hasAlphaChannel = BinaryNumberUtil.byteArrayToBoolean(alphaBinary);
		int width = BinaryNumberUtil.byteArrayToInt(widthBinary);
		int height = BinaryNumberUtil.byteArrayToInt(heightBinary);
		
		if (width >= ConstantsVideoApplication._MAXIMUM_WIDTH || width <= 0) {
			width = (int)ConstantsVideoApplication._VIDEO_RESOLUTION.getWidth();
		}
		if (height >= ConstantsVideoApplication._MAXIMUM_HEIGHT || height<= 0) {
			height = (int)ConstantsVideoApplication._VIDEO_RESOLUTION.getHeight();
		}
		
		// GENERATE BUFFERED IMAGE FROM RAW PIXEL DATA
		BufferedImage image = BufferedImageSerializer.createBufferedImageFromRawPixelData(pixels, width, height, hasAlphaChannel);
		
		return image;
	}
}
