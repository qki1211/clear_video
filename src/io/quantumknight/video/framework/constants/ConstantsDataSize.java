package io.quantumknight.video.framework.constants;
/********************************************************************************************
//* Filename: 		ConstantsDataSize.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    CONSTANTS CLASS - Common number lookups for data-size calculations
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

public abstract class ConstantsDataSize {
	
	public static final int BUFFER_1 						= 1;						// ONE 
	public static final int BUFFER_4 						= 4;						// FOUR 
	public static final int BUFFER_8 						= 8;						// EIGHT
	public static final int BUFFER_16 						= 16;						// SIXTEEN
	public static final int BUFFER_32						= 32;						// THIRTY TWO 
	public static final int BUFFER_64						= 64;						// SIXTY FOUR 
	public static final int BUFFER_128						= 128; 						// ONE HUNDRED TWENTY EIGHT
	public static final int BUFFER_256						= 256; 						// TWO HUNDRED FIFTY SIX
	public static final int BUFFER_512						= 512; 						// FIVE HUNDRED TWELVE
	
	public static final int BUFFER_1K 						= 1024; 					// ONE KB
	
	public static final int BUFFER_4K 						= BUFFER_4 * BUFFER_1K; 	// FOUR KB
	public static final int BUFFER_8K 						= BUFFER_8 * BUFFER_1K; 	// EIGHT KB
	public static final int BUFFER_16K 						= BUFFER_16 * BUFFER_1K; 	// SIXTEEN KB
	public static final int BUFFER_32K 						= BUFFER_32 * BUFFER_1K; 	// THIRTY TWO KB
	public static final int BUFFER_64K 						= BUFFER_64 * BUFFER_1K; 	// SIXTY FOUR KB
	public static final int BUFFER_128K 					= BUFFER_128 * BUFFER_1K; 	// ONE HUNDRED TWENTY EIGHT KB
	public static final int BUFFER_256K 					= BUFFER_256 * BUFFER_1K; 	// TWO HUNDRED FIFTY SIX KB
	public static final int BUFFER_512K 					= BUFFER_512 * BUFFER_1K; 	// FIVE HUNDRED TWELVE KB
	
	public static final int BUFFER_1M 						= BUFFER_1K * BUFFER_1K; 	// ONE MB
	
	public static final int BUFFER_4M 						= BUFFER_4 * BUFFER_1M; 	// FOUR MB
	public static final int BUFFER_8M 						= BUFFER_8 * BUFFER_1M; 	// EIGHT MB
	public static final int BUFFER_16M 						= BUFFER_16 * BUFFER_1M; 	// SIXTEEN MB
	public static final int BUFFER_32M 						= BUFFER_32 * BUFFER_1M; 	// THIRTY TWO MB
	public static final int BUFFER_64M 						= BUFFER_64 * BUFFER_1M; 	// SIXTY FOUR MB
	public static final int BUFFER_128M 					= BUFFER_128 * BUFFER_1M; 	// ONE HUNDRED TWENTY EIGHT MB
	public static final int BUFFER_256M 					= BUFFER_256 * BUFFER_1M; 	// TWO HUNDRED FIFTY SIX MB
	public static final int BUFFER_512M 					= BUFFER_512 * BUFFER_1M; 	// FIVE HUNDRED TWELVE MB
	
	public static final int BUFFER_1G 						= BUFFER_1M * BUFFER_1M; 	// ONE GB
	
	public static final int BUFFER_4G 						= BUFFER_4 * BUFFER_1G; 	// FOUR GB
	public static final int BUFFER_8G 						= BUFFER_8 * BUFFER_1G; 	// EIGHT GB
	public static final int BUFFER_16G 						= BUFFER_16 * BUFFER_1G; 	// SIXTEEN GB
	public static final int BUFFER_32G 						= BUFFER_32 * BUFFER_1G; 	// THIRTY TWO GB
	public static final int BUFFER_64G 						= BUFFER_64 * BUFFER_1G; 	// SIXTY FOUR GB
	public static final int BUFFER_128G 					= BUFFER_128 * BUFFER_1G; 	// ONE HUNDRED TWENTY EIGHT GB
	public static final int BUFFER_256G 					= BUFFER_256 * BUFFER_1G; 	// TWO HUNDRED FIFTY SIX GB
	public static final int BUFFER_512G 					= BUFFER_512 * BUFFER_1G; 	// FIVE HUNDRED TWELVE GB
	
	public static final int BUFFER_1T 						= BUFFER_1G * BUFFER_1G; 	// ONE TB
	
}
