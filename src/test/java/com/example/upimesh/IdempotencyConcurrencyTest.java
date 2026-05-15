package com.example.upimesh;

import com.example.upimesh.controller.DemoController;
import com.example.upimesh.dto.IngestResponse;
import com.example.upimesh.entity.MeshPacket;
import com.example.upimesh.service.BridgeIngestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class IdempotencyConcurrencyTest {

    @Autowired
    private BridgeIngestionService ingestionService;

    @Autowired
    private DemoController demoController;

    @Test
    void duplicatePacketSettlesOnlyOnce()
            throws Exception {

        MeshPacket packet =
                demoController.createPacket();

        ExecutorService executor =
                Executors.newFixedThreadPool(3);

        List<Future<IngestResponse>> futures =
                new ArrayList<>();

        for(int i=0;i<3;i++){

            futures.add(
                    executor.submit(() ->
                            ingestionService.ingest(packet))
            );
        }

        int settled = 0;

        int duplicates = 0;

        for(Future<IngestResponse> future : futures){

            IngestResponse response =
                    future.get();

            if(response.getOutcome()
                    .equals("SETTLED")){

                settled++;
            }

            if(response.getOutcome()
                    .equals("DUPLICATE_DROPPED")){

                duplicates++;
            }
        }

        assertEquals(1, settled);

        assertEquals(2, duplicates);
    }
}