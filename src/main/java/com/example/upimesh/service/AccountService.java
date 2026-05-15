package com.example.upimesh.service;


import com.example.upimesh.entity.Account;
import com.example.upimesh.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {


    private final AccountRepository accountRepository;

    public List<Account>getAllAccounts(){

        return accountRepository.findAll();
    }
    public Account createAccount(
            String owner,
            Double balance){

        Account account= new Account();

        account.setOwner(owner);

        account.setBalance(balance);

        return accountRepository.save(account);
    }
}
