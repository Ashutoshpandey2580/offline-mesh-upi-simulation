package com.example.upimesh.controller;

import com.example.upimesh.dto.MeshStateResponse;
import com.example.upimesh.entity.MeshPacket;
import com.example.upimesh.mesh.MeshSimulatorService;
import com.example.upimesh.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mesh")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MeshController {

    private final MeshSimulatorService meshService;

    private final TransactionRepository transactionRepository;

    @GetMapping("/state")
    public MeshStateResponse getState() {

        return new MeshStateResponse(
                meshService.getDevices(),
                transactionRepository.findAll()
        );
    }

    @PostMapping("/inject")
    public String inject(
            @RequestBody MeshPacket packet
    ) {

        meshService.injectPacket(packet);

        return "Packet injected";
    }

    @PostMapping("/gossip")
    public String gossip() {

        meshService.gossipRound();

        return "Gossip completed";
    }

    @PostMapping("/flush")
    public String flush() {

        meshService.flushBridges();

        return "Bridge flush completed";
    }

    @PostMapping("/reset")
    public String reset() {

        meshService.reset();

        return "Mesh reset";
    }
}