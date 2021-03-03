package com.ashokit.sbms.phonebook.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.sbms.phonebook.constants.PhoneBookConstants;
import com.ashokit.sbms.phonebook.entity.Contact;
import com.ashokit.sbms.phonebook.properties.PhoneBookProperties;
import com.ashokit.sbms.phonebook.service.ContactServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/phonebook")
@Api(value = "PhoneBookRestController")
public class ContactRestController {

	private ContactServiceImpl contactService;
	
	private PhoneBookProperties phoneBookProperties;
	
	//Constructor for constructor injection
	ContactRestController(ContactServiceImpl contactService , PhoneBookProperties phoneBookProperties){
		this.contactService = contactService;
		this.phoneBookProperties = phoneBookProperties;
	}
	
	//Method to get Contact
	@ApiOperation(value = "To get contact by id")
	@GetMapping("/{id}")
	public ResponseEntity<Contact> getContactById(@PathVariable final String id){
		final Long contactId = Long.parseLong(id);
		final Contact contact = this.contactService.findContactById(contactId);
		if(contact != null) return ResponseEntity.ok(contact);
		else return ResponseEntity.status(204).body(null);
	}
	
	//Method to create Contact
	@ApiOperation(value = "To save contact")
	@PostMapping
	public ResponseEntity<String> createContact(@RequestBody final Contact contact){
		final boolean saveContact = this.contactService.saveContact(contact);
		final String message;
		if(saveContact) {
			message = this.phoneBookProperties.getMessages().get(PhoneBookConstants.CONTACT_CREATED); 
			return ResponseEntity.status(201).body(message);
		}
		else {
			message = this.phoneBookProperties.getMessages().get(PhoneBookConstants.CONTACT_NOT_CREATED);
			return ResponseEntity.status(400).body("Contact couldn't be created.");
		}
	}
	
	//Method to update Contact
	@ApiOperation(value = "To update contact")
	@PutMapping
	public ResponseEntity<String> updateContact(@RequestBody final Contact contact){
		final boolean saveContact = this.contactService.saveContact(contact);
		final String message;
		if(saveContact) { 
			message = this.phoneBookProperties.getMessages().get(PhoneBookConstants.CONTACT_UPDATED);
			return ResponseEntity.status(200).body(message);
		}
		else {
			message = this.phoneBookProperties.getMessages().get(PhoneBookConstants.CONTACT_NOT_UPDATED);
			return ResponseEntity.status(204).body(message);
		}
	}
	
	//Method to delete Contact
	@ApiOperation(value = "To delete contact")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteContact(@PathVariable Long id){
		boolean deleteContactById = this.contactService.deleteContactById(id);
		final String message;
		if(deleteContactById) {
			message = this.phoneBookProperties.getMessages().get(PhoneBookConstants.CONTACT_DELETED);
			return ResponseEntity.status(200).body(message);
		}
		else {
			message = this.phoneBookProperties.getMessages().get(PhoneBookConstants.CONTACT_NOT_DELETED);
			return ResponseEntity.status(400).body(message);
		}
	}
	
	//Method to get all contact
	@ApiOperation(value = "To get all the contact")
	@GetMapping("/all")
	public ResponseEntity<List<Contact>> getAllContact(){
		final List<Contact> findAllContacts = this.contactService.findAllContacts();
		return ResponseEntity.ok(findAllContacts);
	}
}
