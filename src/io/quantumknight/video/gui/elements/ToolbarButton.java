package io.quantumknight.video.gui.elements;
/********************************************************************************************
//* Filename: 		ToolbarButton.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    Toolbar Button Element for System Navigation
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

import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ToolbarButton extends JButton {
	
	private static final long serialVersionUID = -1913636077721980713L;
	
	private static final Insets margins = new Insets(0, 0, 0, 0);

	/**
	 * CONSTRUCTOR - ICON 
	 * @param icon
	*/
	public ToolbarButton(Icon icon) {
		super(icon);
		setMargin(margins);
		setVerticalTextPosition(BOTTOM);
		setHorizontalTextPosition(CENTER);
	}

	/**
	 * CONSTRUCTOR - IMAGE FILE
	 * @param imageFile
	*/
	public ToolbarButton(String imageFile) {
		this(new ImageIcon(imageFile));
	}

	/**
	 * CONSTRUCTOR - IMAGE-ICON + TEXT
	 * @param imageFile
	*/
	public ToolbarButton(ImageIcon imageIcon, String text) {
		this(imageIcon);
		setText(text);
	}

	/**
	 * CONSTRUCTOR - IMAGE-ICON + TEXT + BORDER
	 * @param imageFile
	*/
	public ToolbarButton(ImageIcon imageIcon, String text, boolean isBorderEnabled) {
		this(imageIcon);
		setText(text);
		setBorderPainted(isBorderEnabled);
	}

	/**
	 * CONSTRUCTOR - IMAGE FILE + TEXT
	 * @param imageFile
	*/
	public ToolbarButton(String imageFile, String text) {
		this(new ImageIcon(imageFile));
		setText(text);
	}
}
