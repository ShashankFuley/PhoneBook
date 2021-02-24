package com.ashokit.sbms.phonebook.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ashokit.sbms.phonebook.entity.Contact;
import com.ashokit.sbms.phonebook.exception.NoSuchContactFound;
import com.ashokit.sbms.phonebook.exception.PhoneBookException;
import com.ashokit.sbms.phonebook.repository.ContactRepository;

@Service
public class ContactServiceImpl implements IContactService {

	private ContactRepository contactRepository;
	
	private Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);
	
	ContactServiceImpl(ContactRepository contactRepository){
		this.contactRepository = contactRepository;
	}
	
	//Method to save or update contact
	@Override
	public boolean saveContact(Contact contact) {
		logger.debug("**** saveContact() - Execution Started ****");
		try {
			Contact savedContact = contactRepository.save(contact);
			if(savedContact != null) {
				logger.info("**** saveContact() - Contact Saved ****");
				return true;
			}
		}catch(Exception e){
			logger.error("** Exception Occured : ** " + e.getMessage());
			throw new PhoneBookException("OOPS!! There was an error while saving the contact.");
		}
		logger.info("**** saveContact() - Contact Not Saved ****");
		logger.debug("**** saveContact() - Execution Ended ****");
		return false;
	}

	/*Method to find all contact*/
	@Override
	public List<Contact> findAllContacts() {
		logger.debug("**** findAllContacts() - Execution Started ****");
		List<Contact> contacts = contactRepository.findAll();
		logger.info("**** findAllContacts() - Execution Ended ****");
		return contacts;
	}

	/*Method to find contact by id*/
	@Override
	public Contact findContactById(Long id) {
		logger.debug("**** findContactById() - Execution Started ****");
		try {
			Optional<Contact> contact = contactRepository.findById(id);
			if(contact.isPresent()) {
				logger.info("**** findContactById() - Contact Found ****");
				return contact.get();
			}
		}catch(Exception e) {
			logger.error("** Exception Occured : ** " + e.getMessage());
			throw new NoSuchContactFound("No Contact found with id:- "+id);
		}
		logger.info("**** findContactById() - Contact Not Found ****");
		logger.debug("**** findContactById() - Execution Ended ****");
		return null;
	}

	/*Method to delete contact by id*/
	@Override
	public boolean deleteContactById(Long id) {
		try {
			logger.info("**** deleteContactById() - Execution Started ****");
			contactRepository.deleteById(id);
			logger.info("**** deleteContactById() - Contact Deleted ****");
			return true;
		}catch(Exception e) {
			logger.error("** Exception Occured : ** " + e.getMessage());
			logger.info("**** deleteContactById() - Contact Not Found ****");
			logger.debug("**** deleteContactById() - Execution Ended ****");
			throw new PhoneBookException("OOPS!! There was an error while deleting the contact.");
		}
	}

}
