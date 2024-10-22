package io.quantumknight.video.gui.events;
/********************************************************************************************
//* Filename: 		SliderChangeHandler.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    JSlider Event Handler Interface
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

import javax.swing.JLabel;
import javax.swing.JSlider;

import io.quantumknight.video.constants.ConstantsEncryptionStrength;
import io.quantumknight.video.constants.ConstantsElements;
import io.quantumknight.video.framework.interfaces.SliderChangeHandlerInterface;
import io.quantumknight.video.framework.swing.SwingApplicationRuntime;
import io.quantumknight.video.gui.screens.HomeScreen;
import io.quantumknight.video.state.VideoDemoSettings;


public class SliderChangeHandler implements SliderChangeHandlerInterface { 
	
	/**
	 * Invoked when a JSlider is moved
	*/
	public void doHandle(JSlider slider, String name) {

	    if (!slider.getValueIsAdjusting()) {
	        
	    	// GET SLIDER VALUE
	    	int value = slider.getValue();
	    	
	    	// UPDATE SINGLETON
	    	VideoDemoSettings xv = VideoDemoSettings.getInstance();
	    	xv.setModulatorSetting(value);
	    	xv.setEncryptionStrength(ConstantsEncryptionStrength.getStrength(value - 1));
	    	
	    	// UPDATE PRIMARY STRENGTH SETTING VISUAL
	    	HomeScreen screen = (HomeScreen)SwingApplicationRuntime.getSystemJPanelScreensByName(ConstantsElements.SYSTEM_SCREENS[1][0]);  // SCREEN REFERENCE FOR UPDATING TIMERS
			JLabel strengthLabel = (JLabel)screen.getField(ConstantsElements.LABELS[4]);
			strengthLabel.setText("Strength: " + xv.getEncryptionStrength());
			strengthLabel.repaint();
	    }
	}
}
