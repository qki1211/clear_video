package io.quantumknight.video.framework.interfaces;
/********************************************************************************************
//* Filename: 		DragAndDropHandlerInterface.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    JFC/SWING FRAMEWORK - DRAG-N-DROP HANDLER INTERFACE
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
import java.awt.dnd.DragSource;

public interface DragAndDropHandlerInterface {

	/**
	 * This gets fired upon the focus of a registered element
	 * @param Component source
	 * @param Component target
	 * @return void
	*/
	public void doHandle(Component source, Component target);
	
	/**
	 * This gets fired upon the focus of a registered element
	 * @param Component source
	 * @param Component target
	 * @return void
	*/
	public void doHandle(String source, Component target);
	
	/**
	 * This gets fired upon the focus of a registered DropTarget
	 * @param source 
	 * @param target
	 */
	public void doHandleDragEnter(Component source, Component target);
	
	/**
	 * This gets fired upon focus lost of a registered DropTarget
	 * @param source
	 * @param target
	 */
	public void doHandleDragExit(Component target);
	
	/**
	 * This gets fired when a registered element has begun to be dragged
	 * @param dragSource
	 * @param source
	 */
	public void doHandleDragGestureRecognized(DragSource dragSource, Component source);
	
	/**
	 * This gets fired when a drag and drop action is complete
	 * @param source
	 */
	public void doHandleDragDropEnd(Component source);
}
