package com.minbae.deliver.dto;

import com.minbae.deliver.entity.Deliver;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class DeliverSessionDto {
    private String id;
    private double lat;
    private double lng;
    private int state;

    public static DeliverSessionDto create(String id){
        DeliverSessionDto DSD = new DeliverSessionDto();
        DSD.id=id;
        DSD.state=0;
        return DSD;
    }


}
