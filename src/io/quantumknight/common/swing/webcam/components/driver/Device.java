package io.quantumknight.common.swing.webcam.components.driver;
/********************************************************************************************
//* Filename: 		Device.java
//* Revision: 		1.0
//* Author: 		
//* Created On: 	
//* Modified by: 	
//* Modified On: 	
//* 				
//* Description:    WRAPPER IMPLEMENTATION - SARXOS DEVICE
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

import org.bridj.Pointer;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.cpp.CPPObject;
import org.bridj.cpp.CPPRuntime;


/**
 * <i>native declaration : line 1</i><br>
 * This file was autogenerated by <a
 * href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.free.fr/">Olivier Chafik</a> that
 * <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a
 * few opensource projects.</a>.<br>
 * For help, please visit <a
 * href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a
 * href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("OpenIMAJGrabber")
@Runtime(CPPRuntime.class)
@SuppressWarnings("all")
public final class Device extends CPPObject {

	public Device() {
		super();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Device(Pointer pointer) {
		super(pointer);
	}

	// / C type : const char*
	@Field(0)
	protected Pointer<Byte> name() {
		return this.io.getPointerField(this, 0);
	}

	// / C type : const char*
	@Field(0)
	protected Device name(Pointer<Byte> name) {
		this.io.setPointerField(this, 0, name);
		return this;
	}

	// / C type : const char*
	@Field(1)
	protected Pointer<Byte> identifier() {
		return this.io.getPointerField(this, 1);
	}

	// / C type : const char*
	@Field(1)
	protected Device identifier(Pointer<Byte> identifier) {
		this.io.setPointerField(this, 1, identifier);
		return this;
	}

	protected native Pointer<Byte> getName();

	public String getNameStr() {
		return getName().getCString();
	}

	protected native Pointer<Byte> getIdentifier();

	public String getIdentifierStr() {
		return getIdentifier().getCString();
	}

	/*
	 * (non-Javadoc)
	 * @see org.bridj.NativeObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		return o.toString().equals(this.toString());
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}

	@Override
	public String toString() {
		return String.format("Device[%s]=\"%s\"", getIdentifierStr(), getNameStr());
	}
}