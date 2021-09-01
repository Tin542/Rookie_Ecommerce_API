package com.tinnt.AssigmentRookie.payload.response;

import java.util.List;

public class JwtResponse {
	private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String fullname;
    private String email;
    private String phone;
    private String address;
    private List<String> roles;

	public JwtResponse(String token,
					   Long id,
					   String username,
					   String fullname,
					   String email,
					   String phone,
					   String address,
					   List<String> roles) {
		this.token = token;
		this.id = id;
		this.username = username;
		this.fullname = fullname;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
