package com.example.upimesh.mesh;

import com.example.upimesh.entity.MeshPacket;

import java.util.ArrayList;
import java.util.List;

public class VirtualDevice {

    private String deviceId;

    private boolean hasInternet;

    private List<MeshPacket> packets =
            new ArrayList<>();

    public VirtualDevice() {
    }

    public VirtualDevice(
            String deviceId,
            boolean hasInternet,
            List<MeshPacket> packets) {

        this.deviceId = deviceId;
        this.hasInternet = hasInternet;
        this.packets = packets;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public boolean isHasInternet() {
        return hasInternet;
    }

    public void setHasInternet(boolean hasInternet) {
        this.hasInternet = hasInternet;
    }

    public List<MeshPacket> getPackets() {
        return packets;
    }

    public void setPackets(List<MeshPacket> packets) {
        this.packets = packets;
    }
}