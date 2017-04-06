package com.pk.validator;

import org.springframework.stereotype.Service;

import com.pk.enumeration.LovStatus;
import com.pk.model.Contact;
@Service
public class ContactValidator {
	
	public  void valid(Contact contact){
		
		contact.setEmail(contact.getEmail());
		contact.setName(contact.getName());
		contact.setMessage(contact.getMessage());
		if(contact.getStatus()== null){
			contact.setStatus(LovStatus.Active);
		}
		
	}
}

