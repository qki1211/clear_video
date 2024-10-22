package io.quantumknight.common.swing.webcam.components;
/********************************************************************************************
//* Filename: 		WebcamUtils.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    WRAPPER IMPLEMENTATION - SARXOS WEBCAM UTILS
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

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import io.quantumknight.common.swing.webcam.components.utils.ImageUtils;

public class WebcamUtils {

	public static final void capture(Webcam webcam, File file) {
		if (!webcam.isOpen()) {
			webcam.open();
		}
		try {
			ImageIO.write(webcam.getImage(), ImageUtils.FORMAT_JPG, file);
		} catch (IOException e) {
			throw new WebcamException(e);
		}
	}

	public static final void capture(Webcam webcam, File file, String format) {
		if (!webcam.isOpen()) {
			webcam.open();
		}
		try {
			ImageIO.write(webcam.getImage(), format, file);
		} catch (IOException e) {
			throw new WebcamException(e);
		}
	}

	public static final void capture(Webcam webcam, String filename) {
		if (!filename.endsWith(".jpg")) {
			filename = filename + ".jpg";
		}
		capture(webcam, new File(filename));
	}

	public static final void capture(Webcam webcam, String filename, String format) {
		String ext = "." + format.toLowerCase();
		if (!filename.endsWith(ext)) {
			filename = filename + ext;
		}
		capture(webcam, new File(filename), format);
	}

	public static final byte[] getImageBytes(Webcam webcam, String format) {
		return ImageUtils.toByteArray(webcam.getImage(), format);
	}

	/**
	 * Capture image as BYteBuffer.
	 *
	 * @param webcam the webcam from which image should be obtained
	 * @param format the file format
	 * @return Byte buffer
	 */
	public static final ByteBuffer getImageByteBuffer(Webcam webcam, String format) {
		return ByteBuffer.wrap(getImageBytes(webcam, format));
	}

	/**
	 * Get resource bundle for specific class.
	 *
	 * @param clazz the class for which resource bundle should be found
	 * @param locale the {@link Locale} object
	 * @return Resource bundle
	 */
	public static final ResourceBundle loadRB(Class<?> clazz, Locale locale) {
		String pkg = WebcamUtils.class.getPackage().getName().replaceAll("\\.", "/");
		return PropertyResourceBundle.getBundle(String.format("%s/i18n/%s", pkg, clazz.getSimpleName()));
	}
}
