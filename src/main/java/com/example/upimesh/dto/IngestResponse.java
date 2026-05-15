package com.example.upimesh.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class IngestResponse {

    private String outcome;

    private String packetHash;

    private String reason;


}
