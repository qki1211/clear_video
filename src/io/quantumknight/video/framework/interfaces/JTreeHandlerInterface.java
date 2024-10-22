package io.quantumknight.video.framework.interfaces;
/********************************************************************************************
//* Filename: 		JTreeHandlerInterface.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    JFC/SWING FRAMEWORK - JTREE EVENT HANDLER INTERFACE
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

import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;

public interface JTreeHandlerInterface {

	/**
	 * MAIN J-Tree TreeSelectionListener HANLDER METHOD *****************************
	 * @param Object obj
	 * @param ListSelectionEvent e the selection event
	 * @return void
	*/
	public void doHandleValueChanged(Object obj, TreeSelectionEvent e);
	
	/**
	 * J-Tree TreeWillExpandListener.treeWillCollapse() HANDLER METHOD
	 * @param obj
	 * @param e
	 */
	public void doHandleTreeWillCollapse(Object obj, TreeExpansionEvent e);
	
	/**
	 * J-Tree TreeWillExpandListener.treeWillExpand() HANDLER METHOD
	 * @param obj
	 * @param e
	 */
	public void doHandleTreeWillExpand(Object obj, TreeExpansionEvent e);
}
