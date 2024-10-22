package io.quantumknight.video.framework.swing;
/********************************************************************************************
//* Filename: 		SwingFormUtil.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    UTILITY - Construction of common Swing form elements
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import io.quantumknight.video.framework.constants.ConstantsColor;
import io.quantumknight.video.framework.constants.ConstantsFonts;

public abstract class SwingFormUtil {
	
	/**
	 * ATOMIC SUBROUTINE - CONSTRUCT SELECT BOX / DROP DOWN
	 * @param screen
	 * @param screenField
	 * @param optionList
	 * @param selectedIndex
	 * @return JComboBox<String>
	*/
	public static JComboBox<String> buildComboBox(String screen, String screenField, String[] optionList, int selectedIndex) {
		JComboBox<String> cb = new JComboBox<String>(optionList);
		cb.setSelectedIndex(selectedIndex);
		cb.setName(screenField);
		SwingApplicationRuntime.setJComponentByName(screen + "|" + screenField, cb);
		return cb;
	}
	
	/**
	 * ATOMIC SUBROUTINE - CONSTRUCT CHECKBOX
	 * @param screen
	 * @param screenField
	 * @param checked
	 * @return JCheckBox
	*/
	public static JCheckBox buildCheckboxes(String screen, String screenField, boolean checked) {
		JCheckBox checkbox = new JCheckBox();
		checkbox.setSelected(checked);
		checkbox.setName(screenField);
		SwingApplicationRuntime.setJComponentByName(screen + "|" + screenField, checkbox);
		return checkbox;
	}
	
	/**
	 * ATOMIC SUBROUTINE - CONSTRUCT BUTTONS
	 * @param screen
	 * @param screenField
	 * @param buttonLabel
	 * @return JButton
	*/
	public static JButton buildButtons(String screen, String screenField, String buttonLabel) {
		JButton button = new JButton(buttonLabel);
		MasterEventRouter eventHandler = MasterEventRouter.getInstance();
		button.setMargin(new Insets(0,0,0,0));
		button.setActionCommand(screenField); // IMPLEMENTATION TBD
		button.setName(buttonLabel);
		button.addActionListener(eventHandler);
		SwingApplicationRuntime.setJComponentByName(screen + "|" + screenField, button);
		return button;
	}
	
	/**
	 * ATOMIC SUBROUTINE - Construct Textfields
	 * @param screen
	 * @param screenField
	 * @param initialTextValue
	 * @return JTextField
	*/
	public static JTextField buildTextField(String screen, String screenField, String initialTextValue) {
		JTextField jTextField = new JTextField();
		SwingApplicationRuntime.setJComponentByName(screen + "|" + screenField, jTextField);
		jTextField.setName(screenField);
		jTextField.setText(initialTextValue != null ? initialTextValue : "");
		return jTextField;
	}
	
	/**
	 * ATOMIC SUBROUTINE - Construct clickable image tile
	 * @param screen
	 * @param screenField
	 * @param systemGraphicName
	 * @return ImageTile
	*/
	public static ImageTile buildClickableImageTile(String screen, String screenField, String cmd, String systemGraphicName) {
		ImageIcon icon = SwingApplicationRuntime.getGraphicFromApplicationSystem(systemGraphicName);
		ImageTile tile = new ImageTile(icon);
		tile.setName(screenField);
		tile.setActionCommand(cmd);
		SwingApplicationRuntime.setJComponentByName(screen + "|" + screenField + "|" + cmd, tile);
		MasterEventRouter eventHandler = MasterEventRouter.getInstance();
		tile.addMouseListener(eventHandler);
		return tile;
	}
	
	/**
	 * ATOMIC SUBROUTINE - Construct Labels
	 * @param labelString
	 * @param bold
	 * @return JLabel
	*/
	public static JLabel buildLabel(String labelString, boolean bold) {
		JLabel jlabel = new JLabel(labelString);
		if (bold) {
			jlabel.setFont(ConstantsFonts.FONT_VERDANA_14B);
		}
		else {
			jlabel.setFont(ConstantsFonts.FONT_VERDANA_12);
		}
		jlabel.setForeground(ConstantsColor.COLOR_TEXT_BLACK);
		return jlabel;
	}
}
