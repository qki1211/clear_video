package io.quantumknight.video.framework.swing;
/********************************************************************************************
//* Filename: 		GenericSwingAction.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    JFC/SWING FRAMEWORK - GENERIC ACTION CLASS
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
import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;

import javax.swing.JApplet;
import javax.swing.JScrollPane;

import io.quantumknight.video.framework.constants.SwingApplicationConstants;
import io.quantumknight.video.framework.interfaces.SwingCommonLoginActionInterface;
import io.quantumknight.video.framework.io.LogManager;
import io.quantumknight.video.framework.io.Logger;


public abstract class GenericSwingAction {
	
	protected static Logger log = LogManager.getLogger(GenericSwingAction.class);
	
	private boolean isLoginRequest = false;
	private boolean isInitialReg = false;
	
	/**
	 * Constructor Triggers Action to Fire!
	*/
	public GenericSwingAction(Object[] inputs) {
		this(inputs, false);
	}

	/**
	 * Constructor Triggers Action to Fire!
	*/
	public GenericSwingAction(Object[] inputs, boolean isLoginRequest) {
		this(inputs,false,false);
	}

	/**
	 * Constructor Triggers Action to Fire!
	*/
	public GenericSwingAction(Object[] inputs, boolean isLoginRequest, boolean isInitialReg) {
	    super();
	    this.isLoginRequest = isLoginRequest;
	    this.isInitialReg = isInitialReg;
	    try {
	    	if (inputs == null) {
	    		Object[] nullInput = { "" };
	    		executeGlobal(nullInput);
	    	}
	    	else {
	    		executeGlobal(inputs);
	    	}
	    }
	    catch (Exception e) {
	    	System.err.println("GENERIC SWING ACTION ** ERROR ** - " + e.getMessage());
	    }
	}

	/**
	 * Primary (TOP LEVEL) Execute Method - SGA Swing Framework
	 * @param Object[] inputs
	 * @return boolean
	 * @throws Exception
	*/
	public boolean executeGlobal(Object[] inputs) throws Exception {

		boolean rval = false;
		
		//	1)	ONLY ALLOW VALID USER SESSIONS -
		//****************************************************************************/
		SwingSession session = SwingApplicationRuntime.getSession();
		if (!this.isLoginRequest) {
			Object userProfileObject = session.getAttribute(SwingApplicationConstants.USER_PROFILE);
			if (userProfileObject != null) {
				//	A)	Check if UserProfile is Logged In
				UserProfile profile = (UserProfile)userProfileObject;
				if (!profile.isAuthenticated()) {
					doLoginScreen();
				}
			}
			else {
				//	B)	UserProfile object not found in memory -
				doLoginScreen();
			}
		}
		else {
			// added 02-14-2005 >> Login Requests bypass security so that Login Action can extend framework
		}
		
		
		
		//	2)	ENFORCE ROLE-BASED SECURITY FOR A PARTICULAR ACTIVITY
		//****************************************************************************/

//		int requestedActivityID = 0;
		if (!this.isLoginRequest) {
			/** TODO **/
		}
		else {
			// added 02-14-2005 >> Login Requests bypass security so that Login Action can extend framework
		}
		
		
		
		
		//	3)	ACTIVATE SUB-SYSTEM APPLICATION 
		//****************************************************************************/
		//		-> Example: RDXGenericAction will lock-out RDX Application during
		//		-> scheduled batch processes
		try {
			rval = execute(inputs);
		}
		
		
		
		//	4)	HANDLE ERROR CONDITIONS
		//****************************************************************************/
		
	    catch (Exception e) {
	    	System.err.println("EXCEPTION THROWN BY ACTION CLASS * - " + e.getMessage());
	    }
	    
	    // CONTROL IS RETURNED TO EDT - << 
	    return rval;
	}

	/**
	 * Generic return to login screen - requires application init to register the login action
	*/
	private void doLoginScreen() {
		SwingCommonLoginActionInterface login = SwingApplicationRuntime.getRegisteredLoginAction();
		if (!this.isInitialReg) {
			login.doReturnToLoginScreen();
		}
	}

	/**
	 * Generic method - allow sub-classes to refresh / display a screen (JPanel)
	 * 
	 * Note: This method works much like show() in a card layout - where the screen
	 * being sent to this method is refreshed and set to visible on top of any other
	 * JPanels.
	 * 
	 * Simulate Card Layout Metaphor
	 * 
	 * @param CommonScreen - the screen to be displayed on top of the stack - make visible / repaint / validate
	 * @param boolean isApplet - allow the master container to determine whether or not this is an applet or desktop swing JFC
	 * @return void
	*/
	protected synchronized void displayScreen(CommonScreen screen, boolean isApplet) {
		
		JScrollPane scrollPane = new JScrollPane(screen);
		
		// UPDATE 04-05-2010 - Conditionally force no scrolling per Windows SEVEN issue
		scrollPane.setHorizontalScrollBarPolicy(SwingApplicationConstants.MASTER_FRAME_HORIZONTAL_SCROLLBAR_POLICY);
		scrollPane.setVerticalScrollBarPolicy(SwingApplicationConstants.MASTER_FRAME_VERTICAL_SCROLLBAR_POLICY);
		
		if (isApplet) {
			JApplet master = (JApplet)SwingApplicationRuntime.getMasterApplet();
			master.setContentPane(scrollPane);
			master.validate();
			master.repaint();
		}
		else {
			MasterJFrame master = (MasterJFrame)SwingApplicationRuntime.getMasterFrame();
			master.setContentPane(scrollPane);
			master.validate();
			master.repaint();
		}
		screen.setVisible(true);
	}

	/**
	 * Generic method - allow sub-classes to refresh / display a screen (JPanel)
	 * 
	 * Note: This method works much like show() in a card layout - where the screen
	 * being sent to this method is refreshed and set to visible on top of any other
	 * JPanels.
	 * 
	 * Simulate Card Layout Metaphor
	 * 
	 * Note: This method takes care to maintain a toolbar that is placed in the BorderLayout.NORTH
	 * Section of the main JPanel Content Pane.   As such, a required input parameter will be whatever
	 * screen was previously in the content pane so that it can be removed.
	 * 
	 * @param CommonScreen - the screen to be displayed on top of the stack - make visible / repaint / validate
	 * @param boolean isApplet - allow the master container to determine whether or not this is an applet or desktop swing JFC
	 * @return void
	*/
	protected synchronized void displayScreenWithToolbar(CommonScreen screen, boolean isApplet) {
		
		CommonScreen previousScreen = SwingApplicationRuntime.getCurrentlyActiveScreen();
		
		JScrollPane scrollPane = new JScrollPane(screen);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		if (isApplet) {
			JApplet master = (JApplet)SwingApplicationRuntime.getMasterApplet();
			if (previousScreen != null) {
				JScrollPane parent = getParentJScrollPane(previousScreen);
				if (parent != null) {
					master.remove(parent);
				}
			}
			master.getContentPane().add(scrollPane, BorderLayout.CENTER);
			master.validate();
			master.repaint();
		}
		else {
			MasterJFrame master = (MasterJFrame)SwingApplicationRuntime.getMasterFrame();
			if (previousScreen != null) {
				JScrollPane parent = getParentJScrollPane(previousScreen);
				if (parent != null) {
					master.remove(parent);
				}
			}
			
			master.getContentPane().add(screen, BorderLayout.CENTER);
//			master.getContentPane().add(scrollPane, BorderLayout.CENTER);
			
			master.validate();
			master.repaint();
		}
		screen.setVisible(true);
		
		SwingApplicationRuntime.setCurrentlyActiveScreen(screen);
	}

	/**
	 * Utility function - scan through component parents until stop at a JScrollpane
	 * Returns NULL if none found
	 * @param previousScreen
	 * @return JScrollPane
	*/
	private JScrollPane getParentJScrollPane(CommonScreen previousScreen) {
		JScrollPane jScrollParent = null;
		if (previousScreen != null) {
			Container parent = previousScreen.getParent();
			int attempts = 0;
			while (attempts < 50) {
				if (parent instanceof JScrollPane) {
					jScrollParent = (JScrollPane)parent;
					break;
				}
				else {
					parent = parent.getParent();
					attempts++;
				}
			}
		}
		return jScrollParent;
	}

	/**
	 * Convenience method for returning handle to master frame
	 * @param isApplet
	 * @return Component
	*/
	protected Component getMasterFrame(boolean isApplet) {
		Component masterFrame = null;
		if (isApplet) {
			masterFrame = SwingApplicationRuntime.getMasterApplet();
		}
		else {
			masterFrame = SwingApplicationRuntime.getMasterFrame();
		}
		return masterFrame;
	}

	/**
	 * Private utility method for getting the parent frame to support a popup dialogue
	 * 
	 * @param CommonScreen screen
	 * @return Frame
	 */
	protected Frame findParentFrame(CommonScreen screen){
		Container c = screen;
		while(c != null){
			if (c instanceof Frame) {
				return (Frame)c;
			}
			c = c.getParent();
		}
		return (Frame)null;
	}

	/**
	 * Primary Execute Method!
	 * @param Object[] inputs
	 * @return
	*/
	protected abstract boolean execute(Object[] inputs) throws Exception;

}
