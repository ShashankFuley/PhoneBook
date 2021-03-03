package com.ashokit.sbms.phonebook.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ashokit.sbms.phonebook.constants.PhoneBookConstants;
import com.ashokit.sbms.phonebook.entity.Contact;
import com.ashokit.sbms.phonebook.exception.NoSuchContactFound;
import com.ashokit.sbms.phonebook.exception.PhoneBookException;
import com.ashokit.sbms.phonebook.properties.PhoneBookProperties;
import com.ashokit.sbms.phonebook.repository.ContactRepository;

@Service
public class ContactServiceImpl implements IContactService {

	private ContactRepository contactRepository;
	
	private PhoneBookProperties phoneBookProperties;
	
	private Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);
	
	ContactServiceImpl(ContactRepository contactRepository , PhoneBookProperties phoneBookProperties){
		this.contactRepository = contactRepository;
		this.phoneBookProperties = phoneBookProperties;
	}
	
	//Method to save or update contact
	@Override
	public boolean saveContact(final Contact contact) {
		logger.debug("**** saveContact() - Execution Started ****");
		try {
			final Contact savedContact = contactRepository.save(contact);
			if(savedContact != null) {
				logger.info("**** saveContact() - Contact Saved ****");
				return true;
			}
		}catch(Exception e){
			logger.error(String.format("**** saveContact() - Exception Occured : **** %s", e.getMessage()));
			final String message = this.phoneBookProperties.getMessages().get(PhoneBookConstants.SAVE_CONTACT_EXCEPTION);
			throw new PhoneBookException(message);
		}
		logger.info("**** saveContact() - Contact Not Saved ****");
		logger.debug("**** saveContact() - Execution Ended ****");
		return false;
	}

	/*Method to find all contact*/
	@Override
	public List<Contact> findAllContacts() {
		logger.debug("**** findAllContacts() - Execution Started ****");
		final List<Contact> contacts = contactRepository.findAll();
		logger.info("**** findAllContacts() - Execution Ended ****");
		return contacts;
	}

	/*Method to find contact by id*/
	@Override
	public Contact findContactById(final Long id) {
		logger.debug("**** findContactById() - Execution Started ****");
		try {
			Optional<Contact> contact = contactRepository.findById(id);
			if(contact.isPresent()) {
				logger.info("**** findContactById() - Contact Found ****");
				return contact.get();
			}
		}catch(Exception e) {
			logger.error(String.format("**** findContactById() - Exception Occured : **** %s" , e.getMessage()));
			final String message = this.phoneBookProperties.getMessages().get(PhoneBookConstants.FIND_CONTACT_EXCEPTION);
			throw new NoSuchContactFound(message+id);
		}
		logger.info("**** findContactById() - Contact Not Found ****");
		logger.debug("**** findContactById() - Execution Ended ****");
		return null;
	}

	/*Method to delete contact by id*/
	@Override
	public boolean deleteContactById(final Long id) {
		try {
			logger.info("**** deleteContactById() - Execution Started ****");
			contactRepository.deleteById(id);
			logger.info("**** deleteContactById() - Contact Deleted ****");
			return true;
		}catch(Exception e) {
			logger.error(String.format("**** deleteContactById() - Exception Occured : **** %s",e.getMessage()));
			logger.info("**** deleteContactById() - Contact Not Found ****");
			logger.debug("**** deleteContactById() - Execution Ended ****");
			final String message = this.phoneBookProperties.getMessages().get(PhoneBookConstants.DELETE_CONTACT_EXCEPTION);
			throw new PhoneBookException(message+id);
		}
	}

}
