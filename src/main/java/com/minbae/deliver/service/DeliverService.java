package com.minbae.deliver.service;

import com.minbae.comm.stomp.store.StoreToDeliverMessage;
import com.minbae.comm.tradehistory.entity.TradeHistory;
import com.minbae.comm.tradehistory.repository.TradeHistoryRepository;
import com.minbae.deliver.dto.AssignDto;
import com.minbae.deliver.dto.AssignRequestDto;
import com.minbae.deliver.entity.Deliver;
import com.minbae.deliver.repository.DeliverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DeliverService {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final DeliverRepository deliverRepository;
    private final TradeHistoryRepository tradeHistoryRepository;

    public void assignComplete(Long deliverIdx,Long tradeIdx){
        Deliver deliver = deliverRepository.findByDeliverIdx(deliverIdx);
        deliver.setDeliverWorkState(1);
        deliverRepository.save(deliver);
        TradeHistory tradeHistory = tradeHistoryRepository.findByTradeHistoryIdx(tradeIdx);
        tradeHistory.setDeliverIdx(deliver);
        tradeHistory.setOrderState("2");
        tradeHistoryRepository.save(tradeHistory);
    }

    public void assginDeliver(StoreToDeliverMessage dto) {

        AssignRequestDto ARD = new AssignRequestDto();
        List<Deliver> list = deliverRepository.findAll();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDeliverWorkState() == 0) {
                double distanceMeter = distance( assignDto.getStoreLat(),assignDto.getStoreLng(),  list.get(i).getDeliverLat(),list.get(i).getDeliverLng(), "meter");
                if(distanceMeter<5000){
                    System.out.println(list.get(i).getDeliverIdx());
                    simpMessagingTemplate.convertAndSend("/topic/deliver/"+list.get(i).getDeliverIdx(), assignDto);
                }
            }

        }
    }

    private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == "kilometer") {
            dist = dist * 1.609344;
        } else if (unit == "meter") {
            dist = dist * 1609.344;
        }
        return (dist);
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

}
