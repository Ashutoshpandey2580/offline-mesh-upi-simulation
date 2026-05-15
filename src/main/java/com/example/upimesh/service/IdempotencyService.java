package com.example.upimesh.service;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class IdempotencyService {

    private final Map<String, Instant> seen =
            new ConcurrentHashMap<>();

    public boolean claim(String packetHash) {

        Instant now = Instant.now();

        Instant existing =
                seen.putIfAbsent(packetHash, now);

        return existing == null;
    }

    public void clear() {
        seen.clear();
    }
}