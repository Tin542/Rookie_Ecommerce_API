package com.tinnt.AssigmentRookie.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import javax.validation.constraints.*;

@Getter
@Setter
public class SignupRequest {
	@NotBlank
	private String username;

	@NotBlank
	@Email(message = "Email invalid !")
	private String email;
	
	@NotBlank
	private String fullname;
	
	@NotBlank
    private String password;

	@NotBlank
	private String address;

	@Pattern(regexp = "(^$|[0-9]{10})", message = "phone is invalid !")
	private String phone;

	private Set<String> role;

}
