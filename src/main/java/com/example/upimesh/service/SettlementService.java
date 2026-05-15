package com.example.upimesh.service;


import com.example.upimesh.entity.Account;
import com.example.upimesh.entity.TransactionEntity;
import com.example.upimesh.repository.AccountRepository;
import com.example.upimesh.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class SettlementService {


    private  final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;


    @Transactional
    public void settle(
            String sender,
            String receiver,
            Double amount,
            String packetHash
    ){
        Account senderAcc=
                accountRepository.findByOwner(sender)
                        .orElseThrow();

        Account receiverAcc=accountRepository.findByOwner(receiver)
                .orElseThrow();

        if (senderAcc.getBalance()<amount){
            throw new RuntimeException("Insufficient balance");
        }
        senderAcc.setBalance(senderAcc.getBalance()-amount);

        receiverAcc.setBalance(receiverAcc.getBalance()+amount);

        TransactionEntity tx=new TransactionEntity();


        tx.setSender(sender);

        tx.setReceiver(receiver);

        tx.setAmount(amount);

        tx.setPacketHash(packetHash);

        tx.setCreated(Instant.now());
        tx.setStatus("SETTLED");

        transactionRepository.save(tx);

    }


    public void transfer(
            String sender,
            String receiver,
            Double amount
    ) {

        settle(
                sender,
                receiver,
                amount,
                "MESH_PACKET"
        );
    }
    }

