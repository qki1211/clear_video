package io.quantumknight.common.swing.webcam.components.utils;
/********************************************************************************************
//* Filename: 		ImageUtils.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    WRAPPER IMPLEMENTATION - SARXOS WEBCAM IMAGE UTILS
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
import java.awt.image.DataBufferByte;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import io.quantumknight.common.swing.webcam.components.WebcamException;


public class ImageUtils {

	/**
	 * Graphics Interchange Format.
	 */
	public static final String FORMAT_GIF = "GIF";

	/**
	 * Portable Network Graphic format.
	 */
	public static final String FORMAT_PNG = "PNG";

	/**
	 * Joint Photographic Experts Group format.
	 */
	public static final String FORMAT_JPG = "JPG";

	/**
	 * Bitmap image format.
	 */
	public static final String FORMAT_BMP = "BMP";

	/**
	 * Wireless Application Protocol Bitmap image format.
	 */
	public static final String FORMAT_WBMP = "WBMP";

	/**
	 * Convert {@link BufferedImage} to byte array.
	 * 
	 * @param image the image to be converted
	 * @param format the output image format
	 * @return New array of bytes
	 */
	public static byte[] toByteArray(BufferedImage image, String format) {

		byte[] bytes = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			ImageIO.write(image, format, baos);
			bytes = baos.toByteArray();
		} catch (IOException e) {
			throw new WebcamException(e);
		} finally {
			try {
				baos.close();
			} catch (IOException e) {
				throw new WebcamException(e);
			}
		}

		return bytes;
	}

	public static BufferedImage readFromResource(String resource) {
		InputStream is = null;
		try {
			return ImageIO.read(is = ImageUtils.class.getClassLoader().getResourceAsStream(resource));
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					throw new IllegalStateException(e);
				}
			}
		}
	}

	public static BufferedImage createEmptyImage(final BufferedImage source) {
		return new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_RGB);
	}

	/**
	 * Clamp a value to the range 0..255
	 */
	public static int clamp(int c) {
		if (c < 0) {
			return 0;
		}
		if (c > 255) {
			return 255;
		}
		return c;
	}

	/**
	 * Return image raster as bytes array.
	 *
	 * @param bi the {@link BufferedImage}
	 * @return The raster data as byte array
	 */
	public static byte[] imageToBytes(BufferedImage bi) {
		return ((DataBufferByte) bi.getData().getDataBuffer()).getData();
	}
}
