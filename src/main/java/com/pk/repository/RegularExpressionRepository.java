package com.pk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pk.model.RegularExpression;

public interface RegularExpressionRepository extends JpaRepository<RegularExpression, Long>{
	
	RegularExpression findByRegExpName(String regExpName);
}
