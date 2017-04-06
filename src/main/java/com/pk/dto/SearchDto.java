package com.pk.dto;

import lombok.Data;

@Data
public class SearchDto {

	String keyword="";
	
	String id = "";

	Integer selectedPageNumber=0;

	Integer recordPerPage=20;
	
	String param1;
	String param2;
	String param3;
}
