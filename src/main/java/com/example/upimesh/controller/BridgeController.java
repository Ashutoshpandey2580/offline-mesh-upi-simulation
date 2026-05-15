package com.example.upimesh.controller;


import com.example.upimesh.dto.IngestResponse;
import com.example.upimesh.entity.MeshPacket;
import com.example.upimesh.service.BridgeIngestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bridge")
@RequiredArgsConstructor
public class BridgeController {

    private final BridgeIngestionService ingestionService;

    @PostMapping("/ingest")
    public IngestResponse ingest(
            @RequestBody MeshPacket packet
            ){

        return ingestionService.ingest(packet);
    }
}
