package io.quantumknight.video.framework.swing;
/********************************************************************************************
//* Filename: 		ClipboardTransferVO.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    JFC/SWING FRAMEWORK - VALUE OBJECT - TRANSFERS DATA D-N-D TO CLIPBOARD
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

import java.awt.Component;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;

import io.quantumknight.video.framework.constants.SwingApplicationConstants;

public class ClipboardTransferVO implements Serializable, Transferable, ClipboardOwner {
	
	private static final long serialVersionUID = 3701905245917450664L;
	
	public static final DataFlavor localStringFlavor 	= DataFlavor.stringFlavor;
	public static final DataFlavor imageFlavor 			= DataFlavor.imageFlavor;
	public static final DataFlavor objectFlavor 		= new DataFlavor(Object.class,"object");
	public static final DataFlavor componentFlavor 		= new DataFlavor(Component.class,"component");
	
	
	public static final DataFlavor[] flavors = {		ClipboardTransferVO.localStringFlavor,
														ClipboardTransferVO.imageFlavor,
														ClipboardTransferVO.objectFlavor,
														ClipboardTransferVO.componentFlavor
	};
	
	private static final List<DataFlavor> flavorList = Arrays.asList(flavors);
	
	private String textPayload;
	private ImageIcon imagePayload;
	private Object objectPayload;
	
	/**
	 * Constructor takes Text Payload
	 * @param textPayload
	*/
	public ClipboardTransferVO(String textPayload) {
		super();
		this.textPayload = textPayload;
	}
	
	/**
	 * Copnstructor takes ImageIcon Payload
	 * @param imagePayload
	*/
	public ClipboardTransferVO(ImageIcon imagePayload) {
		super();
		this.imagePayload = imagePayload;
	}
	
	/**
	 * Copnstructor takes java Object Payload
	 * @param objectPayload
	*/
	public ClipboardTransferVO(Object objectPayload) {
		super();
		this.objectPayload = objectPayload;
	}
	
	/**
	 * Implementation - get transfer serialized data
	 * @param DataFlavor flavor
	 * @return Object
	 * @throws UnsupportedFlavorException
	 * @throws IOException
	*/
	public synchronized Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
	
		if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Transfer Data Requested from ClipboardTransferVO");}
		
		if (flavor.equals(ClipboardTransferVO.localStringFlavor)) {
			return this.textPayload;
		}
		else if (flavor.equals(ClipboardTransferVO.imageFlavor)) {
			return this.imagePayload;
		}
		else if (flavor.equals(ClipboardTransferVO.objectFlavor)) {
			return this.objectPayload;
		}
		else if (flavor.equals(ClipboardTransferVO.componentFlavor)) {
			return this.objectPayload;
		}
		else {
			throw new UnsupportedFlavorException(flavor);
		}
	}
	
	/**
	 * Return list of data flavors
	 * @return DataFlavor[]
	 */
	public synchronized DataFlavor[] getTransferDataFlavors() {
		return flavors;
	}
	
	/**
	 * Return boolean determination stating whether data flavor is supported
	 * @param DataFlavor flavor
	 * @return boolean
	*/
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return (flavorList.contains(flavor));
	}
	
	/**
	 * Implementation - lost ownership
	*/
	public void lostOwnership(Clipboard clipboard, Transferable contents) {
		if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("ClipboardTransferVO lost ownership of " + clipboard.getName());}
		if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("data: " + contents);}
	}
	
	/**
	 * @return Returns the imagePayload.
	 */
	public ImageIcon getImagePayload() {
		return imagePayload;
	}
	
	/**
	 * @param imagePayload The imagePayload to set.
	 */
	public void setImagePayload(ImageIcon imagePayload) {
		this.imagePayload = imagePayload;
	}
	
	/**
	 * @return Returns the textPayload.
	 */
	public String getTextPayload() {
		return textPayload;
	}
	
	/**
	 * @param textPayload The textPayload to set.
	 */
	public void setTextPayload(String textPayload) {
		this.textPayload = textPayload;
	}
	
	/**
	 * @return Returns the objectPayload.
	 */
	public Object getObjectPayload() {
		return objectPayload;
	}
	
	/**
	 * @param objectPayload The objectPayload to set.
	 */
	public void setObjectPayload(Object objectPayload) {
		this.objectPayload = objectPayload;
	}
}
