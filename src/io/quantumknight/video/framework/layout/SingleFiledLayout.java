package io.quantumknight.video.framework.layout;
/********************************************************************************************
//* Filename: 		SingleFiledLayout.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    JFC/SWING FRAMEWORK - CUSTOM LAYOUT MANAGER SUB-SYSTEM
//* 				
//* 				SingleFiledLayout lays out components singled filed.  This layout manager is
//* 				like FlowLayout except that all components are placed in a single row or column.
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

import java.awt.*;

public class SingleFiledLayout implements java.awt.LayoutManager, java.io.Serializable {

	private static final long serialVersionUID = -6272984621856402492L;

	/** Align components in a column */
	public static final int COLUMN = 0;

	/** Align components in a row */
	public static final int ROW = 1;

	/** Left justify components */
	public static final int LEFT = 0;

	/** Top justify components */
	public static final int TOP = 0;

	/** Center components */
	public static final int CENTER = 1;

	/** Full justify components */
	public static final int FULL = 2;

	/** Bottom justify components */
	public static final int BOTTOM = 3;

	/** Right justify components */
	public static final int RIGHT = 4;

	/** Default gap -- derived classes may override */
	public static int DEFAULT_GAP = 5;

	/** ROW or COLUMN -- should the components be aligned in a row or column */
	protected int orientation;

	/**
	 * LEFT, TOP, CENTER, FULL, BOTTOM, RIGHT -- how should components of different
	 * sizes be aligned
	 */
	protected int justification;

	/** Space between components in pixels */
	protected int gap;

	/**
	 * Constructs an instance of SingleFiledLayout that will align components in a
	 * column using the default gap and LEFT justification.
	 */

	public SingleFiledLayout() {
		this(COLUMN, LEFT, DEFAULT_GAP);
	}

	/**
	 * Constructs an instance of SingleFiledLayout using the default gap and LEFT or
	 * TOP justification.
	 *
	 * @param orientation ROW or COLUMN -- should the components be aligned in a row
	 *                    or column
	 */

	public SingleFiledLayout(int orientation) {
		this(orientation, LEFT, DEFAULT_GAP);
	}

	/**
	 * Constructs an instance of SingleFiledLayout.
	 *
	 * @param orientation   ROW or COLUMN -- should the components be aligned in a
	 *                      row or column
	 * @param justification LEFT, TOP, CENTER, FULL, BOTTOM, RIGHT -- how should
	 *                      components of different sizes be aligned
	 * @param gap           space between components in pixels
	 */

	public SingleFiledLayout(int orientation, int justification, int gap) {
		// Validate parameters
		if (orientation != ROW)
			orientation = COLUMN;

		if ((justification != CENTER) && (justification != FULL) && (justification != RIGHT))
			justification = LEFT;

		if (gap < 0)
			gap = 0;

		// Copy parameters
		this.orientation = orientation;
		this.justification = justification;
		this.gap = gap;
	}

//******************************************************************************
//** java.awt.event.LayoutManager methods                                    ***
//******************************************************************************

	/**
	 * To lay out the specified container using this layout. This method repositions
	 * the components in the specified target container.
	 *
	 * <p>
	 * User code should not have to call this method directly.
	 * </p>
	 *
	 * @param container container being served by this layout manager
	 */

	public void layoutContainer(Container container) {
		// Use preferred size to get maximum width or height
		Dimension size = container.getSize();

		// Get the container's insets
		Insets inset = container.getInsets();

		// Start at top left of container
		int x = inset.left;
		int y = inset.top;

		// Get the components inside the container
		Component component[] = container.getComponents();

		// Components arranged in a column
		if (orientation == COLUMN)
			// Add each component
			for (int counter = 0; counter < component.length; counter++) {
				// Use preferred size of component
				Dimension d = component[counter].getPreferredSize();

				// Align component
				switch (justification) {
				case LEFT:
					x = inset.left;
					break;

				case CENTER:
					x = ((size.width - d.width) >> 1) + inset.left - inset.right;
					break;

				case FULL:
					x = inset.left;
					d.width = size.width - inset.left - inset.right;
					break;

				case RIGHT:
					x = size.width - d.width - inset.right;
					break;
				}

				// Set size and location
				component[counter].setBounds(x, y, d.width, d.height);

				// Increment y
				y += d.height + gap;
			}
		// Components arranged in a row
		else
			// Add each component
			for (int counter = 0; counter < component.length; counter++) {
				// Use preferred size of component
				Dimension d = component[counter].getPreferredSize();

				// Align component
				switch (justification) {
				case TOP:
					y = inset.top;
					break;

				case CENTER:
					y = ((size.height - d.height) >> 1) + inset.top - inset.bottom;
					break;

				case FULL:
					y = inset.top;
					d.height = size.height - inset.top - inset.bottom;
					break;

				case BOTTOM:
					y = size.height - d.height - inset.bottom;
					break;
				}

				// Set size and location
				component[counter].setBounds(x, y, d.width, d.height);

				// Increment x
				x += d.width + gap;
			}
	}

	/**
	 * Determines the preferred size of the container argument using this layout.
	 * The preferred size is the smallest size that, if used for the container's
	 * size, will ensure that no component is truncated when the component is it's
	 * preferred size.
	 *
	 * @param container container being served by this layout manager
	 *
	 * @return a dimension indicating the container's preferred size
	 */

	public Dimension preferredLayoutSize(Container container) {
		int totalWidth = 0; // Width of all components
		int totalHeight = 0; // Height of all components

		// Get the components inside the container
		Component component[] = container.getComponents();

		// Components arranged in a column
		if (orientation == COLUMN) {
			// Add each component
			for (int counter = 0; counter < component.length; counter++) {
				Dimension d = component[counter].getPreferredSize();

				if (totalWidth < d.width)
					totalWidth = d.width;

				totalHeight += d.height + gap;
			}

			// Subtract extra gap
			totalHeight -= gap;
		}
		// Components arranged in a row
		else {
			// Add each component
			for (int counter = 0; counter < component.length; counter++) {
				Dimension d = component[counter].getPreferredSize();

				totalWidth += d.width + gap;

				if (totalHeight < d.height)
					totalHeight = d.height;
			}

			// Subtract extra gap
			totalWidth -= gap;
		}

		// Add insets to preferred size
		Insets inset = container.getInsets();
		totalWidth += inset.left + inset.right;
		totalHeight += inset.top + inset.bottom;

		return new Dimension(totalWidth, totalHeight);
	}

	/**
	 * Determines the minimum size of the container argument using this layout. The
	 * minimum size is the smallest size that, if used for the container's size,
	 * will ensure that no component is truncated. The minimum size is the preferred
	 * size.
	 *
	 * @param container container being served by this layout manager
	 *
	 * @return a dimension indicating the container's minimum size
	 */

	public Dimension minimumLayoutSize(Container container) {
		int totalWidth = 0; // Width of all components
		int totalHeight = 0; // Height of all components

		// Get the components inside the container
		Component component[] = container.getComponents();

		// Components arranged in a column
		if (orientation == COLUMN) {
			// Add each component
			for (int counter = 0; counter < component.length; counter++) {
				Dimension d = component[counter].getMinimumSize();

				if (totalWidth < d.width)
					totalWidth = d.width;

				totalHeight += d.height + gap;
			}

			// Subtract extra gap
			totalHeight -= gap;
		}
		// Components arranged in a row
		else {
			// Add each component
			for (int counter = 0; counter < component.length; counter++) {
				Dimension d = component[counter].getMinimumSize();

				totalWidth += d.width + gap;

				if (totalHeight < d.height)
					totalHeight = d.height;
			}

			// Subtract extra gap
			totalWidth = -gap;
		}

		// Add insets to preferred size
		Insets inset = container.getInsets();
		totalWidth += inset.left + inset.right;
		totalHeight += inset.top + inset.bottom;

		return new Dimension(totalWidth, totalHeight);
	}

	/**
	 * Adds the specified component with the specified name to the layout.
	 *
	 * @param parameter indicates entry's position and anchor
	 * @param component component to add
	 */

	public void addLayoutComponent(String name, Component component) {
	}

	/**
	 * Removes the specified component with the specified name from the layout.
	 *
	 * @param component component being removed
	 */

	public void removeLayoutComponent(Component component) {
	}

}
