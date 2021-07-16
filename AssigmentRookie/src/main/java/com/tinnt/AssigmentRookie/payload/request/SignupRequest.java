package com.tinnt.AssigmentRookie.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import javax.validation.constraints.*;

@Getter
@Setter
public class SignupRequest {
	@NotBlank
	@Size(min = 3, max = 20, message = "username required 3-20 chars !")
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email(message = "Email invalid !")
	private String email;
	
	@NotBlank
    @Size(max = 50, message = "Full name required maximum 50 chars !")
	private String fullname;
	
	@NotBlank
    @Size(min = 6, max = 20, message = "Password required 6-20 chars !")
    private String password;

	@NotBlank
	@Size(min = 6, max = 20, message = "Address required 6-20 chars !")
	private String address;

	@Pattern(regexp = "(^$|[0-9]{10})", message = "phone is invalid !")
	@Size(min = 1, max = 20, message = "Address required 6-20 chars !")
	private String phone;

	private Set<String> role;
	
}
