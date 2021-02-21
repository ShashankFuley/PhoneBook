package com.ashokit.sbms.phonebook.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ashokit.sbms.phonebook.service.ContactServiceImpl;

@RestController
public class ContactRestController {

	private ContactServiceImpl contactService;
	
	//Constructor for constructor injection
	ContactRestController(ContactServiceImpl contactService){
		this.contactService = contactService;
	}
	
}
