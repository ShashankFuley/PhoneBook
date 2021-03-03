package com.ashokit.sbms.phonebook.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.ashokit.sbms.phonebook.entity.Contact;
import com.ashokit.sbms.phonebook.exception.NoSuchContactFound;
import com.ashokit.sbms.phonebook.exception.PhoneBookException;
import com.ashokit.sbms.phonebook.properties.PhoneBookProperties;
import com.ashokit.sbms.phonebook.repository.ContactRepository;


class ContactServiceImpTest {

	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this); 
	}
	
	@Mock
	ContactRepository contactRepository;
	
	@InjectMocks
	ContactServiceImpl contactService;
	
	@Mock
	PhoneBookProperties phoneBookProperties;
	
	//Method to test findAllContacts() method of ContactService
	@Test
	public void testFindAllContacts() {
		List<Contact> list = new ArrayList<>();
		list.add(new Contact(108L ,"Shashank" , "shashank@gmail.com" , 9876543210L));
		list.add(new Contact(109L ,"Ashok" , "ashok@gmail.com" , 9876543211L));
		Mockito.when(this.contactRepository.findAll()).thenReturn(list);
		List<Contact> list2 = this.contactService.findAllContacts();
		assertTrue(list2.equals(list));
	}
	
	//Method to test True from saveContact() method of ContactService
	@Test
	public void testSaveContactTrue() {
		Contact contact = new Contact(null ,"Shashank" , "shashank@gmail.com" , 9876543210L);
		Contact contact1 = new Contact(108L ,"Shashank" , "shashank@gmail.com" , 9876543210L);
		Mockito.when(this.contactRepository.save(contact)).thenReturn(contact1);
		boolean result = this.contactService.saveContact(contact);
		assertEquals(true,result);
	}
	
	//Method to test false from saveContact() method of ContactService  
	@Test
	public void testSaveContactFalse() {
		Contact contact = new Contact(null ,"Shashank" , "shashank@gmail.com" , 9876543210L);
		Mockito.when(this.contactRepository.save(contact)).thenReturn(null);
		boolean result = this.contactService.saveContact(contact);
		assertEquals(false,result);
	}
	
	//Method to test Error class and Error message from saveContact() method of ContactService
	@Test
	public void testSaveContactError() {
		Mockito.when(this.contactRepository.save(null)).thenThrow(IllegalArgumentException.class);
		Map<String,String> map = new HashMap<>();
		map.put("saveContactExceptionMessage", "OOPS!! There was an error while saving the contact.");
		Mockito.when(this.phoneBookProperties.getMessages()).thenReturn(map);
		Throwable throwable = assertThrows(PhoneBookException.class, ()->{this.contactService.saveContact(null);});
		String string = "OOPS!! There was an error while saving the contact."+" "+new Date();
		assertTrue(throwable.getMessage().equals(string));
	}
	
	//Method to test Contact from findContactById() method of ContactService
	@Test
	public void testFindContactByIdContact() {
		Optional<Contact> option = Optional.of(new Contact(108L ,"Shashank" , "shashank@gmail.com" , 9876543210L));
		Mockito.when(this.contactRepository.findById(108L)).thenReturn(option);
		Contact contact = this.contactService.findContactById(108L);
		assertNotNull(contact);
		assertEquals( "Shashank" , contact.getName());
		assertEquals( "shashank@gmail.com" , contact.getEmail());
		assertEquals( 9876543210L ,contact.getNumber());
		assertEquals(108L , contact.getId());
	}
	
	//Method to test null from findContactById() method of ContactService
	@Test
	public void testFindContactByIdNull() {
		Optional<Contact> option = Optional.ofNullable(null);
		Mockito.when(this.contactRepository.findById(108L)).thenReturn(option);
		Contact contact = this.contactService.findContactById(108L);
		assertNull(contact);
	}
	
	//Method to test Error class and Error message from findContactById() method of ContactService
	@Test
	public void testFindContactByIdError() {
		Mockito.when(this.contactRepository.findById(108L)).thenThrow(NoSuchContactFound.class);
		Map<String,String> map = new HashMap<>();
		map.put("findContactByIdExceptionMessage", "OOPs!! there was an error while retriving contact with id:-");
		Mockito.when(this.phoneBookProperties.getMessages()).thenReturn(map);
		Throwable throwable = assertThrows(NoSuchContactFound.class, ()->{this.contactService.findContactById(108L);});
		String string = "OOPs!! there was an error while retriving contact with id:-"+108+" "+new Date();
		assertTrue(throwable.getMessage().equals(string));
	}
	
	/*Method to test True from deleteContactById() method of ContactService.
	 *Also it has been verified that delete method of contactRepository is being called once.
	 */
	@Test
	public void testDeleteContactByIdTrue() {
		doNothing().when(this.contactRepository).deleteById(108L);
		boolean deleted = this.contactService.deleteContactById(108L);
		verify(this.contactRepository, times(1)).deleteById(108L);
		assertEquals(true,deleted);
	}
	
	/*Method to test Error from deleteContactById() method of ContactService.
	 *Also it has been verified that delete method of contactRepository is being called once.
	 */
	@Test
	public void testDeleteContactByIdError() {
		Map<String,String> map = new HashMap<>();
		map.put("deleteContactByIdExceptionMessage", "OOPS!! There was an error while deleting the contact with id:-");
		Mockito.when(this.phoneBookProperties.getMessages()).thenReturn(map);
		doThrow(IllegalArgumentException.class).when(this.contactRepository).deleteById(108L);
		Throwable throwable =  assertThrows(PhoneBookException.class, ()->{contactService.deleteContactById(108L);});
		verify(this.contactRepository, times(1)).deleteById(108L);
		String string = "OOPS!! There was an error while deleting the contact with id:-108"+" "+new Date();
		assertEquals(string,throwable.getMessage());
	}
}
