package com.minbae.deliver.controller;

import com.minbae.deliver.dto.DeliverSessionDto;
import com.minbae.deliver.repository.DeliverSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpAttributesContextHolder;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
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
        System.out.println(SimpAttributesContextHolder.currentAttributes().getSessionId());
        deliverSessionRepository.createChatRoomDTO(id);
    }


    @MessageMapping("/chat/assign")
    public void deliverAssign(@RequestParam("id") String id) {
        System.out.println(SimpAttributesContextHolder.currentAttributes().getSessionId());
    }

}
