package io.quantumknight.video.state;
/********************************************************************************************
//* Filename: 		VideoDemoSettings.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    SINGLETON - Application State Machine - CLEAR Encrypted Video Demo Application
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

import java.util.HashMap;

import com.clear.cryptosystem.CLEAR;
import com.clear.cryptosystem.CLEARMode;
import com.clear.cryptosystem.CLEARResult;
import com.clear.cryptosystem.CLEARStream;
import com.clear.cryptosystem.CLEARString;
import com.github.sarxos.webcam.Webcam;

import io.quantumknight.common.swing.webcam.VideoPanel;
import io.quantumknight.video.constants.ConstantsEncryptionStrength;
import io.quantumknight.video.framework.io.LogManager;
import io.quantumknight.video.framework.io.Logger;
import io.quantumknight.video.threads.StrengthModulatorWorker;
import io.quantumknight.video.threads.VideoPanelUpdateWorker;
import io.quantumknight.video.util.AESEncrypt;
import io.quantumknight.video.util.TimeMarkerNano;


public class VideoDemoSettings {
	
	private static final Logger log = LogManager.getLogger(VideoDemoSettings.class);
	
	private static VideoDemoSettings _INSTANCE = new VideoDemoSettings();  // Static Local Initializer - Java Singleton Pattern
	
    
    /** GENERAL WEB-CAM RELATED CONTROLS AND STATEFUL REFERENCES **/
    private Webcam activeLocalMirrorWebcam;
    private VideoPanel mainVideoDisplayPanel;
    private volatile VideoPanelUpdateWorker videpPanelRefreshThread;
    private boolean videoStarted;
    
    
    /** ENCRYPTION RELATED CONTROLS AND STATEFUL REFERENCES **/
    private boolean encryptionActivated;
    private boolean modulatorActivated;
    private boolean encryptWithAes;
    private boolean simulateKeyRemoval;
    private boolean bypassDecryption;
    
    private int encryptionStrength = 512; 					// BIT STRENGTH -
    private int modulatorSetting    = 1;	 				// STRENGTH SELECTION (1-100)
    
    private volatile StrengthModulatorWorker modulatorThread;
    
    private long encryptionTime;
    private long decryptionTime;
    
    private HashMap<Integer, Long> encryptionJobID;
    private HashMap<Integer, Long> decryptionJobID;
    
    private byte[] aesKey;
    private byte[] aesIV;
    
	private boolean initialized = false;
	
	/**
	 * Private Constructor - Java Singleton Pattern
	 */
	private VideoDemoSettings() {
		super();
		this.initialize();
	}
	
	/**
	 * Singleton Accessor - PUBLIC
	 * @return TransactionSingleton
	*/
	public static VideoDemoSettings getInstance() {
		return _INSTANCE;
	}
	
	/***********************************************************************************************************************************/
	/*** INITIALIZATION SEQUENCE BELOW THIS LINE ***************************************************************************************/
	/***********************************************************************************************************************************/
	
	/**
	 * Initialize Singleton - 
	 * @param seed
	 * @return void
	*/
	private void initialize() {
		
		TimeMarkerNano sw = new TimeMarkerNano();
		
		try {
			
			log.info("[CLEAR ENCRYPTED VIDEO DEMO] APPLICATION - INITIALIZING!");
			
			sw.setTimeMark(1);
			
			this.checkLicense();
			
			this.encryptionJobID = new HashMap<Integer, Long>();
			this.decryptionJobID = new HashMap<Integer, Long>();
		    
			this.initializeCLEARStreamInterface();  // GENERATE KEYS FOR VIDEO DEMO - CLEAR CRYPTOSYSTEM
			this.initializeAESInterface(); 			// GENERATE KEYS FOR VIDEO DEMO - AES-256
			
			this.initialized = true;
			
			sw.setTimeMark(2);
			
			log.info("[CLEAR ENCRYPTED VIDEO DEMO] INITIALIZED - " + sw.compareTimeFormattedString(1, 2));
		}
		catch(Exception e) {
			log.fatal("CANNOT INITIALIZE [ENCRYPTED VIDEO DEMO] SINGLETON: " + e.getMessage(), e.fillInStackTrace());
		}
	}
	
	/**
	 * ATOMIC SUBROUTINE - VERIFY CLEAR LICENSE PRIOR TO STARTUP
	 * @throws Exception
	*/
	private void checkLicense() throws Exception {
		
		boolean verified = false;
		
		try {
			
			CLEARString cs = CLEAR.clearString();
			
			String clear = "sometext";
			CLEARResult cr = cs.encrypt(clear, 1, CLEARMode.SingleJobEncryptionSysGenKeyMode.SINGLE_JOB_SYSTEM_GEN_KEY);
			
			cr = cs.decrypt(cr.getCipherTextString(), cr.getKeyMaterial(), CLEARMode.SingleJobDecryptionMode.SINGLE_JOB_KEY);
			
			if ((cr != null) && (cr.getClearTextString() != null)) {
				
				if (cr.getClearTextString().equals(clear)) {
					verified=  true;
				}
			}
		}
		catch(Exception ex) {
			// ex.printStackTrace();
		}
		
		if (!verified) {
			System.err.println("COULD NOT RUN CLEAR - LICENSE NOT FOUND OR INVALID");
			System.exit(1);
		}
	}
	
	/**
	 * PRE-INITIALIZE CLEAR STREAM INTERFACE 
	 * @return void
	 * @throws Exception
	*/
	private void initializeCLEARStreamInterface() throws Exception {
		
		CLEARStream xs = CLEAR.clearStream();
		byte[] buf = new byte[10];
		
		for (int i = 0; i < ConstantsEncryptionStrength.getStrengthsCount(); i++) {
			
			int keySize = ConstantsEncryptionStrength.getStrength(i);  // INITIALIZE FOR EACH OF THE CLEAR KEY STRENGTHS [512 - 10,240bit]
			
			CLEARResult encrypted = xs.encryptStart(
				buf, 
				keySize, 
				CLEARMode.StreamingEncryptionSysGenKeyMode.STREAMING_SYSTEM_GEN_KEY_HEADERLESS, 
				false
			);
			this.encryptionJobID.put((i+1), encrypted.getJobNumber());
			
			CLEARResult decrypted = xs.decryptStart(
				encrypted.getCipherText(), 
				encrypted.getKeyMaterial(), 
				CLEARMode.StreamingDecryptionMode.STREAMING_KEY_HEADERLESS, 
				false
			);
			this.decryptionJobID.put((i+1), decrypted.getJobNumber());
		}
	}
	
	/**
	 * PRE-INITIALIZE AES-256 INTERFACE 
	 * @return void
	 * @throws Exception
	*/
	private void initializeAESInterface() throws Exception {
		
		Object[] aesKeys = AESEncrypt.generateAESKeys();
		
		this.aesKey = (byte[])aesKeys[0];
		this.aesIV  = (byte[])aesKeys[1];
	}
	
	/**
	 * RESET ENCRYPTION - GENERATE NEW KEYS FOR ALL WAVE FORMS
	 * @throws Exception
	*/
	public void reset() throws Exception {
		
		this.encryptionJobID = new HashMap<Integer, Long>();
		this.decryptionJobID = new HashMap<Integer, Long>();
		
		this.initializeCLEARStreamInterface();
		this.initializeAESInterface();
	}
	
	/***********************************************************************************************************************************/
	/*** ACCESSOR METHODS BELOW THIS LINE **********************************************************************************************/
	/***********************************************************************************************************************************/
	
	/**
	 * PUBLIC ACCESSOR - RETURN THE CLEAR ENCRYPTION JOB ID FOR THE SPECIFIC ENCRYPTION STRENGTH
	 * @return long
	*/
	public long[] getJobIDs() {
		
		long[] jobIDS = { 	this.encryptionJobID.get(this.modulatorSetting),
							this.decryptionJobID.get(this.modulatorSetting)
		};
		
		return jobIDS;
	}

	/** 
	 * Returns the job ID for the current encrypting instance.
	 * @return
	 */
	public long getEncryptingJobId() {
		
		return encryptionJobID.get(modulatorSetting);
	}
	
	/**
	 * Returns the job ID of the current decrypting instance.
	 * @return
	 */
	public long getDecryptingJobId() {
		
		return decryptionJobID.get(modulatorSetting);
	}
	
	/***********************************************************************************************************************************/
	/*** GETTER-SETTER METHODS BELOW THIS LINE *****************************************************************************************/
	/***********************************************************************************************************************************/
	
    /**
     * @return the initialized
     */
    public boolean isInitialized() {
        return initialized;
    }

	/**
	 * @return the activeLocalMirrorWebcam
	 */
	public Webcam getActiveLocalMirrorWebcam() {
		return activeLocalMirrorWebcam;
	}

	/**
	 * @param activeLocalMirrorWebcam the activeLocalMirrorWebcam to set
	 */
	public void setActiveLocalMirrorWebcam(Webcam activeLocalMirrorWebcam) {
		this.activeLocalMirrorWebcam = activeLocalMirrorWebcam;
	}

	/**
	 * @return the mainVideoDisplayPanel
	 */
	public VideoPanel getMainVideoDisplayPanel() {
		return mainVideoDisplayPanel;
	}

	/**
	 * @param mainVideoDisplayPanel the mainVideoDisplayPanel to set
	 */
	public void setMainVideoDisplayPanel(VideoPanel mainVideoDisplayPanel) {
		this.mainVideoDisplayPanel = mainVideoDisplayPanel;
	}

	/**
	 * @return the videpPanelRefreshThread
	 */
	public VideoPanelUpdateWorker getVidepPanelRefreshThread() {
		return videpPanelRefreshThread;
	}

	/**
	 * @param videpPanelRefreshThread the videpPanelRefreshThread to set
	 */
	public void setVidepPanelRefreshThread(VideoPanelUpdateWorker videpPanelRefreshThread) {
		this.videpPanelRefreshThread = videpPanelRefreshThread;
	}

	/**
	 * @return the videoStarted
	 */
	public boolean isVideoStarted() {
		return videoStarted;
	}

	/**
	 * @param videoStarted the videoStarted to set
	 */
	public void setVideoStarted(boolean videoStarted) {
		this.videoStarted = videoStarted;
	}

	/**
	 * @return the encryptionActivated
	 */
	public boolean isEncryptionActivated() {
		return encryptionActivated;
	}

	/**
	 * @param encryptionActivated the encryptionActivated to set
	 */
	public void setEncryptionActivated(boolean encryptionActivated) {
		this.encryptionActivated = encryptionActivated;
	}

	/**
	 * @return the modulatorActivated
	 */
	public boolean isWaveFormActivated() {
		return modulatorActivated;
	}

	/**
	 * @param modulatorActivated the modulatorActivated to set
	 */
	public void setWaveFormActivated(boolean waveFormActivated) {
		this.modulatorActivated = waveFormActivated;
	}

	/**
	 * @return the encryptionStrength
	 */
	public int getEncryptionStrength() {
		return encryptionStrength;
	}

	/**
	 * @param encryptionStrength the encryptionStrength to set
	 */
	public void setEncryptionStrength(int encryptionStrength) {
		this.encryptionStrength = encryptionStrength;
	}

	/**
	 * @return the encryptionTime
	 */
	public long getEncryptionTime() {
		return encryptionTime;
	}

	/**
	 * @param encryptionTime the encryptionTime to set
	 */
	public void setEncryptionTime(long encryptionTime) {
		this.encryptionTime = encryptionTime;
	}

	/**
	 * @return the decryptionTime
	 */
	public long getDecryptionTime() {
		return decryptionTime;
	}

	/**
	 * @param decryptionTime the decryptionTime to set
	 */
	public void setDecryptionTime(long decryptionTime) {
		this.decryptionTime = decryptionTime;
	}

	/**
	 * @return the simulateKeyRemoval
	 */
	public boolean isSimulateKeyRemoval() {
		return simulateKeyRemoval;
	}

	/**
	 * @param simulateKeyRemoval the simulateKeyRemoval to set
	 */
	public void setSimulateKeyRemoval(boolean simulateKeyRemoval) {
		this.simulateKeyRemoval = simulateKeyRemoval;
	}

	/**
	 * @return the bypassDecryption
	 */
	public boolean isBypassDecryption() {
		return bypassDecryption;
	}

	/**
	 * @param bypassDecryption the bypassDecryption to set
	 */
	public void setBypassDecryption(boolean bypassDecryption) {
		this.bypassDecryption = bypassDecryption;
	}

	/**
	 * @return the modulatorSetting
	 */
	public int getModulatorSetting() {
		return modulatorSetting;
	}

	/**
	 * @param modulatorSetting the modulatorSetting to set
	 */
	public void setModulatorSetting(int modulatorSetting) {
		this.modulatorSetting = modulatorSetting;
	}

	/**
	 * @return the modulatorThread
	 */
	public StrengthModulatorWorker getModulatorThread() {
		return modulatorThread;
	}

	/**
	 * @param modulatorThread the modulatorThread to set
	 */
	public void setModulatorThread(StrengthModulatorWorker modulatorThread) {
		this.modulatorThread = modulatorThread;
	}
	
	/**
	 * Returns whether AES is being used for encryption and decryption in lieu of CLEAR.
	 * @return
	 */
	public boolean isEncryptingWithAes() {
		
		return encryptWithAes;
	}
	
	/**
	 * Sets whether AES (true) or CLEAR (false) is used for encryption and decryption.
	 * @param encryptWithAes
	 */
	public void setEncryptingWithAes(boolean encryptWithAes) {
		
		this.encryptWithAes = encryptWithAes;
	}

	/**
	 * @return the aesKey
	 */
	public byte[] getAesKey() {
		return aesKey;
	}

	/**
	 * @return the aesIV
	 */
	public byte[] getAesIV() {
		return aesIV;
	}
}
