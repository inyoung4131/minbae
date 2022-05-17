package com.minbae.deliver.service;

import com.minbae.deliver.dto.DeliverSessionDto;
import com.minbae.deliver.repository.DeliverSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.converter.SimpleMessageConverter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DeliverService {
    private final DeliverSessionRepository deliverSessionRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public void assginDeliver(double storeLat, double storeLng) {
        List<DeliverSessionDto> list = deliverSessionRepository.findAllRooms();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getState() == 0) {
                double distanceMeter = distance(storeLng, storeLat, list.get(i).getLng(), list.get(i).getLat(), "meter");
                if(distanceMeter<1500){
                    simpMessagingTemplate.convertAndSend("/deliver/room"+list.get(i).getId(),"asdasd");
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
