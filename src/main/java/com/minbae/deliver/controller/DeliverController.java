package com.minbae.deliver.controller;

import com.minbae.deliver.dto.DeliverSessionDto;
import com.minbae.deliver.repository.DeliverSessionRepository;
import com.minbae.deliver.service.DeliverService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpAttributesContextHolder;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.socket.messaging.SessionConnectEvent;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class DeliverController {

    private final DeliverSessionRepository deliverSessionRepository;
    private final DeliverService deliverService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/test/del")
    public String asdfasfd() {
        return "deliver/deliver_main";
    }

    @MessageMapping("/chat/test")
    public String handle(SimpMessageHeaderAccessor messageHeaderAccessor, String payload) {

        Map<String, Object> attributes = messageHeaderAccessor.getSessionAttributes();
        System.out.println(attributes);

        return payload;
    }

    //자신의 방 개설
    @MessageMapping("/chat/create")
    public void createRoom(@RequestParam("id") String id) {
        deliverSessionRepository.createChatRoomDTO(id);
    }

    //기사 latlng 변경
    @MessageMapping("/chat/refresh")
    public void refresh(@RequestParam("id") String id,@RequestParam("lat") Double lat,@RequestParam("lng") Double lng){
        System.out.println("refresh"+id);
        System.out.println("refresh"+lat);
        System.out.println("refresh"+lng);
        deliverSessionRepository.refresh(id,lat,lng);
        simpMessagingTemplate.convertAndSend("/deliver/room"+id,"{lat:"+lat+",lng:"+lng+"}");
    }

    //기사 찾아서 메세지 보내기
    @MessageMapping("/chat/assign")
    public void deliverAssign(double storeLat,double storeLng) {
        deliverService.assginDeliver(storeLat,storeLng);
    }
}
