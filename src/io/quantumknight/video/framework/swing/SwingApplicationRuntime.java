package io.quantumknight.video.framework.swing;
/********************************************************************************************
//* Filename: 		SwingApplicationRuntime.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    JFC/SWING FRAMEWORK - MEMORY-RESIDENT SINGLETON
//* 				
//* 				Memory-Resident Singleton serves as the main focal-point
//* 				for user input, screen activity, and program functions.
//*
//*		Note:	 	Exceptions should usually be handled at this classes level!
//*				 	The owner of this class's runtime pointer will be the primary
//*				 	executable MAIN class.  Should an exception filter up to
//*				 	that level, the program will abnormally terminate.
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

import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JApplet;

import io.quantumknight.video.framework.constants.SwingApplicationConstants;
import io.quantumknight.video.framework.interfaces.SwingCommonLoginActionInterface;
import io.quantumknight.video.framework.io.GraphicsIO;


public class SwingApplicationRuntime {

	private static final SwingApplicationRuntime _INSTANCE = new SwingApplicationRuntime();
	
	private static MasterJFrame masterFrame;										// Master JFrame
	private static JApplet masterApplet;											// Master Applet - defined outside of GENERIC Package
	
	private static HashMap<String,byte[]> graphicsSplashScreen;						// Graphic Images for Splashscreen - loaded at init time - purged by caller when finished!
	private static HashMap<String,byte[]> graphicsApplicationSystem;				// Graphic Images for Entire Application - loaded at init time - held indefinitely
	private static HashMap<String,byte[]> audioApplicationSystem;					// Audio for Entire Application - loaded at init time - held indefinitely
	private static HashMap<String, CommonScreen> systemJPanelScreensByName;			// Every JPanel Screen for Entire Application - loaded at init time - held indefinitely
	private static HashMap<String,Object> javaComponentsByName;						// Single Location for Registering & Locating JComponents - optionally set by individual screens - held indefinitely
	
	private static CommonScreen currentlyActiveScreen;									// Currently active Screen - maintained by GenericSwingAction.displayScreenWithToolbar()
	
	private static boolean allComponentsLoadedOK = false;							// set by main executable when all components have loaded successfully!
	
	private static SwingSessionManager sessionManager;
	private static SwingCommonLoginActionInterface registeredLoginAction;			// login action specific to this application

	/**
	 * Private Constructor for ApplicationRuntime.java
	 * Enforces Singleton Pattern
	*/
	private SwingApplicationRuntime() {
	    super();
	    javaComponentsByName = new HashMap<String,Object>();						// not part of global initialization sequence
	    sessionManager = new SwingSessionManager();
	}
	
	/**
	 * Get Swing Session Manager - Used by Maintenance Threads
	 * @return SwingSessionManager
	*/
	public SwingSessionManager getSessionManager() {
	    return sessionManager;
	}
	
	/**
	 * Return an existing session / Create a new session -
	 * Note: This will only allow for ONE session per swing client
	 * @param String singleUserIPAddress
	 * @return SwingSession
	*/
	public static SwingSession getSession() {
	    return sessionManager.getSession();
	}
	
	/**
	 * returns singleton instance this class
	*/
	public static SwingApplicationRuntime getInstance() {
	    return _INSTANCE;
	}
	
	/**
	 * Initialize the MasterJFrame Container -
	 * Note: Digest Exceptions and return boolean to caller
	 * @param String applicationName
	 * @param int initialWidth
	 * @param int initialHeight
	 * @return void
	 * @throws Exception
	*/
	public static void initMasterJFrame(String applicationName, int initialWidth, int initialHeight) throws Exception {
	
	    SwingApplicationRuntime.setMasterFrame(new MasterJFrame(applicationName,initialWidth,initialHeight));
	}
	
	/**
	 * Initialize the MasterApplet Container -
	 * Note: Digest Exceptions and return boolean to caller
	 * @param JApplet container
	 * @return void
	 * @throws Exception
	*/
	public static void initMasterApplet(JApplet container) throws Exception {
	
	    SwingApplicationRuntime.setMasterApplet(container);
	}
	
	
	/**
	 * Toggle the visiblity of the Master JFrame
	 * @param visible
	*/
	public static void setMasterJFrameVisible(boolean visible) {
	    MasterJFrame masterFrame = SwingApplicationRuntime.getMasterFrame();
	    masterFrame.setVisible(visible);
	    masterFrame.repaint();
	}
	
	/**
	 * Toggle the visiblity of the Master Applet
	 * @param visible
	*/
	public static void setMasterAppletVisible(boolean visible) {
	    JApplet masterApplet = SwingApplicationRuntime.getMasterApplet();
	    masterApplet.setVisible(visible);
	    masterApplet.repaint();
	}
	
	/**
	 * Load splash screen images into memory from serialized graphics file
	 * @return void
	 * @throws Exception
	*/
	public static void loadSplashImages() throws Exception {
	    SwingApplicationRuntime.loadImages(SwingApplicationConstants.GRAPHICS_SPLASHSCRN, false);
	}
	
	/**
	 * Load application system images into memory from serialized graphics file
	 * @return void
	 * @throws Exception
	*/
	public static void loadApplicationImages() throws Exception {
	    SwingApplicationRuntime.loadImages(SwingApplicationConstants.GRAPHICS_APPLICATION, true);
	}
	
	/**
	 * Load application audio into memory from serialized audio file
	 * @return void
	 * @throws Exception
	*/
	public static void loadApplicationAudio() throws Exception {
	    SwingApplicationRuntime.loadAudio(SwingApplicationConstants.AUDIO_APPLICATION);
	}
	
	/**
	 * Load application screens into memory via dynamic class loading.
	 * Applications must provide their own NAME | VALUE pairs for classes
	 * @param
	 * @return void
	 * @throws Exception
	*/
	public static void loadApplicationScreens(String[][] applicationScreens) throws Exception {
	
	    HashMap<String,CommonScreen> screenMap = new HashMap<String,CommonScreen>();
	
	    for (int i=0; i<applicationScreens.length; i++) {
	        String[] keyval = applicationScreens[i];
	        if (keyval[0].length() < 1) {
	            continue;
	        }
	        try {
	            CommonScreen screen = (CommonScreen)(Class.forName(keyval[1]).newInstance());
	            screenMap.put(keyval[0], screen);
	        }
	        catch (ClassNotFoundException cex) {
	            if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.err.println("Application cannot find class for screen [" + keyval[0] + "]: " + cex.getMessage()); }
	            throw cex;
	        }
	        catch (InstantiationException iex) {
	            if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.err.println("Application cannot load class for screen [" + keyval[0] + "]: " + iex.getMessage()); }
	            throw iex;
	        }
	        catch (IllegalAccessException iacex) {
	            if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.err.println("Application cannot access the class for screen [" + keyval[0] + "]: " + iacex.getMessage()); }
	            throw iacex;
	        }
	        catch (ClassCastException ccex) {
	            if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.err.println("Application cannot load a non Framework-compliant screen [" + keyval[0] + "]: " + ccex.getMessage()); }
	            throw ccex;
	        }
	    }
	    if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.out.println("[" + screenMap.size() + "] Screens loaded!"); }
	
	    SwingApplicationRuntime.setSystemJPanelScreensByName(screenMap);
	}
	
	/**
	 * Shared convenience method to load graphics files into memory
	 * @param String imageFileName
	 * @param boolean system images or splash images
	 * @return boolean
	*/
	private static boolean loadImages(String imageFileName, boolean system) throws Exception {
	
	    GraphicsIO graphicsIO = new GraphicsIO();
	
	    try {
	        if (system) {
	            graphicsApplicationSystem = graphicsIO.getCompressedGraphicsMapFromJAR(imageFileName);
	        }
	        else {
	            graphicsSplashScreen = graphicsIO.getCompressedGraphicsMapFromJAR(imageFileName);
	        }
	    }
	    catch(Exception e) {
	        if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.err.println("ApplicationRuntime unable to load images from [" + imageFileName + "]: " + e.getMessage()); }
	        throw e;
	    }
	    finally {
	        graphicsIO = null;
	        System.gc();
	    }
	    return true;
	}
	
	/**
	 * Shared convenience method to load Wave Audio files into memory
	 * @param audioFileName
	 * @return boolean
	 * @throws Exception
	*/
	private static boolean loadAudio(String audioFileName) throws Exception {
		
//		AudioIO audioIO = new AudioIO();
//		
//		try {
//			audioApplicationSystem = audioIO.getCompressedAudioMapFromJAR(audioFileName);
//	    }
//	    catch(Exception e) {
//	        if (SwingApplicationConstants.DEBUG_FRAMEWORK) { System.err.println("ApplicationRuntime unable to load audio from [" + audioFileName + "]: " + e.getMessage()); }
//	        throw e;
//	    }
//	    finally {
//	    	audioIO = null;
//	        System.gc();
//	    }
	    return true;
	}
	
	/**
	 * Java Getter Method
	 * Created: 9:32:34 AM
	 * @return Returns the allComponentsLoadedOK.
	 */
	public static boolean isAllComponentsLoadedOK() {
	    return allComponentsLoadedOK;
	}
	/**
	 * Java Setter Method
	 * Created: 9:32:34 AM
	 * @param allComponentsLoadedOK The allComponentsLoadedOK to set.
	 */
	public static void setAllComponentsLoadedOK(boolean allComponentsLoadedOK) {
	    SwingApplicationRuntime.allComponentsLoadedOK = allComponentsLoadedOK;
	}
	/**
	 * Java Getter Method
	 * Created: 9:32:34 AM
	 * @return Returns the graphicsApplicationSystem.
	 */
	public static HashMap<String,byte[]> getGraphicsApplicationSystem() {
	    return graphicsApplicationSystem;
	}
	
	/**
	 * Java Getter Method
	 * @param String imageKey
	 * @return ImageIcon
	*/
	public synchronized static ImageIcon getGraphicFromApplicationSystem(String imageKey) {
	
	    if (graphicsApplicationSystem != null && graphicsApplicationSystem.containsKey(imageKey)) {
	        return new ImageIcon(graphicsApplicationSystem.get(imageKey));
	    }
	    else {
	        return null;
	    }
	}
	
	/**
	 * Java Getter Method
	 * @param String audioKey
	 * @return byte[]
	*/
	public synchronized static byte[] getAudioFromApplicationSystem(String audioKey) {
	
	    if (audioApplicationSystem != null && audioApplicationSystem.containsKey(audioKey)) {
	    	return audioApplicationSystem.get(audioKey);
	    }
	    else {
	        return null;
	    }
	}
	
	/**
	 * Java Setter Method
	 * Created: 9:32:34 AM
	 * @param graphicsApplicationSystem The graphicsApplicationSystem to set.
	 */
	public static void setGraphicsApplicationSystem(HashMap<String,byte[]> graphicsApplicationSystem) {
	    SwingApplicationRuntime.graphicsApplicationSystem = graphicsApplicationSystem;
	}
	/**
	 * Java Getter Method
	 * Created: 9:32:34 AM
	 * @return Returns the graphicsSplashScreen.
	 */
	public static HashMap<String,byte[]> getGraphicsSplashScreen() {
	    return graphicsSplashScreen;
	}
	
	/**
	 * Java Setter Method
	 * Created: 9:32:34 AM
	 * @param graphicsSplashScreen The graphicsSplashScreen to set.
	 */
	public static void setGraphicsSplashScreen(HashMap<String,byte[]> graphicsSplashScreen) {
	    SwingApplicationRuntime.graphicsSplashScreen = graphicsSplashScreen;
	}
	
	/**
	 * @return the audioApplicationSystem
	 */
	public static HashMap<String, byte[]> getAudioApplicationSystem() {
		return audioApplicationSystem;
	}
	
	/**
	 * @param audioApplicationSystem the audioApplicationSystem to set
	 */
	public static void setAudioApplicationSystem(HashMap<String, byte[]> audioApplicationSystem) {
		SwingApplicationRuntime.audioApplicationSystem = audioApplicationSystem;
	}
	
	/**
	 * Java Getter Method
	 * Created: 9:32:35 AM
	 * @return Returns the masterFrame.
	 */
	public static MasterJFrame getMasterFrame() {
	    return masterFrame;
	}
	/**
	 * Java Setter Method
	 * Created: 9:32:35 AM
	 * @param masterFrame The masterFrame to set.
	 */
	public static void setMasterFrame(MasterJFrame masterFrame) {
	    SwingApplicationRuntime.masterFrame = masterFrame;
	}
	/**
	 * Java Getter Method
	 * Created: 9:32:35 AM
	 * @return Returns the systemJPanelScreensByName.
	 */
	public static HashMap<String,CommonScreen> getSystemJPanelScreensByName() {
	    return systemJPanelScreensByName;
	}
	
	/**
	 * Java Getter Method
	 * @param String specificName
	 * @return Object
	 */
	public static Object getSystemJPanelScreensByName(String specificName) {
	    return systemJPanelScreensByName.get(specificName);
	}
	
	/**
	 * Java Setter Method
	 * Created: 9:32:35 AM
	 * @param systemJPanelScreensByName The systemJPanelScreensByName to set.
	 */
	public static void setSystemJPanelScreensByName(HashMap<String,CommonScreen> systemJPanelScreensByName) {
	    SwingApplicationRuntime.systemJPanelScreensByName = systemJPanelScreensByName;
	}
	
	/**
	 * Java Getter Method
	 * @param String specificName
	 * @return Object
	 */
	public static Object getJComponentByName(String specificName) {
	    return javaComponentsByName.get(specificName);
	}
	
	/**
	 * Java Setter Method
	 * @param String fieldName, Object fieldObject
	 */
	public static void setJComponentByName(String specificName, Object fieldObject) {
	    if (javaComponentsByName.containsKey(specificName)) {
	        javaComponentsByName.remove(specificName);
	    }
	    javaComponentsByName.put(specificName,fieldObject);
	}
	
	public static void removeJComponentByName(String specificName) {
	    if (javaComponentsByName.containsKey(specificName)) {
	        javaComponentsByName.remove(specificName);
	    }
	}
	
	public static String[] getAllMappedJComponents() {
	    String[] names = new String[javaComponentsByName.keySet().size()];
	    int c = 0;
	    for (String s : javaComponentsByName.keySet()) {
	        names[c++] = s;
	    }
	    return names;
	}
	
	public static JApplet getMasterApplet() {
	    return masterApplet;
	}
	
	public static void setMasterApplet(JApplet masterApplet) {
	    SwingApplicationRuntime.masterApplet = masterApplet;
	}
	
	/**
	 * @return the registeredLoginAction
	 */
	public static SwingCommonLoginActionInterface getRegisteredLoginAction() {
	    return registeredLoginAction;
	}
	
	/**
	 * @param registeredLoginAction the registeredLoginAction to set
	 */
	public static void setRegisteredLoginAction(
			SwingCommonLoginActionInterface registeredLoginAction) {
	    SwingApplicationRuntime.registeredLoginAction = registeredLoginAction;
	}
	
	/**
	 * @return the currentlyActiveScreen
	 */
	public static CommonScreen getCurrentlyActiveScreen() {
		return currentlyActiveScreen;
	}

	/**
	 * @param currentlyActiveScreen the currentlyActiveScreen to set
	 */
	public static void setCurrentlyActiveScreen(CommonScreen currentlyActiveScreen) {
		SwingApplicationRuntime.currentlyActiveScreen = currentlyActiveScreen;
	}
}