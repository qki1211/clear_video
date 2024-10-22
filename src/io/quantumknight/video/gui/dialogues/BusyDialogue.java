package io.quantumknight.video.gui.dialogues;
/********************************************************************************************
//* Filename: 		BusyDialogue.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    Progress Bar Dialogue to block the EDT
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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class BusyDialogue extends JDialog implements PropertyChangeListener {
	
	private static final long serialVersionUID = 6806426291108540895L;
	
	private JProgressBar progressBar;

	/**
	 * Constructor
	 * @param relativeTo
	 * @param title
	*/
	public BusyDialogue(Component relativeTo, String title){
		
		super((Frame)SwingUtilities.getAncestorOfClass(Frame.class, relativeTo), title, false);
		
		this.progressBar = new JProgressBar();
		this.progressBar.setStringPainted(true);
		this.progressBar.setForeground(Color.ORANGE);
		this.progressBar.setMaximum(100);

		add(progressBar, BorderLayout.CENTER);
		setSize(200, 60);
		
		Rectangle rectOwner, rectDialog;
		rectOwner = relativeTo.getBounds();
		rectDialog = this.getBounds();
		setLocation(rectOwner.x + rectOwner.width /2 - rectDialog.width /2,
		rectOwner.y + rectOwner.height/2 - rectDialog.height /2);
	}

	/**
	 * Invoked when task's progress property changes.
	*/
	public void propertyChange(PropertyChangeEvent evt) {
		
	    if ("progress" == evt.getPropertyName()) {
	    	
	        int progress = (Integer) evt.getNewValue();
	        progressBar.setValue(progress);
	    } 
	}
}
