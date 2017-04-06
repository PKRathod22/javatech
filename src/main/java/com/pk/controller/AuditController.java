package com.pk.controller;



import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pk.dto.BaseDto;
import com.pk.exception.ErrorCode;
import com.pk.exception.RestException;
import com.pk.model.City;
import com.pk.model.Contact;
import com.pk.repository.CityRepository;
import com.pk.util.AppUtil;
import com.pk.util.ValidateUtil;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(value="/api/audit")
@Log4j2
public class AuditController {

	@Autowired
	AppUtil appUtil;
	
	@Autowired
	CityRepository cityRepository;

	@RequestMapping(value="get/{id}",method=RequestMethod.GET)
	public BaseDto get(@PathVariable Long id){
		BaseDto dto=new BaseDto(); 
		City city= cityRepository.findById(id);
		dto.setResponseObject(city);
		return appUtil.setDesc(dto);
		 
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
    public BaseDto createAudit(@RequestBody City city){
		
		 log.info("auditExample called..");
		BaseDto dto = new BaseDto();
		if(city.getContact()!=null && city.getContact().size()>0){
			log.info("contact List size"+city.getContact().size());
			log.info("contact List size"+city.getContact().get(0).getName());
		}else{
			city.setContact(new ArrayList<>());
		}
        city= cityRepository.save(city);
        log.info("auditExample Create Finished..");
        dto.setResponseCode(ErrorCode.SUCCESS);
        dto.setResponseObject(city);
        return appUtil.setDesc(dto);

    }
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
    public BaseDto saveAudit(@RequestBody City city){
		 log.info("auditExample update called..");
		BaseDto dto = new BaseDto();
		auditValidate(city);
		ValidateUtil.notNull(city.getId(), "audit id not found");
        city= cityRepository.save(city);
        log.info("auditExample update Finished..");
        dto.setResponseCode(ErrorCode.SUCCESS);
        dto.setResponseObject(city);
        return appUtil.setDesc(dto);

    }
	
	private void auditValidate(City city){
		if(city.getName() == null){
			 throw new RestException("name can not be empty");
			}else if(city.getCountry() == null){
				 throw new RestException("country can not be empty");
				}else if(city.getState() == null){
					 throw new RestException("state can not be empty");
				}
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
	public BaseDto delete(@PathVariable Long id){
		BaseDto dto=new BaseDto();
		 cityRepository.delete(id);
		dto.setResponseObject(id);
		 return appUtil.setDesc(dto);	
	}
	
}