package com.example.upimesh.controller;

import com.example.upimesh.entity.TransactionEntity;
import com.example.upimesh.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TransactionController {

    private final TransactionRepository transactionRepository;

    @GetMapping
    public List<TransactionEntity> getTransactions(){

        return transactionRepository.findAll();
    }
    @DeleteMapping("/clear")
    public String clear(){

        transactionRepository.deleteAll();

        return "Transactions cleared";
    }
}