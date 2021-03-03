package com.ashokit.sbms.phonebook.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlerController {

	@ExceptionHandler(value = NoSuchContactFound.class)
	public ResponseEntity<String> noSuchContactFoundExceptionHandler(NoSuchContactFound error) {
		return ResponseEntity.status(500).body(error.getMessage());
	}
	
	@ExceptionHandler(value=PhoneBookException.class)
	public ResponseEntity<String> phoneBookExceptionHandler(PhoneBookException error){
		return ResponseEntity.status(500).body(error.getMessage());
	}
	
}
