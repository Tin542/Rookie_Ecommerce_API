package com.tinnt.AssigmentRookie.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {
	
	private String errorCode;
	private String successCode;
	private Object data;

}
