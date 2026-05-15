package com.example.upimesh.controller;

import com.example.upimesh.entity.Account;
import com.example.upimesh.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AccountController {

    private final AccountRepository accountRepository;

    @GetMapping
    public List<Account> getAccounts(){

        return accountRepository.findAll();
    }
}