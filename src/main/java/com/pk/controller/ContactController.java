package com.pk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pk.dto.BaseDto;
import com.pk.model.Contact;
import com.pk.services.ContactService;



@RestController
@RequestMapping(value = "/api/pk/contact")

public class ContactController {

	@Autowired
	ContactService contactService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public BaseDto create(@RequestBody Contact contact) {
		return contactService.create(contact);
	}
	
	@RequestMapping(value = "/get/getall", method = RequestMethod.GET)
	public BaseDto get() {
		return contactService.getAll();
	}
	
	
	@RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
	public BaseDto getId(@PathVariable Long id) {
		return contactService.getById(id);
	}
}