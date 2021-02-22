package com.ashokit.sbms.phonebook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ashokit.sbms.phonebook.entity.Contact;
import com.ashokit.sbms.phonebook.exception.NoSuchContactFound;
import com.ashokit.sbms.phonebook.exception.PhoneBookException;
import com.ashokit.sbms.phonebook.repository.ContactRepository;

@Service
public class ContactServiceImpl implements IContactService {

	private ContactRepository contactRepository;
	
	
	ContactServiceImpl(ContactRepository contactRepository){
		this.contactRepository = contactRepository;
	}
	
	//Method to save or update contact
	@Override
	public boolean saveContact(Contact contact) {
		try {
			Contact savedContact = contactRepository.save(contact);
			return savedContact != null;
		}catch(Exception e){
			throw new PhoneBookException("OOPS!! There was an error while saving the contact.");
		}
	}

	/*Method to find all contact*/
	@Override
	public List<Contact> findAllContacts() {
		return contactRepository.findAll();
	}

	/*Method to find contact by id*/
	@Override
	public Contact findContactById(Long id) {
		try {
			Optional<Contact> contact = contactRepository.findById(id);
			return contact.isPresent() ? contact.get() : null;
		}catch(Exception e) {
			throw new NoSuchContactFound("No Contact found with id:- "+id);
		}
	}

	/*Method to delete contact by id*/
	@Override
	public boolean deleteContactById(Long id) {
		try {
			contactRepository.deleteById(id);
			return true;
		}catch(Exception e) {
			throw new PhoneBookException("OOPS!! There was an error while deleting the contact.");
		}
	}

}
