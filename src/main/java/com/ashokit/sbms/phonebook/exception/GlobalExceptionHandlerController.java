package com.ashokit.sbms.phonebook.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlerController {

	@ExceptionHandler(value = NoSuchContactFound.class)
	public ResponseEntity<?> noSuchContactFoundExceptionHandler(NoClassDefFoundError error) {
		return ResponseEntity.badRequest().body(error.getMessage());
	}
	
	@ExceptionHandler(value=PhoneBookException.class)
	public ResponseEntity<?> phoneBookExceptionHandler(PhoneBookException error){
		return ResponseEntity.status(500).body(error.getMessage());
	}
	
}
