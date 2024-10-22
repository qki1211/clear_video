package io.quantumknight.video.gui.elements;
/********************************************************************************************
//* Filename: 		XVIDToolbar.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    Primary Navigational Toolbar
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

import javax.swing.ImageIcon;
import javax.swing.JToolBar;

import io.quantumknight.video.constants.ConstantsElements;
import io.quantumknight.video.framework.swing.MasterEventRouter;
import io.quantumknight.video.framework.swing.SwingApplicationRuntime;

public class XVIDToolbar extends JToolBar {
	
	private static final long serialVersionUID = -8024992837075540798L;
	
	/** 
	 * Default Constructor
	*/
	public XVIDToolbar() {
		super();
		this.setupButtons();
	}

	/**
	 * Initialize Buttons
	*/
	private void setupButtons() {
		
		for(int i =0 ; i < ConstantsElements._BUTTONS.length; i++) {
			
			ImageIcon icon = SwingApplicationRuntime.getGraphicFromApplicationSystem(ConstantsElements._BUTTONS[i][1]);
			ToolbarButton button = new ToolbarButton(icon, ConstantsElements._BUTTONS[i][0], false);
			button.setName(ConstantsElements._BUTTONS[i][0]);
			
			button.addActionListener(MasterEventRouter.getInstance());
			
			SwingApplicationRuntime.setJComponentByName(button.getName(), button);
			
			if (i == 1 || i == 2 || i == 3 || i == 4) {
				this.addSeparator();
			}
			
			this.add(button);
		}
	    this.setFloatable(false);
	}
}
