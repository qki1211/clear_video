package io.quantumknight.video.framework.interfaces;
/********************************************************************************************
//* Filename: 		MouseHandlerInterface.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    JFC/SWING FRAMEWORK - MOUSE EVENT HANDLER INTERFACE
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

import java.awt.event.MouseEvent;

public interface MouseHandlerInterface {
	
	/**
	 * MAIN MOUSE-CLICK HANLDER METHOD *****************************
	 * @param Object obj
	 * @param MouseEvent e
	 * @return void
	*/
	public void doHandle(Object obj, MouseEvent e);
	
	/**
	 * MAIN MOUSE-OVER HANLDER METHOD ******************************
	 * @param Object obj
	 * @parm MouseEvent e
	 * @return void
	*/
	public void doHandleMouseOver(Object obj, MouseEvent e);
	
	/**
	 * MAIN MOUSE-OUT HANLDER METHOD *******************************
	 * @param Object obj
	 * @param MouseEvent e
	 * @return void
	*/
	public void doHandleMouseOut(Object obj, MouseEvent e);
	
	/**
	 * MAIN MOUSE-PRESSED HANLDER METHOD *******************************
	 * @param Object obj
	 * @param MouseEvent e
	 * @return void
	*/
	public void doHandleMousePressed(Object obj, MouseEvent e);
	
	/**
	 * MAIN MOUSE-RELEASED HANLDER METHOD *******************************
	 * @param Object obj
	 * @param MouseEvent e
	 * @return void
	*/
	public void doHandleMouseReleased(Object obj, MouseEvent e);
	
	/**
	 * MAIN MOUSE-DRAGGED HANLDER METHOD *******************************
	 * @param Object obj
	 * @param MouseEvent e
	 * @return void
	*/
	public void doHandleMouseDragged(Object obj, MouseEvent e);
}
