package com.minbae.deliver.controller;

import com.minbae.comm.stomp.store.StoreToDeliverMessage;
import com.minbae.deliver.dto.RefreshDto;
import com.minbae.deliver.entity.Deliver;
import com.minbae.deliver.repository.DeliverRepository;
import com.minbae.deliver.service.DeliverService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class DeliverController {

    private final DeliverRepository deliverRepository;
    private final DeliverService deliverService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/deliver/page/main")
    public String asdfasfd() {
        return "deliver_main2";
    }

    //기사 latlng 변경
    @MessageMapping("/chat/refresh")
    public void refresh(@Payload RefreshDto refreshDto){
        Deliver deliver= deliverRepository.findByDeliverIdx(refreshDto.getId());
        deliver.setDeliverLng(refreshDto.getLng());
        deliver.setDeliverLat(refreshDto.getLat());
        deliverRepository.save(deliver);
        simpMessagingTemplate.convertAndSend("/topic/deliver/"+refreshDto.getId(),"{lat:"+refreshDto.getLat()+",lng:"+refreshDto.getLng()+"}");
    }

    //기사 찾아서 메세지 보내기
    @MessageMapping("/chat/assign")
    public void deliverAssign(@Payload StoreToDeliverMessage dto) {
        deliverService.assginDeliver(dto);
    }


    @GetMapping("/loginform/deliver")
    public String loginform(){
        return "deliver/deliver_login";
    }

    @MessageMapping("/chat/complete")
    public void complete(@Payload StoreToDeliverMessage dto){
        deliverService.deliveryComplete(dto);
    }
    @MessageMapping("/chat/pickup")
    public void pickup(@Payload StoreToDeliverMessage dto){
        deliverService.pickup(Long.parseLong(dto.getStoreIdx()),Long.parseLong(dto.getTradeHistoryIdx()));
    }
}
