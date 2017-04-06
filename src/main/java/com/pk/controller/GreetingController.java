package com.pk.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")
public class GreetingController {
	
	@RequestMapping("/greeting")
	public String hello() {
	    
		return "praveen";
	    		
	    		
	}



}










/*package com.pk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pk.dto.DummyDto;
import com.pk.services.GreetingService;

@RestController
@RequestMapping(value = "abc")
public class GreetingController {
	
@Autowired
GreetingService greetingService;

@RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
 public DummyDto hello(@PathVariable String name) {
		System.out.println("callled greeting controller"+name);
    return greetingService.get(name);
  }


@RequestMapping("/greeting")
public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
    
	return "sathish";
    		
    		
}









}
*/