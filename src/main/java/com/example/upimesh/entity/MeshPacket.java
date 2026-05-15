package com.example.upimesh.entity;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeshPacket {

    private String packetId;

    private String ciphertext;

    private Integer ttl;

    private Long createdAt;
}
