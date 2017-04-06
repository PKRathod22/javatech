package com.pk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pk.dto.BaseDto;
import com.pk.services.RegularExpressionService;


@RestController
@RequestMapping(value = "/api/v1/regularexpression")
public class RegularExpressionController {
	
	@Autowired
	RegularExpressionService regularExpressionService;
	
	@RequestMapping(value = "/get/all", method = RequestMethod.GET)
	public BaseDto getall() {
		return regularExpressionService.getAll();
	}
}
