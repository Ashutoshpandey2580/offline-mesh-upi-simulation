package com.example.upimesh.repository;

import com.example.upimesh.entity.TransactionEntity;
import jakarta.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public  interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {



}
