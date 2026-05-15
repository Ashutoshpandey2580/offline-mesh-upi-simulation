package com.example.upimesh.dto;

public class AccountResponse {

    private String owner;

    private Double balance;

    public AccountResponse(
            String owner,
            Double balance
    ) {
        this.owner = owner;
        this.balance = balance;
    }

    public String getOwner() {
        return owner;
    }

    public Double getBalance() {
        return balance;
    }
}