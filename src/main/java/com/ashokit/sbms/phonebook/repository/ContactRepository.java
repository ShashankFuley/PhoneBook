package com.ashokit.sbms.phonebook.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.sbms.phonebook.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Serializable> {

}
