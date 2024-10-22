package io.quantumknight.video.business;
/********************************************************************************************
//* Filename: 		FetchPixelDataBO.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    BUSINESS OBJECT - GRAB PIXEL DATA FROM SARXOS WEBCAM INTERFACE
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

import com.github.sarxos.webcam.Webcam;

import io.quantumknight.video.state.VideoDemoSettings;
import io.quantumknight.video.util.BinaryNumberUtil;

public abstract class FetchPixelDataBO {
	
	/**
	 * CONVERT A FRAME OF PIXEL DATA TO RGB PIXEL ARRAY
	 * -----------------------------------------------------------------------------
	 * @return void
	 * @throws Exception
	*/
	public static byte[] getNextPixelData() throws Exception {
		
		VideoDemoSettings xv = VideoDemoSettings.getInstance();
		Webcam w = xv.getActiveLocalMirrorWebcam();
		
		byte[] pixelDisplay2D 	= null;
		
		if (w != null) {
			
			byte[] pixels 			= null;
			byte[] width 			= null;
			byte[] height 			= null;
			byte[] alpha 			= null;
			
			BufferedImage image = w.getImage();
			
			if (image != null) {
				
				// CONVERT IMAGE DATA TO SERIALIZABLE FORMAT - 2D RGB PIXEL ARRAY
				pixels 	= ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
				width 	= BinaryNumberUtil.intToByteArray32(image.getWidth());
				height 	= BinaryNumberUtil.intToByteArray32(image.getHeight());
				alpha 	= BinaryNumberUtil.intToByteArray32(image.getAlphaRaster() != null ? 1 : 0);
				
				ByteArrayOutputStream baosImageData = new ByteArrayOutputStream();
				baosImageData.write(alpha);
				baosImageData.write(width);
				baosImageData.write(height);
				baosImageData.write(pixels);
				
				baosImageData.flush();
				pixelDisplay2D = baosImageData.toByteArray();
				baosImageData.reset();
			}
		}
		
		return pixelDisplay2D;
	}
}
