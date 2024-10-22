package io.quantumknight.video.gui.panels;
/********************************************************************************************
//* Filename: 		XVIDGlassPane.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    Glass Pane Implementation - Encrypted Video Demo Application
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

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import io.quantumknight.video.constants.ConstantsApplicationGlobalUI;
import io.quantumknight.video.framework.constants.ConstantsColor;
import io.quantumknight.video.framework.swing.MasterJFrame;
import io.quantumknight.video.gui.dialogues.BusyDialogue;
import io.quantumknight.video.gui.elements.XVIDGlassPaneHelper;

public class XVIDGlassPane extends JPanel implements MouseListener, MouseMotionListener {
	
	private static final long serialVersionUID = 7970658227444521188L;
	
	private int width = ConstantsApplicationGlobalUI.INITIAL_WINDOW_WIDTH;
	private int height= ConstantsApplicationGlobalUI.INITIAL_WINDOW_HEIGHT;
	
	private BusyDialogue dialogue;

	private AlphaComposite composite;
	private BufferedImage ghostedDragImage = null;
	private Point ghostedDragLocation = new Point(0, 0);
	
	/**
	 * Default Constructor
	*/
	public XVIDGlassPane() {
		super();
		this.createMouseListeners();
		
		setOpaque(false);
		composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f); 
		
		// recompute size in case main view has changed
		this.recomputeDimensions();
	}

	/**
	 * ATOMIC SUBROUTINE - Resize glasspane based on window size changes
	*/
	public void recomputeDimensions() {
		
		// recompute size in case main view has changed
		MasterJFrame masterJFrame = (MasterJFrame)XVIDGlassPaneHelper.getMasterFrame();
		
		Dimension d = masterJFrame.getSize();
		int w = (int)d.getWidth();
		int h = (int)d.getHeight();
		
		if (this.width != w) {
			this.width = w;
		}
		if (this.height != h) {
			this.height = h;
		}
	}
	
	/**
	 * Block mouse clicks while glass pane is raise
	 * @param e
	 * @param repaint
	*/
	private void redispatchMouseEvent(MouseEvent e,boolean repaint) {
		
		// block mouse events - (hence being a glass pane!)
		if (e.getID() == 506) { // click event
			try {
				//Toolkit.getDefaultToolkit().beep();
				if (this.dialogue != null) {
					this.dialogue.requestFocus();
					this.dialogue.repaint();
					this.dialogue.validate();
				}
			}
			catch(Exception ex) {
				// digest
			}
		}
	}
	
	/**
	 * Utility feature - Create Mouse Adapter 
	 * @return MouseAdapter
	*/
	private MouseAdapter createMouseAdapter() {
		return new MouseAdapter() { 
			public void mousePressed(MouseEvent me) { 
				try {
					//Toolkit.getDefaultToolkit().beep();
					if (dialogue != null) {
						dialogue.requestFocus();
						dialogue.repaint();
						dialogue.validate();
					}
				}
				catch(Exception ex) {
					// digest
				}
	        }
	    };
	}

	/**
	 * Paint Method - Paints Over Entire Screen Screen
	*/
	public void paintComponent(Graphics g) {
		
		if (ghostedDragImage == null) {
			
			// blocking glass pane mode
			g.setColor(ConstantsColor.COLOR_GREY_BLUE_25XP);
			g.fillRect(0,0,this.width,this.height);
		}
		else {
			// DnD ghosted image mode
			Graphics2D g2 = (Graphics2D) g;
			g2.setComposite(composite);
			g2.drawImage(ghostedDragImage,
				(int) (ghostedDragLocation.getX( ) - (ghostedDragImage.getWidth(this)  / 2)),
				(int) (ghostedDragLocation.getY( ) - (ghostedDragImage.getHeight(this) / 2)),
				null);
		}
	}

	/**
	 * Add mouse motion listeners
	*/
	private void createMouseListeners() {
		addMouseListener(this.createMouseAdapter());
		addMouseMotionListener(this);
	}

	/**
	 * Sets the image for a ghost drag and drop action
	 * @param dragged image to be displayed during ghosted drag and drop
	 */
	public void setGhostedDragImage(BufferedImage dragged) {
		this.ghostedDragImage = dragged;
	}

	/**
	 * Set the location where a ghosted drag image should occur
	 * 
	 * @param location
	*/
	public void setGhostedDragLocation(Point location) {
		this.ghostedDragLocation = location;
	}

	public void mouseMoved(MouseEvent e) {
		redispatchMouseEvent(e, false);
	}

	public void mouseDragged(MouseEvent e) {
		redispatchMouseEvent(e, false);
	}

	public void mouseClicked(MouseEvent e) {
	    redispatchMouseEvent(e, false);
	}

	public void mouseEntered(MouseEvent e) {
	    redispatchMouseEvent(e, false);
	}

	public void mouseExited(MouseEvent e) {
	    redispatchMouseEvent(e, false);
	}

	public void mousePressed(MouseEvent e) {
	    redispatchMouseEvent(e, false);
	}

	public void mouseReleased(MouseEvent e) {
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the dialogue
	 */
	public BusyDialogue getDialogue() {
		return dialogue;
	}

	/**
	 * @param dialogue the dialogue to set
	 */
	public void setDialogue(BusyDialogue dialogue) {
		this.dialogue = dialogue;
	}
}
