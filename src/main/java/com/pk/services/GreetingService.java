package com.pk.services;

import org.springframework.stereotype.Service;


@Service
public class GreetingService {
public String get(String name){
	
	System.out.println("callled greeting service");
	return "";
}
}
