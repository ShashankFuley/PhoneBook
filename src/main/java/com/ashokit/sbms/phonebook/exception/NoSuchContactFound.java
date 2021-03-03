package com.ashokit.sbms.phonebook.exception;

import java.util.Date;

public class NoSuchContactFound extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private final String message;
	
	public NoSuchContactFound(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return this.message+" "+ new Date();
	}
}
