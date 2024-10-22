package io.quantumknight.video.util;
/********************************************************************************************
//* Filename: 		AESEncrypt.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    UTILITY - AES Encryption Standard.   Encrypt / Decrypt Methods 
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

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public abstract class AESEncrypt {
	
	public static final String _AES_CBC_MODE = "AES/CBC/PKCS5PADDING";
	public static final String _AES_CFB_MODE = "AES/CFB/PKCS5PADDING";
	public static final String _AES_OFB_MODE = "AES/OFB/PKCS5PADDING";
	public static final String _AES_CTR_MODE = "AES/CTR/NoPadding";
	
	public static final String _MODE = _AES_CFB_MODE;
	
	public static final String _AES_KEY_SPEC = "AES";
	
	
	/**
	 * AES-256 Encryption 
	 * -------------------------------------------------------------------------------------------
	 * Cipher Block Chaining (CBC) - https://en.wikipedia.org/wiki/Block_cipher_mode_of_operation#Cipher_Block_Chaining_%28CBC%29
	 * -------------------------------------------------------------------------------------------
	 * @param key
	 * @param iv
	 * @param cleartext
	 * @return byte[] - Ciphertext Data
	 * @throws Exception
	*/
    public static byte[] encrypt(byte[] key, byte[] iv, byte[] cleartext) throws Exception {
        SecretKeySpec secret = new SecretKeySpec(key, _AES_KEY_SPEC);
        Cipher cipher = Cipher.getInstance(_MODE);
        cipher.init(Cipher.ENCRYPT_MODE, secret, new IvParameterSpec(iv));
        return cipher.doFinal(cleartext);
    }
    
    /**
	 * AES-256 Decryption - CBC Mode
	 * -------------------------------------------------------------------------------------------
	 * Cipher Block Chaining (CBC) - https://en.wikipedia.org/wiki/Block_cipher_mode_of_operation#Cipher_Block_Chaining_%28CBC%29
	 * -------------------------------------------------------------------------------------------
     * @param key
     * @param iv
     * @param ciphertext
     * @return byte[] - Cleartext Data
     * @throws Exception
    */
    public static byte[] decrypt(byte[] key, byte[] iv, byte[] ciphertext) throws Exception {
    	SecretKeySpec secret = new SecretKeySpec(key, _AES_KEY_SPEC);
        Cipher cipher = Cipher.getInstance(_MODE);
        cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));
        return cipher.doFinal(ciphertext);
    }
	
	/**
	 * GENERATE RANDOM AES KEYS -
	 * -------------------------------------------------------------------------------------------
	 * @return Object[]
	 * @throws Exception
	*/
	public static Object[] generateAESKeys() throws Exception {
		
		SecureRandom csrng = new SecureRandom();
		
		byte[] iv = new byte[16];  // 128 bit key length
		for (int j = 0; j < iv.length; j++) {
			iv[j] = (byte)csrng.nextInt(127);
		}
		
		byte[] key = new byte[32]; // 256 bit key length
		csrng.nextBytes(key); 
		
		Object[] aesKeys = { key, iv };
		
		return aesKeys;
	}
}
