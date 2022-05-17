package com.minbae.deliver.repository;

import com.minbae.deliver.dto.DeliverSessionDto;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class DeliverSessionRepository {

    private Map<String, DeliverSessionDto> chatRoomDTOMap;

    @PostConstruct
    private void init(){
        chatRoomDTOMap = new LinkedHashMap<>();
    }

    public List<DeliverSessionDto> findAllRooms(){
        List<DeliverSessionDto> result = new ArrayList<>(chatRoomDTOMap.values());
        Collections.reverse(result);

        return result;
    }

    public DeliverSessionDto findRoomById(String id){
        return chatRoomDTOMap.get(id);
    }

    public void refresh(String id,double lat,double lng){
        DeliverSessionDto dto = findRoomById(id);
        dto.setLat(lat);
        dto.setLng(lng);
        chatRoomDTOMap.put(id,dto);
    }

    public DeliverSessionDto createChatRoomDTO(String id){
        DeliverSessionDto sessionDto = DeliverSessionDto.create(id);
        chatRoomDTOMap.put(sessionDto.getId(), sessionDto);

        return sessionDto;
    }
}