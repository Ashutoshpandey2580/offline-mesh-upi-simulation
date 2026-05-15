package com.example.upimesh.controller;

import com.example.upimesh.crypto.CryptoService;
import com.example.upimesh.entity.MeshPacket;
import com.example.upimesh.entity.PaymentInstruction;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api/demo")
@RequiredArgsConstructor
public class DemoController {

    private final CryptoService cryptoService;

    @PostMapping("/create-packet")
    public MeshPacket createPacket()
            throws Exception {

        PaymentInstruction instruction =
                PaymentInstruction.builder()
                        .sender("alice")
                        .receiver("bob")
                        .amount(500.0)
                        .nonce(UUID.randomUUID().toString())
                        .signedAt(Instant.now())
                        .build();

        String encrypted =
                cryptoService.encrypt(instruction);

        return MeshPacket.builder()
                .packetId(UUID.randomUUID().toString())
                .ciphertext(encrypted)
                .ttl(5)
                .createdAt(
                        System.currentTimeMillis())
                .build();
    }
    @PostMapping("/decrypt")
    public PaymentInstruction decrypt(
            @RequestBody MeshPacket packet)
            throws Exception {

        return cryptoService.decrypt(
                packet.getCiphertext());
    }
    @PostMapping("/tamper")
    public String tamper(
            @RequestBody MeshPacket packet) {

        String ciphertext =
                packet.getCiphertext();

        ciphertext =
                ciphertext.substring(0,
                        ciphertext.length() - 2)
                        + "ab";

        packet.setCiphertext(ciphertext);

        return packet.getCiphertext();
    }
}