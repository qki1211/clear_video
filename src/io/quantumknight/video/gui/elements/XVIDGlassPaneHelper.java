package io.quantumknight.video.gui.elements;
/********************************************************************************************
//* Filename: 		XVIDGlassPaneHelper.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    UTILITY - facilitates progress bars and DND ghost
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
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JApplet;
import javax.swing.SwingUtilities;

import io.quantumknight.video.constants.ConstantsApplicationGlobalUI;
import io.quantumknight.video.framework.swing.MasterJFrame;
import io.quantumknight.video.framework.swing.SwingApplicationRuntime;
import io.quantumknight.video.gui.dialogues.BusyDialogue;
import io.quantumknight.video.gui.panels.XVIDGlassPane;

public abstract class XVIDGlassPaneHelper {

	/**
	 * Convenience method for engaging the progress bar / screen blocking glass pane
	 * @param dialogueTitle
	 * @return BusyDialogue
	*/
	public static BusyDialogue raiseGlassPane(String dialogueTitle) {
		
		XVIDGlassPane glass = getGlassPane();
		glass.recomputeDimensions();
		Component masterFrame = getMasterFrame();
		BusyDialogue dialogue = new BusyDialogue(masterFrame,dialogueTitle);
		dialogue.setLocationRelativeTo(masterFrame);
		dialogue.setVisible(true); // note this will break if the dialogue is modal : modal=false
		glass.setDialogue(dialogue);
		glass.setVisible(true);
		return dialogue;
	}

	/**
	 * Convenience method for engaging the progress bar / screen blocking glass pane
	 * @param dialogueTitle
	 * @return BusyDialogue
	*/
	public static void raiseBlockingGlassPane() {
		
		XVIDGlassPane glass = getGlassPane();
		glass.setVisible(true);
		glass.repaint();
	}

	/**
	 * Convenience method for putting the glass pane in a state to support ghosted
	 * image drag and drop
	 * @param p
	 * @param image
	 */
	public static void initiateGhostedDrag(Point p, BufferedImage image) {
		
		XVIDGlassPane glass = getGlassPane();
		XVIDGlassPaneHelper.raiseBlockingGlassPane();
		SwingUtilities.convertPointFromScreen(p, glass);
		glass.setGhostedDragLocation(p);
		glass.setGhostedDragImage(image);
		glass.validate();
		glass.repaint();
	}

	/**
	 * Convenience method for updating the location of a ghosted image during ghosted
	 * image drag and drop
	 * @param p
	 */
	public static void updateGhostedDrag(Point p) {
		
		XVIDGlassPane glass = getGlassPane();
		SwingUtilities.convertPointFromScreen(p, glass);
		glass.setGhostedDragLocation(p);
		glass.validate();
		glass.repaint();
	}

	/**
	 * Convenience method for updating the state of the glass pane when a ghosted
	 * image drag and drop has completed
	 * @param p
	 */
	public static void endGhostedDrag(Point p) {
		XVIDGlassPane glass = getGlassPane();
		SwingUtilities.convertPointFromScreen(p, glass);
		glass.setGhostedDragLocation(p);
		XVIDGlassPaneHelper.lowerGlassPane(null);
		glass.setGhostedDragImage(null);
	}

	/**
	 * Lower the screen blocking glass pane and dispose of the progress bar
	 * @param dialogue
	*/
	public static void lowerGlassPane(BusyDialogue dialogue) {
		
		if (dialogue != null) {
			dialogue.dispose();
			dialogue.setVisible(false);
		}
		XVIDGlassPane glass = getGlassPane();
		glass.setVisible(false);
		glass.setDialogue(null);
	}

	/**
	 * Internal subroutine to consolidate the retrieval of the glass pane
	 * @return XVIDGlassPane
	*/
	private static XVIDGlassPane getGlassPane() {
		
		XVIDGlassPane glass = null;
		if (ConstantsApplicationGlobalUI.USE_APPLET) {
			JApplet jap = (JApplet)SwingApplicationRuntime.getMasterApplet();
			glass = (XVIDGlassPane)jap.getGlassPane();
		}
		else {
			MasterJFrame mjf = (MasterJFrame)SwingApplicationRuntime.getMasterFrame();
			glass = (XVIDGlassPane)mjf.getGlassPane();
		}
		return glass;
	}

	/**
	 * Convenience method for acquiring the Master Frame component
	 * @return Component
	*/
	public static Component getMasterFrame() {
		
		Component masterFrame = null;
		if (ConstantsApplicationGlobalUI.USE_APPLET) {
			masterFrame = SwingApplicationRuntime.getMasterApplet();
		}
		else {
			masterFrame = SwingApplicationRuntime.getMasterFrame();
		}
		return masterFrame;
	}

	/**
	 * getter method for Ghost Pane
	 * @return XVIDGlassPane
	*/
	public static XVIDGlassPane getGhostGlassPane() {
		return getGlassPane();
	}
}
