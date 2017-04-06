package com.pk.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pk.dto.BaseDto;
import com.pk.exception.ErrorCode;
import com.pk.model.RegularExpression;
import com.pk.repository.RegularExpressionRepository;
import com.pk.util.ValidateUtil;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class RegularExpressionService {

	@Autowired
	RegularExpressionRepository regularExpressionRepository;
	
	public BaseDto getAll() {
		BaseDto baseDto = new BaseDto();
		try{
				Map<String,String> mapObj = new HashMap<>();
				for(RegularExpression regExp : regularExpressionRepository.findAll()){
					mapObj.put(regExp.getRegExpName(), regExp.getJsRegExp());
				}
				baseDto.setResponseObject(mapObj);
				baseDto.setResponseCode(ErrorCode.SUCCESS);
		} catch(Exception ex){
			log.error("Exception on Getting : ", ex);
			baseDto.setResponseCode(ErrorCode.FAILED);
		}
		return baseDto;
	}
	
	public void validate(Object object, String regExpName, String errorCode){
		ValidateUtil.checkPattern(object, getRegExp(regExpName), errorCode);
	}
	
	private String getRegExp(String regExpName){
		RegularExpression regExp = regularExpressionRepository.findByRegExpName(regExpName);
		if(regExp!=null)
			return regExp.getJavaRegExp();
		return null;
	}
}
