package io.quantumknight.video.framework.swing;
/********************************************************************************************
//* Filename: 		ImageTile.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    JFC/SWING FRAMEWORK - JPANEL CONTAINER FOR SINGLE IMAGE ICON
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

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImageTile extends JPanel {
	
	private static final long serialVersionUID = -514025689316034512L;
	
	private ImageIcon icon;
	private String actionCommand;
	
	/**
	 * Constructor Takes ImageIcon, Width, and Height
	 * @param ImageIcon icon
	 * @param int width
	 * @param int height
	*/
	public ImageTile(ImageIcon icon) {
		super();
		this.icon = icon;
	}
	
	/**
	 * Overridden PAINT Method for backgroud tile
	 * @param Graphics G
	*/
	public void paintComponent(Graphics g) {
		g.drawImage(this.icon.getImage(), 0,0,this.getWidth(),this.getHeight(),this);
	}
	
	///**
	// * Antialias Tile
	//*/
	//public void paintComponent(Graphics g) {
	//	Graphics2D g2 = (Graphics2D) g;
	//	g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	//	g2.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
	//	g2.drawImage(this.icon.getImage(), 0,0,this.getWidth(),this.getHeight(),this);
	//}
	
	/**
	 * @return the icon
	 */
	public ImageIcon getIcon() {
		return icon;
	}

	/**
	 * @return the actionCommand
	 */
	public String getActionCommand() {
		return actionCommand;
	}

	/**
	 * @param actionCommand the actionCommand to set
	 */
	public void setActionCommand(String actionCommand) {
		this.actionCommand = actionCommand;
	}
}
