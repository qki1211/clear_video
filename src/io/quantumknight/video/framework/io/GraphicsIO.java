package io.quantumknight.video.framework.io;
/********************************************************************************************
//* Filename: 		GraphicsIO.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    JFC/SWING FRAMEWORK - GRAPHICS I/O - LOADS IMAGERY FROM COMPRESSED JAR FILE
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

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import io.quantumknight.video.framework.swing.ImageKit;


public class GraphicsIO {

	/**
	 * Load serialized graphics HashMap and return it to caller
	 * @param String fileName
	 * @return HashMap
	 * @throws Exception
	 * @Deprecated
	*/
	@SuppressWarnings("unchecked")
	public synchronized HashMap<String,ImageIcon> getGraphicsMapFromJAR(String fileName) throws Exception {
	
		//	Resource is packaged inside JAR File
		InputStream is = this.getClass().getResourceAsStream("/resources/" + fileName);
		ObjectInputStream ois = new ObjectInputStream(is);
		HashMap<String,ImageIcon> returnMap = (HashMap<String,ImageIcon>)ois.readObject();
		ois.close();
		is.close();
		System.gc();
		
		return returnMap;
	}
	
	/**
	 * Load serialized graphics HashMap and return it to caller
	 * @param String fileName
	 * @return HashMap
	 * @throws Exception
	*/
	@SuppressWarnings("unchecked")
	public synchronized HashMap<String,byte[]> getCompressedGraphicsMapFromJAR(String fileName) throws Exception {
	
		//	Resource is packaged inside JAR File
		InputStream is = this.getClass().getResourceAsStream("/resources/" + fileName);
		ObjectInputStream ois = new ObjectInputStream(is);
		HashMap<String,byte[]> returnMap = (HashMap<String,byte[]>)ois.readObject();
		ois.close();
		is.close();
		System.gc();
		
		return returnMap;
	}
	
	/**
	 * Save serialized Object as binary file
	 * @param String fileName
	 * @param Object output
	 * @throws Exception
	*/
	public synchronized void setObjectToSerializedFile(String fileName, Object output) throws Exception {
	
		//	Serialize and store the ImageIcon to DISK IO
		File gf = new File(fileName);
		if (gf.exists()) { gf.delete(); }
		
		FileOutputStream fos = new FileOutputStream(gf);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(output);
		
		fos.flush();
		fos.close();
		System.gc();
	}
	
	/**
	 * Create Single Image as Serialized HashMap
	 * @param String inputFileName
	 * @param String outputFileName
	 * @param String imageKeyName
	 * @return void
	 * @throws Exception
	*/
	public void setSingleImageToSerializedMap(String inputFileName, String outputFileName, String imageKeyName) throws Exception {
	    
	    HashMap<String,ImageIcon> outputMap = new HashMap<String,ImageIcon>();
	    ImageIcon icon = getImageFileToImageIcon(inputFileName);
	    outputMap.put(imageKeyName,icon);
	    this.setObjectToSerializedFile(outputFileName, outputMap);
	}
	
	/**
	 * Create Batch of Images as Serialized HashMap
	 * @param String[] inputFileNames
	 * @param String outputFileName
	 * @param String[] imageKeyNames
	 * @return void
	 * @throws Exception
	 * @Deprecated
	*/
	public void setBatchImagesToSerializedMap(String inputFileNames[][], String outputFileName) throws Exception {
	
	    HashMap<String,ImageIcon> outputMap = new HashMap<String,ImageIcon>();
	    for (int i=0; i<inputFileNames.length; i++) {
	        String fileName = inputFileNames[i][0];
	        String fileKey = inputFileNames[i][1];
	        ImageIcon icon = getImageFileToImageIcon(fileName);
	        outputMap.put(fileKey, icon);
	    }
	    this.setObjectToSerializedFile(outputFileName, outputMap);
	}
	
	/**
	 * Create Batch of Images as Serialized HashMap
	 * This is the newer version of this method.  It will create
	 * a similar serialized hashmap, however, the binary images
	 * shall be as byte[] instead of serialized ImageIcon classes
	 * @param String[] inputFileNames
	 * @param String outputFileName
	 * @param String[] imageKeyNames
	 * @return void
	 * @throws Exception
	*/
	public void setBatchImagesToSerializedMapCompressed(String inputFileNames[][], String outputFileName, int quality) throws Exception {
	
	    HashMap<String,byte[]> outputMap = new HashMap<String,byte[]>();
	    for (int i=0; i<inputFileNames.length; i++) {
	        String fileName = inputFileNames[i][0];
	        String fileKey = inputFileNames[i][1];
	        byte[] imageBinary = getImageFileToByteArray(fileName, quality);
	        outputMap.put(fileKey, imageBinary);
	    }
	    this.setObjectToSerializedFile(outputFileName, outputMap);
	}
	
	/**
	 * Loads a single graphic image and returns it as a javax.swing.ImageIcon object!
	 * @param String inputFileName
	 * @return ImageIcon
	 * @throws Exception
	*/
	public static ImageIcon getImageFileToImageIcon(String inputFileName) throws Exception {
		File f = new File(inputFileName);
		if (!f.exists()) { 
			throw new Exception("File Not Found! - [" + inputFileName + "]");
		}
	    Image img = Toolkit.getDefaultToolkit().createImage(inputFileName);
		f = null;
		System.gc();
		return new ImageIcon(img);
	}
	
	/**
	 * Loads a single graphic images and returns it as a byte[] object!
	 * @param inputFileName
	 * @return byte[]
	 * @throws Exception
	*/
	public static byte[] getImageFileToByteArray(String inputFileName, int quality) throws Exception {
		File theFile = new File(inputFileName);
		if (!theFile.exists()) { 
			throw new Exception("File Not Found! - [" + inputFileName + "]");
		}
		BufferedImage img = ImageIO.read(theFile);
		return ImageKit.getBytes(img, quality/100f, "JPEG"); // set default JPG quality as 85%
	}
}
