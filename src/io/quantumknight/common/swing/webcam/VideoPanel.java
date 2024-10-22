package io.quantumknight.common.swing.webcam;
/********************************************************************************************
//* Filename: 		VideoPanelStatic.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    Panel - Video Display from Binary Source
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

import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.KEY_INTERPOLATION;
import static java.awt.RenderingHints.KEY_RENDERING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_OFF;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;
import static java.awt.RenderingHints.VALUE_INTERPOLATION_BILINEAR;
import static java.awt.RenderingHints.VALUE_RENDER_SPEED;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import io.quantumknight.video.framework.swing.ImageTile;


public class VideoPanel extends JPanel {

	private static final long serialVersionUID = 2298450706652197336L;
	
	public static final Map<RenderingHints.Key, Object> DEFAULT_IMAGE_RENDERING_HINTS = new HashMap<RenderingHints.Key, Object>();
	static {
		DEFAULT_IMAGE_RENDERING_HINTS.put(KEY_INTERPOLATION, VALUE_INTERPOLATION_BILINEAR);
		DEFAULT_IMAGE_RENDERING_HINTS.put(KEY_RENDERING, VALUE_RENDER_SPEED);
		DEFAULT_IMAGE_RENDERING_HINTS.put(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
	}
	
	/**
	 * Rendering hints to be used when painting image to be displayed.
	 */
	private Map<RenderingHints.Key, Object> imageRenderingHints = new HashMap<RenderingHints.Key, Object>(DEFAULT_IMAGE_RENDERING_HINTS);
	
	/**
	 * Buffered image resized to fit into panel drawing area.
	 */
	private BufferedImage resizedImage = null;

	
	private boolean updated = false;
	
	protected String screenName;
	
  /**
   * Constructor.
   *
  */
  public VideoPanel(String screenName) {
      this.screenName = screenName;
      initializeGUI();
  }

  /**
   * Initialize GUI.
   */
  public void initializeGUI() {
  	
  	
  	// ENABLE DOUBLEBUFFERING
  	setDoubleBuffered(true);
  	
  	
  	this.setVisible(true);
  	
  }
  
  /**
   * Refresh screen.
   *
   * @param userProfiles the user profiles
   * @return void
   */
  public void refreshScreen(Object obj) throws Exception {
  	if (obj != null) {
  		
  		// CONVERT TO BUFFERED IMAGE
  		if (obj instanceof byte[]) {
  			this.resizedImage = createImageFromBytes((byte[])obj);
  		}
  		else if (obj instanceof BufferedImage) {
  			
  			this.resizedImage = (BufferedImage)obj;
  		}
  		else if (obj instanceof ImageTile) {
  			ImageTile it = (ImageTile)obj;
  			ImageIcon icon = it.getIcon();
  			Image img = icon.getImage();
  			this.resizedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		}
  		
  		// REPAINT
  		if (this.resizedImage != null) {
  			this.updated=true;
      		repaint();
  		}
  	}
  }
  
  /**
   * CONVERT RAW IMAGE DATA TO BUFFERED IMAGE
   * @param imageData
   * @return BufferedImage
   * @throws Exception
  */
  private BufferedImage createImageFromBytes(byte[] imageData) throws Exception {
      final InputStream in = new ByteArrayInputStream(imageData);
      BufferedImage buf = ImageIO.read(in);
      return buf;
  }
  
	/**
	 * Paint Method - Paints Over Entire Screen Screen
	*/
	public void paintComponent(Graphics g) {
		
		if (this.updated) {
			Graphics2D g2 = (Graphics2D) g;
			paintImage(this.resizedImage, g2);
			this.updated = false;
		}
	}
  
	public void paintImage(BufferedImage image, Graphics2D g2) {

		assert image != null;
		assert g2 != null;

		int pw = getWidth();
		int ph = getHeight();
		int iw = image.getWidth();
		int ih = image.getHeight();

		Object antialiasing = g2.getRenderingHint(KEY_ANTIALIASING);
		Object rendering = g2.getRenderingHint(KEY_RENDERING);

		g2.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_OFF);
		g2.setRenderingHint(KEY_RENDERING, VALUE_RENDER_SPEED);
		g2.setBackground(Color.BLACK);
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, pw, ph);

		// resized image position and size
		int x = 0;
		int y = 0;
		int w = 0;
		int h = 0;

		double s = Math.max((double) iw / pw, (double) ih / ph);
		double niw = iw / s;
		double nih = ih / s;
		double dx = (pw - niw) / 2;
		double dy = (ph - nih) / 2;
		w = (int) niw;
		h = (int) nih;
		x = (int) dx;
		y = (int) dy;
		
		GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsConfiguration gc = genv.getDefaultScreenDevice().getDefaultConfiguration();
		
		Graphics2D gr = null;
		
		try {
			resizedImage = gc.createCompatibleImage(pw, ph);
			gr = resizedImage.createGraphics();
			gr.setComposite(AlphaComposite.Src);

			for (Map.Entry<RenderingHints.Key, Object> hint : imageRenderingHints.entrySet()) {
				gr.setRenderingHint(hint.getKey(), hint.getValue());
			}

			gr.setBackground(Color.BLACK);
			gr.setColor(Color.BLACK);
			gr.fillRect(0, 0, pw, ph);

			int sx1, sx2, sy1, sy2; // source rectangle coordinates
			int dx1, dx2, dy1, dy2; // destination rectangle coordinates

			dx1 = x;
			dy1 = y;
			dx2 = x + w;
			dy2 = y + h;
			
			sx1 = 0;
			sy1 = 0;
			sx2 = iw;
			sy2 = ih;
			
			gr.drawImage(image, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
		}
		finally {
			if (gr != null) {
				gr.dispose();
			}
		}
		
		g2.drawImage(resizedImage, 0, 0, null);
		
		g2.setRenderingHint(KEY_ANTIALIASING, antialiasing);
		g2.setRenderingHint(KEY_RENDERING, rendering);
	}
}