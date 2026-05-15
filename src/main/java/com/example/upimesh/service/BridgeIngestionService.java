package com.example.upimesh.service;

import com.example.upimesh.crypto.CryptoService;
import com.example.upimesh.dto.IngestResponse;
import com.example.upimesh.entity.MeshPacket;
import com.example.upimesh.entity.PaymentInstruction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class BridgeIngestionService {

    private final CryptoService cryptoService;

    private final IdempotencyService idempotencyService;

    private final SettlementService settlementService;

    public IngestResponse ingest(
            MeshPacket packet) {
        System.out.println("INGEST CALLED");

        System.out.println("Saving transaction...");

        try {

            String hash =
                    cryptoService.sha256(
                            packet.getCiphertext());

            boolean claimed =
                    idempotencyService.claim(hash);

            if (!claimed) {

                return IngestResponse.builder()
                        .outcome("DUPLICATE_DROPPED")
                        .packetHash(hash)
                        .reason("Packet already processed")
                        .build();
            }

            PaymentInstruction instruction =
                    cryptoService.decrypt(
                            packet.getCiphertext());

            Duration age =
                    Duration.between(
                            instruction.getSignedAt(),
                            Instant.now());

            if (age.toHours() > 24) {

                return IngestResponse.builder()
                        .outcome("INVALID")
                        .packetHash(hash)
                        .reason("Packet expired")
                        .build();
            }

            settlementService.settle(
                    instruction.getSender(),
                    instruction.getReceiver(),
                    instruction.getAmount(),
                    hash
            );

            return IngestResponse.builder()
                    .outcome("SETTLED")
                    .packetHash(hash)
                    .reason(null)
                    .build();

        } catch (Exception e) {

            return IngestResponse.builder()
                    .outcome("INVALID")
                    .packetHash(null)
                    .reason(e.getMessage())
                    .build();
        }
    }
}