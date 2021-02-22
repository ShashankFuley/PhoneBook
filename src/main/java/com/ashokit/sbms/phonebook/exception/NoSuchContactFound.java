package com.ashokit.sbms.phonebook.exception;

import java.util.Date;

public class NoSuchContactFound extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public NoSuchContactFound(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message +" "+ new Date();
	}
}
