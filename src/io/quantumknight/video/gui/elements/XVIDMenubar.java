package io.quantumknight.video.gui.elements;
/********************************************************************************************
//* Filename: 		XVIDMenubar.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    Primary Navigation Menubar
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
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import io.quantumknight.video.constants.ConstantsElements;
import io.quantumknight.video.framework.swing.MasterEventRouter;
import io.quantumknight.video.framework.swing.SwingApplicationRuntime;

public class XVIDMenubar extends JMenuBar {
	
	private static final long serialVersionUID = -1684039390565327295L;

	/** 
	 * Default Constructor
	*/
	public XVIDMenubar() {
		super();
		JMenu[] menus = this.setupMenus();
		this.setupMenuItems(menus);
	}

	/**
	 * Initialize Menus
	 * @return JMenu[]
	*/
	private JMenu[] setupMenus() {
		JMenu[] menus = new JMenu[ConstantsElements._MENU_NAMES.length];
		for (int i=0; i<menus.length; i++) {
			menus[i] = new JMenu(ConstantsElements._MENU_NAMES[i]);
			if (i == 1) {
				continue; // skip edit menu
			}
			this.add(menus[i]);
		}
		menus[0].setMnemonic(KeyEvent.VK_F);
//		menus[1].setMnemonic(KeyEvent.VK_E);
		menus[2].setMnemonic(KeyEvent.VK_H);
		
		return menus;
	}

	/**
	 * Initialize Menu Items
	 * @param JMenu[] menus
	 * @return void
	*/
	private void setupMenuItems(JMenu[] menus) {
		this.setupFileMenu(menus);
//		this.setupEditMenu(menus);
		this.setupHelpMenu(menus);
	}

	/**
	 * Initialize File Menu Items
	 * @param JMenu[] menus
	 * @return void
	*/
	private void setupFileMenu(JMenu[] menus) {
		String[] fileItems = {	ConstantsElements.BUTTON_LABELS[0],
								ConstantsElements.BUTTON_LABELS[1],
								ConstantsElements.BUTTON_LABELS[3]
		};
		JMenuItem[] fileMenuitems = new JMenuItem[fileItems.length];
		for (int i=0; i<fileMenuitems.length; i++) {
			fileMenuitems[i] = new JMenuItem(fileItems[i]);
			menus[0].add(fileMenuitems[i]);
			switch (i) {
				case 1:	menus[0].addSeparator(); break;
				default:break;
			}
			fileMenuitems[i].setName(fileItems[i]);
			fileMenuitems[i].addActionListener(MasterEventRouter.getInstance());
			
			SwingApplicationRuntime.setJComponentByName("JMenuItem" + "|" + fileItems[i], fileMenuitems[i]);
			
		}
		fileMenuitems[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.SHIFT_MASK));
		fileMenuitems[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, ActionEvent.SHIFT_MASK));
		fileMenuitems[2].setMnemonic(KeyEvent.VK_X);
	}

//	/**
//	 * Initialize Edit Menu Items
//	 * @param JMenu[] menus
//	 * @return void
//	*/
//	private void setupEditMenu(JMenu[] menus) {
//		String[] editItems = {	ConstantsElements.BUTTON_LABELS[4], };
//		
//		JMenuItem[] editMenuitems = new JMenuItem[editItems.length];
//		for (int i=0; i<editMenuitems.length; i++) {
//			editMenuitems[i] = new JMenuItem(editItems[i]);
//			menus[1].add(editMenuitems[i]);
//			editMenuitems[i].setName(editItems[i]);
//			editMenuitems[i].addActionListener(MasterEventRouter.getInstance());
//			switch (i) {
//				case 0:	menus[1].addSeparator(); break;
//				default:break;
//			}
//		}
//	}

	/**
	 * Initialize Help Menu Items
	 * @param JMenu[] menus
	 * @return void
	*/
	private void setupHelpMenu(JMenu[] menus) {
		String[] helpItems = {	ConstantsElements.BUTTON_LABELS[2]
		};
		JMenuItem[] helpMenuitems = new JMenuItem[helpItems.length];
		for (int i=0; i<helpMenuitems.length; i++) {
			helpMenuitems[i] = new JMenuItem(helpItems[i]);
			helpMenuitems[i].setName(helpItems[i]);
			menus[2].add(helpMenuitems[i]);
			helpMenuitems[i].addActionListener(MasterEventRouter.getInstance());
		}
	}
}
