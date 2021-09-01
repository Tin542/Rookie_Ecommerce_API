package com.tinnt.AssigmentRookie.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {

	@NotBlank(message = "Please enter username !")
	private String username;
	
	@NotBlank(message = "please enter password !")
	private String password;
	
}
