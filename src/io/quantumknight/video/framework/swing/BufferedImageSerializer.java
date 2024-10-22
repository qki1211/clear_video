package io.quantumknight.video.framework.swing;
/********************************************************************************************
//* Filename: 		BufferedImageSerializer.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    UTILITY - SERIALIZE / DE-SERIALIZE IMAGE DATA
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

public abstract class BufferedImageSerializer {

	/**
	 * CONVERT 2D PIXEL ARRAY TO RGB BUFFERED IMAGE
	 * @param pixels
	 * @return BufferedImage
	 * @throws Exception
	*/
	public static BufferedImage getBufferedImageFromPixels(int[][] pixels) throws Exception {
		int width = pixels[0].length;
		int height = pixels.length;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int row = 0; row < height; row++) {
			image.setRGB(0, row, width, 1, pixels[row], 0, width);
		}
		return image;
	}
	
	/**
	 * CONVERT 2D PIXEL COMPONENTS (RAW) TO BUFFERED IMAGE OBJECT
	 * @param pixels
	 * @param width
	 * @param height
	 * @param hasAlphaChannel
	 * @return BufferedImage
	 * @throws Exception
	*/
	public static BufferedImage createBufferedImageFromRawPixelData(byte[] pixels, int width, int height, boolean hasAlphaChannel) throws Exception {
		
		int[][] result = new int[height][width];
		final int pixelLength = hasAlphaChannel ? 4 : 3;
		if (hasAlphaChannel) {
			try {
				for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
					int argb = 0;
					argb += (((int) pixels[pixel] & 0xff) << 24); 		// alpha
					argb += ((int) pixels[pixel + 1] & 0xff); 			// blue
					argb += (((int) pixels[pixel + 2] & 0xff) << 8); 	// green
					argb += (((int) pixels[pixel + 3] & 0xff) << 16); 	// red
					result[row][col] = argb;
					col++;
					if (col == width) {
						col = 0;
						row++;
					}
				}
			}
			catch(ArrayIndexOutOfBoundsException aioobe) {
				// GENERALLY THE RESULT OF ENCRYPTION / DECRYPTION MISMATCH
			}
			
		} 
		else {
			try {
				for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
					int argb = 0;
					argb += -16777216; 									// 255 alpha
					argb += ((int) pixels[pixel + 2] & 0xff); 			// blue
					argb += (((int) pixels[pixel + 1] & 0xff) << 8); 	// green
					argb += (((int) pixels[pixel] & 0xff) << 16); 		// red
					result[row][col] = argb;
					col++;
					if (col == width) {
						col = 0;
						row++;
					}
				}
			}
			catch(ArrayIndexOutOfBoundsException aioobe) {
				// GENERALLY THE RESULT OF ENCRYPTION / DECRYPTION MISMATCH
			}
		}
        
		BufferedImage image = getBufferedImageFromPixels(result);

        return image;
	}

	/**
	 * CONVERT BUFFERED IMAGE TO 2D PIXEL ARRAY
	 * @param image
	 * @return int[][]
	*/
	public static int[][] convertTo2DPixelDisplay(BufferedImage image) {

		final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		final int width = image.getWidth();
		final int height = image.getHeight();
		final boolean hasAlphaChannel = image.getAlphaRaster() != null;

		int[][] result = new int[height][width];
		if (hasAlphaChannel) {
			final int pixelLength = 4;
			for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
				int argb = 0;
				argb += (((int) pixels[pixel] & 0xff) << 24); 		// alpha
				argb += ((int) pixels[pixel + 1] & 0xff); 			// blue
				argb += (((int) pixels[pixel + 2] & 0xff) << 8); 	// green
				argb += (((int) pixels[pixel + 3] & 0xff) << 16); 	// red
				result[row][col] = argb;
				col++;
				if (col == width) {
					col = 0;
					row++;
				}
			}
		} 
		else {
			final int pixelLength = 3;
			for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
				int argb = 0;
				argb += -16777216; 									// 255 alpha
				argb += ((int) pixels[pixel + 2] & 0xff); 			// blue
				argb += (((int) pixels[pixel + 1] & 0xff) << 8); 	// green
				argb += (((int) pixels[pixel] & 0xff) << 16); 		// red
				result[row][col] = argb;
				col++;
				if (col == width) {
					col = 0;
					row++;
				}
			}
		}
		return result;
	}
}
