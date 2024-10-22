package io.quantumknight.video.constants;
/********************************************************************************************
//* Filename: 		ConstantsEncryptionStrength.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    CLEAR Bit-Strength <> Strength Level (Index) Translation
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

import java.util.*;

public abstract class ConstantsEncryptionStrength {

	private static final int MULTIPLIER = 512;
	
	private static final int[] STRENGTH_VALUES = { 1, 2, 4, 10, 20 };
	
	private ConstantsEncryptionStrength() {}
	
	public static final int getStrength(int index) {
		
		return STRENGTH_VALUES[index] * MULTIPLIER;
	}
	
	public static final int[] getAllStrengths() {
		
		return Arrays.stream(STRENGTH_VALUES).map(x -> x * MULTIPLIER).toArray();
	}
	
	public static final int getStrengthsCount() {
		
		return STRENGTH_VALUES.length;
	}
}
