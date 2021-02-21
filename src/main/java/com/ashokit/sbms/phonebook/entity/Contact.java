package com.ashokit.sbms.phonebook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="CONTACT_DETAILS")
public class Contact {

	@Id
	@Column(name="CONTACT_ID" , length = 10)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="CONTACT_NAME" , length = 50, nullable = false)
	private String name;
	
	@Column(name="CONTACT_EMAIL" , length = 50 , nullable = false)
	private String email;
	
	@Column(name="CONTACT_NUMBER" , length = 10 , nullable = false, unique = true)
	private Long number;
}
