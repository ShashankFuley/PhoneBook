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

import com.ashokit.sbms.phonebook.entity.Contact;
import com.ashokit.sbms.phonebook.service.ContactServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/phonebook")
@Api(value = "PhoneBookRestController")
public class ContactRestController {

	private ContactServiceImpl contactService;
	
	//Constructor for constructor injection
	ContactRestController(ContactServiceImpl contactService){
		this.contactService = contactService;
	}
	
	//Method to get Contact
	@ApiOperation(value = "To get contact by id")
	@GetMapping("/{id}")
	public ResponseEntity<Contact> getContactById(@PathVariable String id){
		Long contactId = Long.parseLong(id);
		Contact contact = this.contactService.findContactById(contactId);
		if(contact != null) return ResponseEntity.ok(contact);
		else return ResponseEntity.status(204).body(null);
	}
	
	//Method to create Contact
	@ApiOperation(value = "To save contact")
	@PostMapping
	public ResponseEntity<String> saveContact(@RequestBody Contact contact){
		boolean saveContact = this.contactService.saveContact(contact);
		if(saveContact) return ResponseEntity.status(201).body("Contact created successfully.");
		else return ResponseEntity.status(400).body("Contact couldn't be created.");
	}
	
	//Method to update Contact
	@ApiOperation(value = "To update contact")
	@PutMapping
	public ResponseEntity<String> updateContact(@RequestBody Contact contact){
		boolean saveContact = this.contactService.saveContact(contact);
		if(saveContact) return ResponseEntity.status(200).body("Contact updated successfully.");
		else return ResponseEntity.status(204).body("Contact couldn't be saved.");
	}
	
	//Method to delete Contact
	@ApiOperation(value = "To delete contact")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteContact(@PathVariable Long id){
		boolean deleteContactById = this.contactService.deleteContactById(id);
		if(deleteContactById) return ResponseEntity.status(200).body("Contact deleted successfully.");
		else return ResponseEntity.status(400).body("Contact couldn't be deleted.");
	}
	
	//Method to get all contact
	@ApiOperation(value = "To get all the contact")
	@GetMapping("/all")
	public ResponseEntity<List<Contact>> getAllContact(){
		List<Contact> findAllContacts = this.contactService.findAllContacts();
		return ResponseEntity.ok(findAllContacts);
	}
}
