package com.ashokit.sbms.phonebook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ashokit.sbms.phonebook.entity.Contact;
import com.ashokit.sbms.phonebook.repository.ContactRepository;

@Service
public class ContactServiceImpl implements IContactService {

	private ContactRepository contactRepository;
	
	ContactServiceImpl(ContactRepository contactRepository){
		this.contactRepository = contactRepository;
	}
	
	@Override
	public boolean saveContact(Contact contact) {
		try {
			Contact savedContact = contactRepository.save(contact);
			return savedContact != null;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Contact> findAllContacts() {
		return contactRepository.findAll();
	}

	@Override
	public Contact findContactById(Long id) {
		try {
			Optional<Contact> contact = contactRepository.findById(id);
			return contact.isPresent() ? contact.get() : null;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteContactById(Long id) {
		try {
			contactRepository.deleteById(id);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
