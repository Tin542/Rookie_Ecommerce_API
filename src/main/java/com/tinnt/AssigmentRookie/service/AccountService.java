package com.tinnt.AssigmentRookie.service;

import com.tinnt.AssigmentRookie.entity.Account;

import java.util.Optional;

public interface AccountService {
    Optional<Account> findByUsername(String username);
}
