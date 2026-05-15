package com.example.upimesh.mesh;

import com.example.upimesh.entity.MeshPacket;
import com.example.upimesh.service.BridgeIngestionService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MeshSimulatorService {

    private final List<VirtualDevice> devices =
            new ArrayList<>();

    private final BridgeIngestionService
            ingestionService;

    public MeshSimulatorService(
            BridgeIngestionService ingestionService
    ) {
        this.ingestionService = ingestionService;
    }

    public void flushBridges() {

        for (VirtualDevice device : devices) {

            if (!device.isHasInternet()) {
                continue;
            }

            for (MeshPacket packet :
                    device.getPackets()) {

                System.out.println("Uploading packet...");
                ingestionService.ingest(packet);
            }
        }
    }

    @PostConstruct
    public void init() {

        devices.add(
                new VirtualDevice(
                        "phone-alice",
                        false,
                        new ArrayList<>()
                )
        );

        devices.add(
                new VirtualDevice(
                        "phone-bob",
                        false,
                        new ArrayList<>()
                )
        );

        devices.add(
                new VirtualDevice(
                        "phone-carol",
                        false,
                        new ArrayList<>()
                )
        );

        devices.add(
                new VirtualDevice(
                        "phone-bridge",
                        true,
                        new ArrayList<>()
                )
        );
    }

    public List<VirtualDevice> getDevices() {

        return devices;
    }

    public void injectPacket(
            MeshPacket packet
    ) {

        devices.get(0)
                .getPackets()
                .add(packet);
    }

    private boolean alreadyExists(
            VirtualDevice device,
            MeshPacket packet
    ) {

        return device.getPackets()
                .stream()
                .anyMatch(p ->
                        p.getPacketId()
                                .equals(packet.getPacketId()));
    }

    public void gossipRound() {

        List<VirtualDevice> snapshot =
                new ArrayList<>(devices);

        for (VirtualDevice sender : snapshot) {

            List<MeshPacket> senderPackets =
                    new ArrayList<>(sender.getPackets());

            for (MeshPacket packet : senderPackets) {

                if (packet.getTtl() <= 0) {
                    continue;
                }

                for (VirtualDevice receiver : devices) {

                    // skip same device
                    if (sender == receiver) {
                        continue;
                    }

                    // skip duplicate packet
                    if (alreadyExists(receiver, packet)) {
                        continue;
                    }

                    MeshPacket forwarded =
                            MeshPacket.builder()
                                    .packetId(packet.getPacketId())
                                    .ciphertext(packet.getCiphertext())
                                    .ttl(packet.getTtl() - 1)
                                    .createdAt(packet.getCreatedAt())
                                    .build();

                    receiver.getPackets()
                            .add(forwarded);
                }
            }
        }
    }

    public void reset() {

        for (VirtualDevice device : devices) {

            device.getPackets().clear();
        }
    }
}