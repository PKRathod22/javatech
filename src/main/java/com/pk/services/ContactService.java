package com.pk.services;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pk.dto.BaseDto;
import com.pk.exception.ErrorCode;
import com.pk.exception.RestException;
import com.pk.exception.UniqueKey;
import com.pk.model.Contact;
import com.pk.repository.ContactRepository;
import com.pk.util.AppUtil;
import com.pk.validator.ContactValidator;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ContactService {
	
	@Autowired
	AppUtil appUtil; 
	
	@Autowired
	ContactRepository contactRepository;
	
	@Autowired
	ContactValidator contactValidator;
	
	public BaseDto getAll(){
		BaseDto dto = new BaseDto();
		try{
		List<Contact> contactList = contactRepository.findAll();
		dto.setResponseCode(ErrorCode.SUCCESS);
        dto.setResponseObject(contactList);
		log.info("getAll method is executed successfully..");
		}
		catch (RestException exception) {
			log.error("Exception in getAll method : " , exception);
			dto.setResponseCode(exception.getMessage());
		} catch (Exception exception) {
			log.error("Exception in getAll method : " , exception);
			dto.setResponseCode(ErrorCode.FAILED);
		}
		return appUtil.setDesc(dto);

	}
	
	
	public BaseDto create(Contact contact ){
		BaseDto dto =new BaseDto();
		try{
			contactValidator.valid(contact);
			contact =contactRepository.save(contact);
			dto.setResponseCode(ErrorCode.SUCCESS);
			dto.setResponseObject(contact);
		}catch(RestException e){
			log.info("Error while sending contact",e);
			dto.setResponseCode(e.getMessage());	
		}
		catch(Exception e){

			String exceptionCause = ExceptionUtils.getRootCauseMessage(e);
			log.error("Exception ", e);
			log.info("Exception Cause ::: "  , exceptionCause);
			dto.setResponseCode(ErrorCode.ERROR_GENERIC);
			if (exceptionCause.contains(UniqueKey.UK_CONTACT_EMAIL)) {
				dto.setResponseCode(ErrorCode.UK_CONTACT_EMAIL_ALREADY_EXIST);
			}
		
		}
		log.info("Create method is end.");
		return appUtil.setDesc(dto);
	}
	public BaseDto getById(Long id ){
		BaseDto dto =new BaseDto();
		try{
		Contact	contact =contactRepository.findById(id);
		dto.setResponseObject(contact);
		} catch (RestException re) {
			log.error("Exception in ContactService -> getById method ", re);
			dto.setResponseCode(re.getMessage());

		} catch (Exception e) {
			log.error("Exception in ContactService -> getById method ", e);
			dto.setResponseCode(ErrorCode.ERROR_GENERIC);

		}	

		return appUtil.setDesc(dto);
	}	
}
