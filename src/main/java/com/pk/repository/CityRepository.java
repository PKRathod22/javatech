package com.pk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pk.model.City;

public interface CityRepository extends JpaRepository <City, Long> {

	City findById(Long id);
}
