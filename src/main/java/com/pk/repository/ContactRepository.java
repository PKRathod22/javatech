package com.pk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pk.model.Contact;

public interface ContactRepository extends JpaRepository <Contact, Long> {

	Contact findById(Long id);
}
