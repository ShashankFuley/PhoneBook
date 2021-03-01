package com.ashokit.sbms.phonebook.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ashokit.sbms.phonebook.entity.Contact;
import com.ashokit.sbms.phonebook.service.ContactServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(value = ContactRestController.class)
class ContactRestControllerTest {

	@MockBean
	private ContactServiceImpl contactService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Test
	public void testSaveContact() throws Exception{
		when(contactService.saveContact(Mockito.any())).thenReturn(true);
		
		Contact contact = new Contact(108L, "Shashank", "shashank@gmail.com", 9876543210L);
		
		String json = this.objectMapper.writeValueAsString(contact);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/phonebook").contentType("application/json").content(json);
		
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		
		int status = response.getStatus();
		
		assertEquals(201,status);
	}
	
	@Test
	public void testSaveContact1() throws Exception{
		when(contactService.saveContact(Mockito.any())).thenReturn(false);
		
		Contact contact = new Contact(108L, "Shashank", "shashank@gmail.com", 9876543210L);
		
		String json = this.objectMapper.writeValueAsString(contact);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/phonebook")
																			 .contentType("application/json")
				 															 .content(json);
		
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		
		int status = response.getStatus();
		
		assertEquals(400,status);
	}
	
	@Test
	public void testGetAllContact() throws Exception{
		Contact contact = new Contact(Long.getLong("108"), "Shashank", "shashank@gmail.com", Long.getLong("9876543210"));
		
		List<Contact> list = new ArrayList<Contact>();
		
		list.add(contact);
		
		when(contactService.findAllContacts()).thenReturn(list);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/phonebook/all");
		
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		
		int status = response.getStatus();
		
		assertEquals(200,status);
	}
	
	@Test
	public void testDeleteByIdTrue() throws Exception{
		when(contactService.deleteContactById(Mockito.anyLong())).thenReturn(true);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/phonebook/{id}",101L);
		
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		
		int status = response.getStatus();
		
		assertEquals(200,status);
	}
	
	@Test
	public void testDeleteByIdFalse() throws Exception{
		when(contactService.deleteContactById(Mockito.anyLong())).thenReturn(false);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/phonebook/{id}",101L);
		
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		
		int status = response.getStatus();
		
		assertEquals(400,status);
	}
	
	@Test
	public void testGetContactByIdContact() throws Exception{
		Contact contact = new Contact(Long.getLong("108"), "Shashank", "shashank@gmail.com", Long.getLong("9876543210"));
		
		when(contactService.findContactById(Mockito.anyLong())).thenReturn(contact);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/phonebook/{id}",108L);
		
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response  = mvcResult.getResponse();
		
		int status = response.getStatus();
		
		assertEquals(200, status);
	}
	
	@Test
	public void testGetContactByIdNull() throws Exception{
		Mockito.when(contactService.findContactById(Mockito.anyLong())).thenReturn(null);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/phonebook/{id}",108L);
		
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		
		int status = response.getStatus();
		
		assertEquals(204,status);
	}
	
	@Test
	public void testUpdateContactTrue() throws Exception{
		Mockito.when(contactService.saveContact(Mockito.any())).thenReturn(true);
		
		Contact contact = new Contact(108L, "Shashank", "shashank@gmail.com", 9876543210L);
		
		String json = this.objectMapper.writeValueAsString(contact);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/phonebook").contentType("application/json").content(json);
		
		MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		
		int status = response.getStatus();
	    
		assertEquals(200,status);
	}
	
	@Test
	public void testUpdateContactFalse() throws Exception{
		Mockito.when(contactService.saveContact(Mockito.any())).thenReturn(false);
		
		Contact contact = new Contact(108L, "Shashank", "shashank@gmail.com", 9876543210L);
		
		String json = this.objectMapper.writeValueAsString(contact);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/phonebook").contentType("application/json").content(json);
		
		MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		
		int status = response.getStatus();
		
		assertEquals(204, status);
	}
}
