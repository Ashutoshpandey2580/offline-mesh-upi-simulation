package com.example.upimesh.dto;

import com.example.upimesh.entity.TransactionEntity;
import com.example.upimesh.mesh.VirtualDevice;

import java.util.List;

public class MeshStateResponse {

    private List<VirtualDevice> devices;

    private List<TransactionEntity> transactions;

    public MeshStateResponse(
            List<VirtualDevice> devices,
            List<TransactionEntity> transactions
    ) {
        this.devices = devices;
        this.transactions = transactions;
    }

    public List<VirtualDevice> getDevices() {
        return devices;
    }

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }
}