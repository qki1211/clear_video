package io.quantumknight.video.framework.swing;
/********************************************************************************************
//* Filename: 		CommonDialog.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    JFC/SWING FRAMEWORK - COMMON - JDialog with ESCAPE Handler
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

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

public class CommonDialog extends JDialog {
	
	private static final long serialVersionUID = -7116786658847150907L;
	
	private static final String _ESCAPE = "ESCAPE";
	
	/**
	 * SCI JDialog Constructor - Includes Modality Type
	 * @param masterFrame
	 * @param headerLabel
	 * @param modalityType
	*/
	public CommonDialog(JFrame masterFrame, String headerLabel, ModalityType modalityType) {
		super(masterFrame, headerLabel, modalityType);
	}
	
	/**
	 * OVERRIDE - CreateRootPane Override (inherited by the JDialog) allows "Escape / Close" Function
	 * @return JRootPane
	*/
	public JRootPane createRootPane() {
		JRootPane rootPane = new JRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke(_ESCAPE);
		Action action = new AbstractAction() {
			private static final long serialVersionUID = 1L;
			/**
			 * INNER CLASS EVENT LISTENER
			 * @param ActionEvent
			*/
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		};
		InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(stroke, _ESCAPE);
		rootPane.getActionMap().put(_ESCAPE, action);
		return rootPane;
	}
}
