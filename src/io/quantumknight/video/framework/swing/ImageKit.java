package io.quantumknight.video.framework.swing;
/********************************************************************************************
//* Filename: 		ImageKit.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    UTILITY - I/O UTILITIES FOR LOADING / RENDERING IMAGES
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

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;
 
public abstract class ImageKit {
    
	/**
	 * SAVE IMAGE TO FILE - 
	 * 
	 * NOTE:  quality means jpeg output, if quality is < 0 ==> use default quality
	 * 
	 * @param image
	 * @param quality
	 * @param out
	 * @throws IOException
	*/
    public static void write(BufferedImage image, float quality, OutputStream out) throws IOException {
        Iterator<ImageWriter> writers = ImageIO.getImageWritersBySuffix("jpeg");
        if (!writers.hasNext())
            throw new IllegalStateException("No writers found");
        ImageWriter writer = (ImageWriter) writers.next();
        ImageOutputStream ios = ImageIO.createImageOutputStream(out);
        writer.setOutput(ios);
        ImageWriteParam param = writer.getDefaultWriteParam();
        if (quality >= 0) {
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(quality);
        }
        writer.write(null, new IIOImage(image, null, null), param);
    }
 
    /**
     * LOAD BUFFERED IMAGE FROM BYTES
     * @param bytes
     * @return BufferedImage
    */
    public static BufferedImage read(byte[] bytes) {
        try {
            return ImageIO.read(new ByteArrayInputStream(bytes));
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    /**
     * RETURN BYTE ARRAY FROM BUFFERED IMAGE - USES QUALITY FILTER
     * @param image
     * @param quality
     * @return byte[]
    */
    public static byte[] getBytes(BufferedImage image, float quality, int bufferSize) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream(bufferSize);
            write(image, quality, out);
            return out.toByteArray();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * RETURN BYTE ARRAY FROM BUFFERED IMAGE - USES QUALITY FILTER
     * @param image
     * @param quality
     * @return byte[]
    */
    public static byte[] getBytes(BufferedImage image, float quality, String fileType) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            write(image, quality, out, fileType);
            return out.toByteArray();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
    
	/**
	 * SAVE IMAGE TO FILE - 
	 * 
	 * NOTE:  quality means jpeg output, if quality is < 0 ==> use default quality
	 * 
	 * @param image
	 * @param quality
	 * @param out
	 * @throws IOException
	*/
    public static void write(BufferedImage image, float quality, OutputStream out, String fileType) throws IOException {
    	
    	if (!fileType.equalsIgnoreCase("ico")) {
    		
            Iterator<ImageWriter> writers = ImageIO.getImageWritersBySuffix(fileType);
            
            if (!writers.hasNext())
                throw new IllegalStateException("No writers found");
            ImageWriter writer = (ImageWriter) writers.next();
            ImageOutputStream ios = ImageIO.createImageOutputStream(out);
            writer.setOutput(ios);
            ImageWriteParam param = writer.getDefaultWriteParam();
            if (quality >= 0) {
            	if (fileType.equalsIgnoreCase("jpeg")) {
            		param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                    param.setCompressionQuality(quality);
            	}
            }
            writer.write(null, new IIOImage(image, null, null), param);
    	}
    }
    
    /**
     * RETURN BYTE ARRAY FROM BUFFERED IMAGE - USES QUALITY FILTER
     * @param image
     * @param quality
     * @return byte[]
    */
    public static byte[] getBytes(BufferedImage image, float quality) {
        return getBytes(image, quality, 50000);
    }
 
    /**
     * COMPRESS A BUFFERED IMAGE
     * @param image
     * @param quality
     * @return BufferedImage
    */
    public static BufferedImage compress(BufferedImage image, float quality) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream(50000);
            write(image, quality, out);
            return ImageIO.read(new ByteArrayInputStream(out.toByteArray()));
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * RGB RE-SCALE A BUFFERED IMAGE
     * @param bsrc
     * @param width
     * @param height
     * @return BufferedImage
    */
    public static BufferedImage scale(BufferedImage bsrc, int width, int height) {
    	BufferedImage bdest = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    	Graphics2D g = bdest.createGraphics();
    	AffineTransform at = AffineTransform.getScaleInstance((double)width/bsrc.getWidth(),(double)height/bsrc.getHeight());
    	g.drawRenderedImage(bsrc,at);
    	return bdest;
    }
    
    /**
     * UTILITY METHOD - GET DEFAULT CONFIGURATION FOR GRAPHICS FROM LOCAL ENVIRONMENT
     * @return GraphicsConfiguration
    */
    public static GraphicsConfiguration getDefaultConfiguration() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }

    /**
     * COPY BUFFERED IMAGE - OVERRIDE - FORCES BICUBIC RENDERING HINTS
     * @param source
     * @param target
     * @return BufferedImage
    */
    public static BufferedImage copy(BufferedImage source, BufferedImage target) {
    	return copy(source,target,RenderingHints.VALUE_INTERPOLATION_BICUBIC);
    }

    /**
     * COPY BUFFERED IMAGE 
     * @param source
     * @param target
     * @param interpolationHint
     * @return BufferedImage
    */
    public static BufferedImage copy(BufferedImage source, BufferedImage target, Object interpolationHint) {
        Graphics2D g = target.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, interpolationHint);
        double scalex = (double) target.getWidth() / source.getWidth();
        double scaley = (double) target.getHeight() / source.getHeight();
        AffineTransform at = AffineTransform.getScaleInstance(scalex, scaley);
        g.drawRenderedImage(source, at);
        g.dispose();
        return target;
    }

    /**
     * GET SCALED INSTANCE OF A BUFFERED IMAGE FROM BUFFERED IMAGE SOURCE - USES JAVA2D IMAGE RENDER
     * @param source
     * @param factor
     * @param interpolationHint
     * @param gc
     * @return BufferedImage
    */
    public static BufferedImage getScaledInstance2D(BufferedImage source, double factor, Object interpolationHint, GraphicsConfiguration gc) {
        if (gc == null)
            gc = getDefaultConfiguration();
        int w = (int) (source.getWidth() * factor);
        int h = (int) (source.getHeight() * factor);
        int transparency = source.getColorModel().getTransparency();
        return copy(source, gc.createCompatibleImage(w, h, transparency), interpolationHint);
    }

    /**
     * GET SCALED INSTANCE OF A BUFFERED IMAGE AS IMAGE FILE - USES AWT TOOLKIT
     * @param source
     * @param factor
     * @param hint
     * @return
    */
    public static Image getScaledInstanceAWT(BufferedImage source, double factor, int hint) {
        int w = (int) (source.getWidth() * factor);
        int h = (int) (source.getHeight() * factor);
        return source.getScaledInstance(w, h, hint);
    }

    /**
     * GET SCALED INSTANCE OF A BUFFERED IMAGE AS AN IMAGE FILE - USES BICUBIC
     * 
     * >> *** best of breed!
     * 
     * @param source
     * @param factor
     * @param gc
     * @return Image
    */
    public static Image getScaledInstance(BufferedImage source, double factor, GraphicsConfiguration gc) {
         if (factor >= 1.0)
             return getScaledInstance2D(source, factor, RenderingHints.VALUE_INTERPOLATION_BICUBIC, gc);
         else
             return getScaledInstanceAWT(source, factor, Image.SCALE_AREA_AVERAGING);
    }

    /**
     * CONVERT IMAGE TO BUFFERED IMAGE
     * 
     * This method returns a buffered image with the contents of an image
     * 
     * @param image
     * @return BufferedImage
    */
	public static BufferedImage toBufferedImage(Image image) { 

		if (image instanceof BufferedImage) { 
			return (BufferedImage)image; 
		}
	
		// This code ensures that all the pixels in the image are loaded 
		image = new ImageIcon(image).getImage(); 
	
	
		// Determine if the image has transparent pixels; for this method's 
		// implementation, see Determining If an Image Has Transparent Pixels 
	
		boolean hasAlpha = hasAlpha(image); 
	
		// Create a buffered image with a format that's compatible with the screen 
		BufferedImage bimage = null; 
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	
		try { 
	
			// Determine the type of transparency of the new buffered image 
			int transparency = Transparency.OPAQUE; 
		
			if (hasAlpha) { 
				transparency = Transparency.BITMASK; 
			}
		
			// Create the buffered image 
			GraphicsDevice gs = ge.getDefaultScreenDevice();
		
			GraphicsConfiguration gc = gs.getDefaultConfiguration();
		
			bimage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency); 
		} 
		catch (HeadlessException e) { 
			// The system does not have a screen
			e.printStackTrace();
		}
	
		if (bimage == null) { 
			// Create a buffered image using the default color model 
	
			int type = BufferedImage.TYPE_INT_RGB; 
	
			if (hasAlpha) { 
				type = BufferedImage.TYPE_INT_ARGB; 
			}
		
			bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type); 
		}
	
		// Copy image to buffered image 
		Graphics g = bimage.createGraphics();
	
		// Paint the image onto the buffered image 
		g.drawImage(image, 0, 0, null); 
		g.dispose();
	
		return bimage; 
	}


	/**
	 * DETERMINE IF IMAGE HAS ALPHA
	 * @param image
	 * @return boolean
	*/
	public static boolean hasAlpha(Image image) { 

		// If buffered image, the color model is readily available 
	
		if (image instanceof BufferedImage) { 
			BufferedImage bimage = (BufferedImage)image;
			return bimage.getColorModel().hasAlpha(); 
		}

		// Use a pixel grabber to retrieve the image's color model; 	
		// grabbing a single pixel is usually sufficient 
		PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false); 
	
		try { 
			pg.grabPixels();
	
		} 
		catch (InterruptedException e) { 
		}
		
		// Get the image's color model 
		ColorModel cm = pg.getColorModel();
		return cm.hasAlpha(); 
	}
	
    /**
     * CONVERTING TEXT TO IMGAE USING GRAPHICS2D
     * 
     * @param txt
     * @param fontName
     * @param fontSize
     * @param width
     * @return byte[]
     * @throws Exception
     */
    public static byte[] convertTextToImage(String txt, String fontName, int fontSize, int width) throws Exception {

        int ascent = 20; // TO ACHEIVE THE TEXT WRAPPING IN IMAGE
        
        List<String> strings = convertStringToList(txt, width);
        int height = ascent * (strings.size() + 2); // DECIDING THE IMAGE HEIGHT BASED ON TEXT WRAP
        
        // CREATING FONT OBJECT FOR THE IMAGE TEXT DRAWING.
        Font font = new Font(fontName, Font.PLAIN, fontSize);

        // CREATING THE IMAGE WITH SPECIFIED WIDTH AND HEIGHT
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // CREATING THE GRAPHICS OF IMAGE
        Graphics2D g2d = img.createGraphics();
        g2d.fillRect(0, 0, img.getWidth(), img.getHeight());
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);

        // DRAW TEXT 
        for (String line : strings) {
            g2d.drawString(line, 0, ascent);
            ascent = ascent + fontSize + 10; // ASCENT OF LINE BASED ON FONTSIZE
        }
        g2d.dispose();
        
        byte[] imageInByte = getBytes(img, 1, 64); // GET THE BYTE[] OF IMAGE

        return imageInByte;

    }
    
    /**
     * CONVERTING STRING TO LIST<STRING>
     * 
     * @param txt
     * @param width
     * @return List<String>
     * @throws Exception
     */
    public static List<String> convertStringToList(String txt, int width) throws Exception {

        int index = 0;
        int txtLength = txt.length();
        
        // BREAK THE TEXT INTO A LIST OF STRINGS
        // EACH STRING SHOULD BE LESS THE MESSAGE SLICE(MAXIUMUM VALUE OF IMAGE SLICE IS width / 7)
        List<String> strings = new ArrayList<String>();
        while (index < txtLength) {
            strings.add(txt.substring(index, Math.min(index + width / 7, txtLength)).trim());
            index += width / 7;
        }
        return strings;
    }
}

