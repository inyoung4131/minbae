package com.minbae.deliver.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class DeliverSessionDto {
    private Set<WebSocketSession> sessions = new HashSet<>();
    private String id;
    private double lat;
    private double lng;

    public static DeliverSessionDto create(String id){
        DeliverSessionDto DSD = new DeliverSessionDto();
        DSD.id=id;
        return DSD;
    }
}
