package com.example.upimesh.entity;


import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentInstruction {


    private String sender;

    private String receiver;

    private Double amount;

    private String nonce;

    private Instant signedAt;
}
