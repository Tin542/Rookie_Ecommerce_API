package com.tinnt.AssigmentRookie.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tinnt.AssigmentRookie.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	
	Optional<Account> findByUsername(String username);
	
	Boolean existsByUsername(String username);
}
