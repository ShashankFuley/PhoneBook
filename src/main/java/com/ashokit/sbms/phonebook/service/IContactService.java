package com.ashokit.sbms.phonebook.service;

import java.util.List;

import com.ashokit.sbms.phonebook.entity.Contact;

public interface IContactService {

	//Method to save and update contact
	boolean saveContact(Contact contact);
	
	//Method to find All Contacts
	List<Contact> findAllContacts();
	
	//Method to find contact by id
	Contact findById(Long id);
	
	//Method to delete contact
	boolean deleteContact(Long id);
}
