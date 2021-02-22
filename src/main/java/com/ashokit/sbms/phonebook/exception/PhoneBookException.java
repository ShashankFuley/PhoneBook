package com.ashokit.sbms.phonebook.exception;

import java.util.Date;

public class PhoneBookException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	public PhoneBookException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message +" "+ new Date();
	}
}
