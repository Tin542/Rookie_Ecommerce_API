package com.tinnt.AssigmentRookie.security.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinnt.AssigmentRookie.entity.Account;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String username;

	private String fullname;
	
	@JsonIgnore
    private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	 
	public UserDetailsImpl(Long id, String username, String fullname, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.fullname = fullname;
		this.password = password;
		this.authorities = authorities;
	}
	
	public static UserDetailsImpl build(Account acc) {
        List<GrantedAuthority> authorities = acc.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getName().name()))
            .collect(Collectors.toList());

        return new UserDetailsImpl(
        		acc.getAccountID(),
        		acc.getUsername(),
        		acc.getFullname(),
        		acc.getPassword(),
        		authorities);
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getId() {
        return id;
    }
	
	public String getFullname() {
        return fullname;
    }
	
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(authorities, fullname, id, password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDetailsImpl other = (UserDetailsImpl) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
