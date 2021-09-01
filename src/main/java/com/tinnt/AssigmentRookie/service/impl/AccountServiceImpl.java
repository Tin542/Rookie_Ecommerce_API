package com.tinnt.AssigmentRookie.service.impl;

import com.tinnt.AssigmentRookie.entity.Account;
import com.tinnt.AssigmentRookie.repository.AccountRepository;
import com.tinnt.AssigmentRookie.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Optional<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }
}
