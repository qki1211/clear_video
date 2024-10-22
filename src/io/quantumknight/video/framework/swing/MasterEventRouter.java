package io.quantumknight.video.framework.swing;
/********************************************************************************************
//* Filename: 		MasterEventRouter.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    JFC/SWING FRAMEWORK - FOCAL POINT / MVC EVENT ROUTER - ALL SWING EVENTS
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
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceContext;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DropTargetContext;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.dnd.InvalidDnDOperationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JTree;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.ExpandVetoException;

import io.quantumknight.video.framework.constants.SwingApplicationConstants;
import io.quantumknight.video.framework.interfaces.ButtonHandlerInterface;
import io.quantumknight.video.framework.interfaces.CheckboxHandlerInterface;
import io.quantumknight.video.framework.interfaces.ComboBoxHandlerInterface;
import io.quantumknight.video.framework.interfaces.DocumentHandlerInterface;
import io.quantumknight.video.framework.interfaces.DragAndDropHandlerInterface;
import io.quantumknight.video.framework.interfaces.FocusHandlerInterface;
import io.quantumknight.video.framework.interfaces.JTabbedPaneHandlerInterface;
import io.quantumknight.video.framework.interfaces.JTableHandlerInterface;
import io.quantumknight.video.framework.interfaces.JTreeHandlerInterface;
import io.quantumknight.video.framework.interfaces.KeyPressHandlerInterface;
import io.quantumknight.video.framework.interfaces.MenuItemHandlerInterface;
import io.quantumknight.video.framework.interfaces.MenuListenerInterface;
import io.quantumknight.video.framework.interfaces.MouseHandlerInterface;
import io.quantumknight.video.framework.interfaces.RadioButtonHandlerInterface;
import io.quantumknight.video.framework.interfaces.SliderChangeHandlerInterface;
import io.quantumknight.video.framework.interfaces.SystemTrayHandlerInterface;

public class MasterEventRouter implements ActionListener, ItemListener, KeyListener, MouseListener, FocusListener, ChangeListener, DragGestureListener, DragSourceListener, DropTargetListener, ListSelectionListener, TreeSelectionListener, TreeWillExpandListener, DocumentListener, MouseMotionListener, MenuListener {

	private static MasterEventRouter _INSTANCE = new MasterEventRouter();	// Singleton THIS
	
	@SuppressWarnings("rawtypes")
	private ArrayList registeredEventHandlers;
	
	/** 
	 * Private Constructor used to make this object a strict singleton 
	 * 
	*/
	@SuppressWarnings("rawtypes")
	private MasterEventRouter() {
		super();
		this.registeredEventHandlers = new ArrayList();
	}
	
	/**
	 * Get Instance Returns the singleton instance of this class 
	 * 
	*/
	public static MasterEventRouter getInstance() {
		return _INSTANCE;
	}
	
	/**
	 * Lookup a registered event handler from internal ArrayList
	 * @param Object eventHandlerInterface
	 * @return Object
	*/
	private Object getHandler(Class<?> eventHandlerInterface) {
	
	    Object returnObj = null;
	    Iterator<?> it = this.registeredEventHandlers.iterator();
	    while (it.hasNext()) {
	        Object reg = it.next();
	        if ((eventHandlerInterface.isInterface()) && (eventHandlerInterface.isInstance(reg))) {
	            returnObj = reg;
	            break;
	        }
	    }
	    return returnObj;
	}
	
	/**
	 * Register an Event Handler
	 * @param Object handler
	 * @return void
	*/
	@SuppressWarnings("unchecked")
	public synchronized void registerEventHandler(Object handler) {
	    
	    boolean duplicateFound = false;
	    Iterator<?> it = this.registeredEventHandlers.iterator();
	    while (it.hasNext()) {
	        Object reg = it.next();
	        Class<?> handlerClass = handler.getClass();
	        if (handlerClass.isInstance(reg)) {
	            duplicateFound = true;
	        }
	    }
	    if (!duplicateFound) {
	        this.registeredEventHandlers.add(handler);
	        if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Registered Handler: " + handler.getClass().getName());}
	    }
	}
	
	/**
	 * Button Pressing Event Handler
	*/
	public void actionPerformed(ActionEvent event) {
	
		Object obj = event.getSource();
		String cmd = event.getActionCommand();
	    String name = "";
	    
		ButtonHandlerInterface buttonHandler = (ButtonHandlerInterface)this.getHandler(ButtonHandlerInterface.class);
	
	    if (buttonHandler == null) {
		    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a JButton-Press - Could Not Find a Registered Button Handler!");}
		    return;
		}
		
		if (obj instanceof JButton) {
		    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a JButton-Press");}
			JButton button = (JButton)obj;
			name = button.getName();
	        buttonHandler.doHandle(button,name,cmd);
		}
		else if (obj instanceof JToggleButton) {
		    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a JToggleButton-Click");}
			JToggleButton button = (JToggleButton)obj;
			name = button.getName();
	        buttonHandler.doHandle(button,name,cmd);
		}
		else if (obj instanceof JRadioButton) {
		    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a JRadioButton-Click");}
		    JRadioButton button = (JRadioButton)obj;
			name = button.getName();
	        buttonHandler.doHandle(button,name,cmd);
		}
	    else if (obj instanceof JMenuItem) {
	        if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a JMenuItem-Selection");}
	        JMenuItem item = (JMenuItem)obj;
	        name = item.getName();
	        MenuItemHandlerInterface menuItemHandler = (MenuItemHandlerInterface)this.getHandler(MenuItemHandlerInterface.class);
	        if (menuItemHandler == null) {
	            if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a MenuItem Selection - Could Not Find a Registered MenuItem Handler!");}
	            return;
	        }
	        menuItemHandler.doHandle(item,name,cmd);
	    }
		else {
	        //  Handle components that may be thrown from non-standard JARS outside of OSX Framework
	        //  Example:    "WinTrayIconService" is a runtime instance of "obj" that might be received
	        //              from an application that uses JDIC.JAR and the Windows System Tray metaphor.
	        //              In this case, introspect the action event for usable generic details.
	        if (cmd.equalsIgnoreCase(SwingApplicationConstants.SYSTRAY_DEFAULT_CMD)) {
	            if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a WinTrayIconService-Selection");}
	            SystemTrayHandlerInterface systemTrayHandler = (SystemTrayHandlerInterface)this.getHandler(SystemTrayHandlerInterface.class);
	            if (systemTrayHandler == null) {
	                if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a WinTrayIconService-Click - Could Not Find a Registered SystemTray Handler!");}
	                return;
	            }
	            systemTrayHandler.doHandle(obj,cmd);
	        }
	        else {
	            if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.err.println("object not understood");}
	            return;
	        }
		}
	}
	
	/**
	 * Handles Events specifically related to the drop-down box's
	*/
	public void itemStateChanged(ItemEvent evt) {
		
		int state = evt.getStateChange();	// selected/deselected
		
		if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted an \"itemStateChanged\" event");}
		
		if (evt.getSource() instanceof JComboBox) {
			ComboBoxHandlerInterface handler = (ComboBoxHandlerInterface)this.getHandler(ComboBoxHandlerInterface.class);
			handler.doHandle((JComboBox<?>)evt.getSource(),evt.getItem(),state);
		}
		else if (evt.getSource() instanceof JCheckBox) {
			CheckboxHandlerInterface handler = (CheckboxHandlerInterface)this.getHandler(CheckboxHandlerInterface.class);
			handler.doHandle((JCheckBox)evt.getSource(),evt.getItem(),state);
		}
		else if (evt.getSource() instanceof JRadioButton) {
			RadioButtonHandlerInterface handler = (RadioButtonHandlerInterface)this.getHandler(RadioButtonHandlerInterface.class);
			handler.doHandle((JRadioButton)evt.getSource(),evt.getItem(),state);
		}
		else {
			if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted an \"itemStateChanged\" event - Could Not Find a Registered Handler!");}
		}
	}
	
	/*****************************************************/
	/*****************************************************/
	
	/**
	 * Handles Java2 Drag and Drop Events - PRIMARY DRAG-GESTURE LISTENER
	 * This listener will start the drag and has access to the top level DRAG SOURCE LISTENER and DRAG SOURCE
	*/
	public void dragGestureRecognized(DragGestureEvent e) {
	
		//	STEP 1) VALIDATE LOCATION IS OK
		if (e.getDragAction() == 0) {
			return; // invalid - end drag gesture
		}
	
		//	STEP 2)	CAPTURE THE ELEMENT'S DATA & PUT IT INSIDE A CLIPBOARDTRANSFERVO
		ClipboardTransferVO transferable = new ClipboardTransferVO(e.getComponent());
	
		
		//	STEP 2.5) ADD GHOSTED SEMI-TRANSPARENT IMAGE OF ITEM BEING DRAGGED
	//	Point ptDragOrigin = e.getDragOrigin();
	//	JPanel src = (JPanel)e.getSource();
	//	
	//	BufferedImage _imgGhost = new BufferedImage(
	//		(int)src.getWidth(),
	//		(int)src.getHeight(),
	//		BufferedImage.TYPE_INT_ARGB_PRE
	//	);
	//	
	//	Graphics2D g2 = _imgGhost.createGraphics();
	//	g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 0.5f));
	//	src.paint(g2);
	//	
	
		
		//	STEP 3)	KICK-OFF THE DRAG EVENT
		try {
			
			// Kick off Ghosted Image Drag and drop
			DragAndDropHandlerInterface handler = (DragAndDropHandlerInterface)this.getHandler(DragAndDropHandlerInterface.class);
			if (handler == null) {
			    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a Drag-and-Drop *DROP EVENT* - Could Not Find a Registered Drag-and-Drop Handler!");}
			    return;
			} else {
				handler.doHandleDragGestureRecognized(e.getDragSource(), e.getComponent());
			}
			
			// DnD for JTree already started
			if (!(e.getComponent() instanceof JTree)) {
				e.startDrag(DragSource.DefaultMoveDrop,transferable,this);
			}
	//		BufferedImage image = new BufferedImage(e.getComponent().getWidth( ),
	//		e.getComponent().getHeight( ), 
	//		BufferedImage.TYPE_INT_ARGB);
	//		Graphics g = image.getGraphics( );
	//		e.getComponent().paint(g);
	//
	//		Point p = (Point) e.getComponent().getLocation().clone();
	//		e.startDrag(DragSource.DefaultMoveDrop, image, p, transferable, this);
			
			
		}
		catch(InvalidDnDOperationException idoe) {
		    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a Drag-and-Drop Gesture - Returned Invalid Operation!\n" + idoe.getMessage());}
		    return;
		}
	}
	
	/*****************************************************/
	/*****************************************************/
	
	/**
	 * Handles Java2 Drag and Drop Events - SECONDARY DRAG SOURCE LISTENER (LOWER LEVEL)
	 * Manage the drag source end of a drop event
	 * @param e the event
	*/
	public void dragDropEnd(DragSourceDropEvent e) {
		
		// Terminate ghosted image drag and drop support
		DragAndDropHandlerInterface handler = (DragAndDropHandlerInterface)this.getHandler(DragAndDropHandlerInterface.class);
		if (handler == null) {
		    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a Drag-and-Drop *DROP EVENT* - Could Not Find a Registered Drag-and-Drop Handler!");}
		    return;
		} else {
			handler.doHandleDragDropEnd(e.getDragSourceContext().getComponent());
		}
		
		if(e.getDropSuccess() == false) {
			if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a Drag-and-Drop *END EVENT* - Not Successful!");}
			return;
		}
		
		// NOTE: Success criteria is that the DROPACTION should be what the DROP TARGET specified in ACCEPTDROP()
		if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a Drag-and-Drop *END EVENT* : OK " + e.getDropAction());}
	}
	
	/**
	 * Handles Java2 Drag and Drop Events - SECONDARY DRAG SOURCE LISTENER (LOWER LEVEL)
	 * Manage the intersection of the users selected action, and the source and target actions
	 * @param e the event
	*/
	public void dragEnter(DragSourceDragEvent e) {
		DragSourceContext context = e.getDragSourceContext();
		int myaction = e.getDropAction();
		if(myaction != 0) {
			// this could be a [+] label
			context.setCursor(DragSource.DefaultCopyDrop);
			
		}
		else {
			// this could be something else
			context.setCursor(DragSource.DefaultCopyNoDrop);
		}
	}
	
	/**
	 * Handles Java2 Drag and Drop Events - SECONDARY DRAG SOURCE LISTENER (LOWER LEVEL)
	 * Manage the drag source over the target
	 * @param e the event
	*/
	public void dragOver(DragSourceDragEvent e) {
		// no implementation
	}
	
	/**
	 * Handles Java2 Drag and Drop Events - SECONDARY DRAG SOURCE LISTENER (LOWER LEVEL)
	 * Manage drag source Exit Event
	 * @param e the event
	*/
	public void dragExit(DragSourceEvent e) {
		DragSourceContext context = e.getDragSourceContext();
		context.setCursor(DragSource.DefaultMoveDrop);  // change back to a [] move cursor
		
	}
	
	/**
	 * Handles Java2 Drag and Drop Events - SECONDARY DRAG SOURCE LISTENER (LOWER LEVEL)
	 * Manage change events during drop - 
	 * for example, press shift during drag to change to a link action
	 * @param e the event     
	 */
	public void dropActionChanged (DragSourceDragEvent e) {
		//DragSourceContext context = e.getDragSourceContext();
		//context.setCursor(DragSource.DefaultCopyNoDrop);
	}
	
	/*****************************************************/
	/*****************************************************/
	
	/**
	 * Handles DND *DROP* Events - SECONDARY DROP TARGET LISTENER (LOWER LEVEL)
	 * Called by isDragOk - Checks to see if the flavor drag flavor is acceptable
	 * @param e the event
	*/
	public void dragEnter(DropTargetDragEvent e) {
		try {
			
			DataFlavor chosen = new DataFlavor(Component.class, "component");
			Object data = null;
			data = e.getTransferable().getTransferData(chosen);
			
			if (data != null) {
				DragAndDropHandlerInterface handler = (DragAndDropHandlerInterface)this.getHandler(DragAndDropHandlerInterface.class);
				if (handler == null) {
				    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a Drag-and-Drop *DROP EVENT* - Could Not Find a Registered Drag-and-Drop Handler!");}
				    return;
				}
				
				DropTargetContext targetCtx = e.getDropTargetContext();
				Component target = targetCtx.getComponent();
				Component source = (Component) data;
				
				handler.doHandleDragEnter(source,target);
			} 
			else {
				if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a Drag-and-Drop *DRAG ENTER EVENT* - Could Not Find a Transferrable Component!");}
			    return;
			}
			
		} catch (Exception ex) {
			if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a Drag-and-Drop *DROP EVENT* - Could Not Find a Registered Drag-and-Drop Handler!");}
		}
	}
	
	/**
	 * Handles DND *DROP* Events - SECONDARY DROP TARGET LISTENER (LOWER LEVEL)
	 * continue "drag under" feedback on component
	 * @param e the event
	*/
	public void dragOver(DropTargetDragEvent e) {
		//e.rejectDrag();
		//e.acceptDrag(e.getDropAction());
	//	System.out.println("DRAG OVER Event....");
	}
	
	/**
	 * Handles DND *DROP* Events - SECONDARY DROP TARGET LISTENER (LOWER LEVEL)
	 * continue "drag under" feedback on component
	 * @param e the event
	*/
	public void dropActionChanged(DropTargetDragEvent e) {
		//e.rejectDrag();
		//e.acceptDrag(e.getDropAction());
	}
	
	/**
	 * Handles DND *DROP* Events - SECONDARY DROP TARGET LISTENER (LOWER LEVEL)
	 * @param e the event
	*/
	public void dragExit(DropTargetEvent e) {
		// do some action - change border color for example
		try {
			DragAndDropHandlerInterface handler = (DragAndDropHandlerInterface)this.getHandler(DragAndDropHandlerInterface.class);
			if (handler == null) {
				if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a Drag-and-Drop *DROP EVENT* - Could Not Find a Registered Drag-and-Drop Handler!");}
			    return;
			}
			
			DropTargetContext targetCtx = e.getDropTargetContext();
			Component target = targetCtx.getComponent();
			
			handler.doHandleDragExit(target);
			
		}
		catch(Exception ex) {
			if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a Drag-and-Drop *DROP EVENT* : FAILED! -> " + ex.getMessage());}
		}
	}
	
	/**
	 * Handles DND *DROP* Events - SECONDARY DROP TARGET LISTENER (LOWER LEVEL)
	 * Perform action from getSourceActions on the transferrable
	 * and invoke acceptDrop or rejectDrop
	 * invoke dropComplete if its a local (same JVM) transfer, use ClipboardTrasnferVO.componentFlavor
	 * find a match for the flavor check the operation
	 * get the transferable according to the chosen flavor
	 * do the transfer
	 * @param e the event
	*/
	public void drop(DropTargetDropEvent e) {
	
		try {
			/* the source listener receives this action in dragDropEnd.  
			 * If the action is DnDConstants.ACTION_COPY_OR_MOVE then the source receives MOVE!
			*/
			boolean isSourceComponent = false;
			boolean isSourceString = false;
			e.acceptDrop(DnDConstants.ACTION_MOVE);
			
			DataFlavor chosen = null;
			
			if (e.getTransferable().isDataFlavorSupported(new DataFlavor(Component.class,"component")))  {
				chosen = new DataFlavor(Component.class,"component");
				isSourceComponent = true;
			}
			else if (e.getTransferable().isDataFlavorSupported(new DataFlavor(String.class,"string"))) {
				chosen = new DataFlavor(String.class,"string");
				isSourceString = true;
			}
			 
			Object data = e.getTransferable().getTransferData(chosen);
	
			if (data != null) {
				
				DragAndDropHandlerInterface handler = (DragAndDropHandlerInterface)this.getHandler(DragAndDropHandlerInterface.class);
				if (handler == null) {
				    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a Drag-and-Drop *DROP EVENT* - Could Not Find a Registered Drag-and-Drop Handler!");}
				    return;
				}
				
				DropTargetContext targetCtx = e.getDropTargetContext();
				Component target = targetCtx.getComponent();
				
				if (isSourceComponent) {
					Component source = (Component)data;
				
					handler.doHandle(source,target);
				}
				else if (isSourceString) {
					String source = (String) data;
					handler.doHandle(source, target);
				}
				e.dropComplete(true);
			}
			else {
				if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a Drag-and-Drop *DROP EVENT* : FAILED! -> Could Not Receive Transfer Data From Clipboard!");}
				e.dropComplete(false);
			}
		}
		catch(Exception ex) {
			if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a Drag-and-Drop *DROP EVENT* : FAILED! -> " + ex.getMessage());}
		}
	}
	
	/*****************************************************/
	/*****************************************************/
	
	
	/** 
	 * Event Handler that limits to maxlength
	*/
	public void keyPressed(KeyEvent e) {
	    
	    int keyCode = e.getKeyCode();	// ie: '10' is "Enter Key"
	    @SuppressWarnings("unused")
		char c = e.getKeyChar();
	    JComponent component = (JComponent)e.getSource();
	
	    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a KeyPress");}
	
		KeyPressHandlerInterface handler = (KeyPressHandlerInterface)this.getHandler(KeyPressHandlerInterface.class);
		if (handler == null) {
		    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a KeyPress - Could Not Find a Registered KeyPress Handler!");}
		    return;
		}
		handler.doHandle(keyCode,component,e);
	}
	
	/**
	 * Invoked when a key is released
	*/
	public void keyReleased(KeyEvent e) { 
		// System.err.println("key released");
	}
	
	/**
	 * Invoked when a key is pressed
	*/
	public void keyTyped(KeyEvent e) {
		// System.err.println("key typed");
	}
	
	/**
	 * Invoked when the mouse has been clicked on a component.
	 * @param MouseEvent e
	 * @return void
	*/
	public void mouseClicked(MouseEvent e) {
		
		Object obj = e.getSource();
		
		MouseHandlerInterface handler = (MouseHandlerInterface)this.getHandler(MouseHandlerInterface.class);
		if (handler == null) {
		    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a MouseClick over a JLabel - Could Not Find a Registered MouseClick Handler!");}
		    return;
		}
		
		if (obj instanceof JLabel) {	// also handle clicking of JLabel
		    
		    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a MouseClick over a JLabel");}
		    JLabel label = (JLabel)obj;
		    handler.doHandle(label,e);
		    
		    // NOTE - BREAKING CHANGE (11/2023) TO EARLIER IMPLEMENTATIONS OF LD FRAMEWORK
		    
//			Component parent = label.getParent();
//			if (parent instanceof Object) {
//			    handler.doHandle(label,e);
//			}
		}
		else {
			if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a MouseClick over a Component");}
			handler.doHandle(obj,e);
		}
	}
	
	/**
	 * Invoked when a component gains focus.
	*/
	public void focusGained(FocusEvent e) {
	    
		Object obj = e.getSource();
		String name = "";
		//String cmd = "";
		
		FocusHandlerInterface handler = (FocusHandlerInterface)this.getHandler(FocusHandlerInterface.class);
		if (handler == null) {
		    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a Focus Event - Could Not Find a Registered Focus Handler!");}
		    return;
		}
		if (obj instanceof JLabel) {
		    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a JLabel Focus Event");}
		    JLabel label = (JLabel)obj;
			name = label.getName();
			handler.doHandle(label,name);
		}
		else if (obj instanceof JTextField) {
		    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a JTextField Focus Event");}
		    JTextField textField = (JTextField)obj;
			name = textField.getName();
			handler.doHandle(textField,name);
		}
		else {
		    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.err.println("object not understood");}
			return;
		}
	}
	
	/**
	 * Invoked when a component loses focus.
	*/
	public void focusLost(FocusEvent e) { 
		
		Object obj = e.getSource();
		String name = "";
		//String cmd = "";
		
		FocusHandlerInterface handler = (FocusHandlerInterface)this.getHandler(FocusHandlerInterface.class);
		if (handler == null) {
		    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a Focus Event - Could Not Find a Registered Focus Handler!");}
		    return;
		}
		if (obj instanceof JLabel) {
		    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a JLabel Focus Event");}
		    JLabel label = (JLabel)obj;
			name = label.getName();
			handler.doHandle(label,name);
		}
		else if (obj instanceof JTextField) {
		    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a JTextField Focus Event");}
		    JTextField textField = (JTextField)obj;
			name = textField.getName();
			handler.doHandle(textField,name);
		}
		else {
		    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.err.println("object not understood");}
			return;
		}
	}
	
	/**
	 * Invoked when the mouse enters a component.
	 * @param MouseEvent e
	 * @return void
	*/
	public void mouseEntered(MouseEvent e) { 
	
		Object obj = e.getSource();
		
		MouseHandlerInterface handler = (MouseHandlerInterface)this.getHandler(MouseHandlerInterface.class);
		if (handler == null) {
		    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a Mouse Enter from a Component - Could Not Find a Registered Mouse Handler!");}
		    return;
		}
		
		handler.doHandleMouseOver(obj,e);
	}
	
	/**
	 * Invoked when the mouse exits a component.
	 * @param MouseEvent e
	 * @return void
	*/
	public void mouseExited(MouseEvent e) { 
	
		Object obj = e.getSource();
		
		MouseHandlerInterface handler = (MouseHandlerInterface)this.getHandler(MouseHandlerInterface.class);
		if (handler == null) {
		    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a Mouse Exit from a Component - Could Not Find a Registered Mouse Handler!");}
		    return;
		}
		
		handler.doHandleMouseOut(obj,e);
	}
	
	/**
	 * Invoked when a JTable list is changed / sorted
	 * @param ListSelectionEvent e
	 * @return void
	*/
	public void valueChanged(ListSelectionEvent e) {
		
		Object obj = e.getSource();
		
		JTableHandlerInterface handler = (JTableHandlerInterface)this.getHandler(JTableHandlerInterface.class);
		if (handler == null) {
		    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a ListSelectionEvent from a JTable Component - Could Not Find a Registered JTable Handler!");}
		    return;
		}
		
		handler.doHandleValueChanged(obj,e);
	}
	
	/**
	 * Invoked when a mouse button has been pressed on a component.
	 * @param MouseEvent e
	 * @return void
	*/
	public void mousePressed(MouseEvent e) { 
		Object obj = e.getSource();
		
		MouseHandlerInterface handler = (MouseHandlerInterface)this.getHandler(MouseHandlerInterface.class);
		if (handler == null) {
		    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a Mouse Exit from a Component - Could Not Find a Registered Mouse Handler!");}
		    return;
		}
		
		handler.doHandleMousePressed(obj,e);
	}
	
	/**
	 * Invoked when a mouse button has been released on a component.
	 * @param MouseEvent e
	 * @return void
	*/
	public void mouseReleased(MouseEvent e) { 
		Object obj = e.getSource();
		
		MouseHandlerInterface handler = (MouseHandlerInterface)this.getHandler(MouseHandlerInterface.class);
		if (handler == null) {
		    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a Mouse Exit from a Component - Could Not Find a Registered Mouse Handler!");}
		    return;
		}
		
		handler.doHandleMouseReleased(obj,e);
	}
	
	/**
	 * Invoked when a JSlider is moved or JTabbedPane index is modified
	*/
	public void stateChanged(ChangeEvent e) {
	    
		Object obj = e.getSource();
		
		if (obj instanceof JSlider) {
 	        
			if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a JSlider Change Event");}
 	        
			SliderChangeHandlerInterface handler = (SliderChangeHandlerInterface)this.getHandler(SliderChangeHandlerInterface.class);
		    
		    if (handler == null) {
		        if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a JSlider Change Event - Could Not Find a Registered Event Handler!");}
		        return;
		    }
		    else {
		    	JSlider slider = (JSlider)obj;
	 	        handler.doHandle(slider,slider.getName());
		    }
 	    }
 	    else if (obj instanceof JTabbedPane) {
 	    	
	        if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a JTabbedPane Index-Change Event");}
	        
	        JTabbedPaneHandlerInterface handler = (JTabbedPaneHandlerInterface)this.getHandler(JTabbedPaneHandlerInterface.class);
	        
	        if (handler == null) {
		        if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a JTabbedPane Index-Change Event - Could Not Find a Registered Event Handler!");}
		        return;
		    }
		    else {
		    	JTabbedPane jTabbedPane = (JTabbedPane)obj;
		        handler.doHandle(jTabbedPane, jTabbedPane.getName());
		    }
	    }
 	    else {
 	        if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.err.println("object not understood");}
 	        return;
 	    }
	}
	
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		Object obj = e.getSource();
		
		JTreeHandlerInterface handler = (JTreeHandlerInterface)this.getHandler(JTreeHandlerInterface.class);
		if (handler == null) {
		    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a TreeSelectionEvent from a JTree Component - Could Not Find a Registered JTree Handler!");}
		    return;
		}
		
		handler.doHandleValueChanged(obj,e);
		
	}
	
	@Override
	public void treeWillCollapse(TreeExpansionEvent e)
			throws ExpandVetoException {
		Object obj = e.getSource();
		
		JTreeHandlerInterface handler = (JTreeHandlerInterface)this.getHandler(JTreeHandlerInterface.class);
		if (handler == null) {
		    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a TreeExpansionEvent from a JTree Component - Could Not Find a Registered JTree Handler!");}
		    return;
		}
		
		handler.doHandleTreeWillCollapse(obj,e);
		
	}
	
	@Override
	public void treeWillExpand(TreeExpansionEvent e) throws ExpandVetoException {
		Object obj = e.getSource();
		
		JTreeHandlerInterface handler = (JTreeHandlerInterface)this.getHandler(JTreeHandlerInterface.class);
		if (handler == null) {
		    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a TreeExpansionEvent from a JTree Component - Could Not Find a Registered JTree Handler!");}
		    return;
		}
		
		handler.doHandleTreeWillExpand(obj,e);
		
	}
	
	@Override
	public void changedUpdate(DocumentEvent e) {
	
		DocumentHandlerInterface handler = (DocumentHandlerInterface) this.getHandler(DocumentHandlerInterface.class);
		if (handler == null ) {
			if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a DocumentEvent from a Textfield Component - Could Not Find a Registered Document Handler!");}
		    return;
		}
		
		handler.doHandle(e.getDocument(), e.getType());
	}
	
	@Override
	public void insertUpdate(DocumentEvent e) {
		DocumentHandlerInterface handler = (DocumentHandlerInterface) this.getHandler(DocumentHandlerInterface.class);
		if (handler == null ) {
			if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a DocumentEvent from a Textfield Component - Could Not Find a Registered Document Handler!");}
		    return;
		}
		
		handler.doHandle(e.getDocument(), e.getType());
		
	}
	
	@Override
	public void removeUpdate(DocumentEvent e) {
		DocumentHandlerInterface handler = (DocumentHandlerInterface) this.getHandler(DocumentHandlerInterface.class);
		if (handler == null ) {
			if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a DocumentEvent from a Textfield Component - Could Not Find a Registered Document Handler!");}
		    return;
		}
		
		handler.doHandle(e.getDocument(), e.getType());
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		Object obj = e.getSource();
		
		MouseHandlerInterface handler = (MouseHandlerInterface)this.getHandler(MouseHandlerInterface.class);
		if (handler == null) {
		    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a Mouse Exit from a Component - Could Not Find a Registered Mouse Handler!");}
		    return;
		}
		
		handler.doHandleMouseDragged(obj,e);
		
	//	// GABE
	//	GhostImageDragHandlerInterface gHandler = (GhostImageDragHandlerInterface)this.getHandler(GhostImageDragHandlerInterface.class);
	//	if (gHandler != null)
	//		gHandler.doHandleMouseDragged(obj, e);
		
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
	//	// GABE
	//	GhostImageDragHandlerInterface gHandler = (GhostImageDragHandlerInterface)this.getHandler(GhostImageDragHandlerInterface.class);
	//	if (gHandler != null)
	//		gHandler.doHandleMouseDragged(e.getSource(), e);
	}
	
	@Override
	public void menuCanceled(MenuEvent e) {
		JMenu menu = (JMenu)e.getSource();
		MenuListenerInterface handler = (MenuListenerInterface)this.getHandler(MenuListenerInterface.class);
		if (handler == null) {
		    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a JMenu Canceled from a Component - Could Not Find a Registered Menu Handler!");}
		    return;
		}
		handler.doHandleMenuCanceled(menu,e);
	}
	
	@Override
	public void menuDeselected(MenuEvent e) {
		JMenu menu = (JMenu)e.getSource();
		MenuListenerInterface handler = (MenuListenerInterface)this.getHandler(MenuListenerInterface.class);
		if (handler == null) {
		    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a JMenu Deselected from a Component - Could Not Find a Registered Menu Handler!");}
		    return;
		}
		handler.doHandleMenuDeselected(menu,e);
	}
	
	@Override
	public void menuSelected(MenuEvent e) {
		JMenu menu = (JMenu)e.getSource();
		MenuListenerInterface handler = (MenuListenerInterface)this.getHandler(MenuListenerInterface.class);
		if (handler == null) {
		    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("Master Event Handler Interpretted a JMenu selected from a Component - Could Not Find a Registered Menu Handler!");}
		    return;
		}
		handler.doHandleMenuSelected(menu,e);
	}
}
