package com.tinnt.AssigmentRookie.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tinnt.AssigmentRookie.entity.Account;
import com.tinnt.AssigmentRookie.repository.AccountRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private AccountRepository accRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account user = accRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User Not Found with -> username: " + username)
                );

        return UserDetailsImpl.build(user);
	}
}
