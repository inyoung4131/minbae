package com.minbae.deliver.service;

import com.minbae.comm.stomp.store.StoreToDeliverMessage;
import com.minbae.comm.stomp.store.UserToStoreMessage;
import com.minbae.comm.tradehistory.entity.TradeHistory;
import com.minbae.comm.tradehistory.repository.TradeHistoryRepository;
import com.minbae.deliver.dto.AssignRequestDto;
import com.minbae.deliver.entity.Deliver;
import com.minbae.deliver.repository.DeliverRepository;
import com.minbae.store.entity.Store;
import com.minbae.store.repository.StoreRepository;
import com.minbae.user.entity.User;
import com.minbae.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.core.ApplicationContext;
import org.apache.catalina.core.ApplicationSessionCookieConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class DeliverService {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final DeliverRepository deliverRepository;
    private final TradeHistoryRepository tradeHistoryRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    public Map<String,List<Long>> map = new HashMap<>();
    private final ServletConfig servletConfig;


    public void pickup(Long storeIdx, Long tradeIdx) {

        TradeHistory tradeHistory = tradeHistoryRepository.findByTradeHistoryIdx(tradeIdx);
        Deliver deliver = deliverRepository.findByDeliverIdx(tradeHistory.getDeliverIdx().getDeliverIdx());
        Store store = storeRepository.findByStoreIdx(tradeHistory.getStoreIdx().getStoreIdx());
        User user = userRepository.findByUserIdx(tradeHistory.getUserIdx().getUserIdx());
        deliver.setDeliverWorkState(2);
        deliverRepository.save(deliver);
        UserToStoreMessage message = new UserToStoreMessage();
        message.setU_trade_history_idx("보냈음");
        AssignRequestDto ARD = new AssignRequestDto();
        ARD.setMessage("픽업완료");
        ARD.setStoreIdx(user.getUserIdx());
        ARD.setStoreBasicAddr(store.getStoreBasicAddr());
        ARD.setStoreLat(store.getStoreLat());
        ARD.setStoreLng(store.getStoreLng());
        ARD.setStoreName(store.getStoreName());
        ARD.setTradeHistoryIdx(tradeHistory.getTradeHistoryIdx());
        ARD.setUserIdx(user.getUserIdx());
        ARD.setUserBasicAddr(user.getUserBasicAddr());
        simpMessagingTemplate.convertAndSend("/topic/store/" + tradeHistory.getStoreIdx().getStoreIdx(), message);
        simpMessagingTemplate.convertAndSend("/topic/deliver/" + tradeHistory.getDeliverIdx().getDeliverIdx(), ARD);
    }

    public void deliveryComplete(StoreToDeliverMessage dto) {
        TradeHistory tradeHistory = tradeHistoryRepository.findByTradeHistoryIdx(Long.parseLong(dto.getTradeHistoryIdx()));
        tradeHistory.setOrderState("3");
        tradeHistoryRepository.save(tradeHistory);
        Deliver deliver = deliverRepository.findByDeliverIdx(tradeHistory.getDeliverIdx().getDeliverIdx());
        AssignRequestDto ARD = new AssignRequestDto();
        ARD.setMessage("배달완료");
        deliver.setDeliverWorkState(0);
        UserToStoreMessage message = new UserToStoreMessage();
        message.setU_trade_history_idx("보냈음");
        deliverRepository.save(deliver);
        simpMessagingTemplate.convertAndSend("/topic/deliver/" + tradeHistory.getDeliverIdx().getDeliverIdx(), ARD);
        simpMessagingTemplate.convertAndSend("/topic/user/" + tradeHistory.getUserIdx().getUserIdx(), message);
        simpMessagingTemplate.convertAndSend("/topic/store/" + tradeHistory.getStoreIdx().getStoreIdx(), message);
    }

    public void assignComplete(Long deliverIdx, Long tradeIdx) {
        ServletContext servletContext = servletConfig.getServletContext();
        map=(Map<String, List<Long>>) servletContext.getAttribute("map");
        List<Long> list = map.get(Long.toString(tradeIdx));
        Deliver deliver = deliverRepository.findByDeliverIdx(deliverIdx);
        TradeHistory tradeHistory = tradeHistoryRepository.findByTradeHistoryIdx(tradeIdx);
        Store store = storeRepository.findByStoreIdx(tradeHistory.getStoreIdx().getStoreIdx());
        User user = userRepository.findByUserIdx(tradeHistory.getUserIdx().getUserIdx());
        AssignRequestDto ARD = new AssignRequestDto();
        ARD.setMessage("배정성공");
        ARD.setStoreIdx(user.getUserIdx());
        ARD.setStoreBasicAddr(store.getStoreBasicAddr());
        ARD.setStoreLat(store.getStoreLat());
        ARD.setStoreLng(store.getStoreLng());
        ARD.setStoreName(store.getStoreName());
        ARD.setTradeHistoryIdx(tradeHistory.getTradeHistoryIdx());
        ARD.setUserIdx(user.getUserIdx());
        ARD.setUserBasicAddr(user.getUserBasicAddr());
        if (deliver.getDeliverWorkState() == 0) {
            deliver.setDeliverWorkState(1);
        } else {
            ARD.setMessage("배정있음");
        }
        deliverRepository.save(deliver);
        tradeHistory.setDeliverIdx(deliver);
        tradeHistory.setOrderState("2");
        tradeHistoryRepository.save(tradeHistory);

        UserToStoreMessage message = new UserToStoreMessage();
        message.setU_trade_history_idx("보냈음");
        simpMessagingTemplate.convertAndSend("/topic/deliver/" + tradeHistory.getDeliverIdx().getDeliverIdx(), ARD);

        for(int i=0 ; i<list.size();i++) {
            ARD.setMessage("배정완료");
            simpMessagingTemplate.convertAndSend("/topic/deliver/" + list.get(i), ARD);
        }
        map.remove(Long.toString(tradeIdx));
        servletContext.removeAttribute(Long.toString(tradeIdx));
        simpMessagingTemplate.convertAndSend("/topic/user/" + tradeHistory.getUserIdx().getUserIdx(), message);
    }

    public void assginDeliver(StoreToDeliverMessage dto) {
        ServletContext servletContext = servletConfig.getServletContext();
        List<Long> dellist = new ArrayList<>();
        TradeHistory tradeHistory = tradeHistoryRepository.findByTradeHistoryIdx(Long.parseLong(dto.getTradeHistoryIdx()));
        Store store = storeRepository.findByStoreIdx(Long.valueOf(dto.getStoreIdx()));
        User user = userRepository.findByUserIdx(tradeHistory.getUserIdx().getUserIdx());
        AssignRequestDto ARD = new AssignRequestDto();
        ARD.setMessage("배달요청");
        ARD.setStoreIdx(user.getUserIdx());
        ARD.setStoreBasicAddr(store.getStoreBasicAddr());
        ARD.setStoreLat(store.getStoreLat());
        ARD.setStoreLng(store.getStoreLng());
        ARD.setStoreName(store.getStoreName());
        ARD.setTradeHistoryIdx(tradeHistory.getTradeHistoryIdx());
        ARD.setUserIdx(user.getUserIdx());
        ARD.setUserBasicAddr(user.getUserBasicAddr());
        List<Deliver> list = deliverRepository.findAll();
        System.out.println("2"+list.get(0).getDeliverIdx());
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDeliverWorkState() == 0) {
                double distanceMeter = distance(store.getStoreLat(), store.getStoreLng(), list.get(i).getDeliverLat(), list.get(i).getDeliverLng(), "meter");
                System.out.println("1"+list.get(0).getDeliverIdx());
                if (distanceMeter < 2500) {
                    System.out.println("3"+list.get(0).getDeliverIdx());
                    dellist.add(list.get(i).getDeliverIdx());
                    simpMessagingTemplate.convertAndSend("/topic/deliver/" + list.get(i).getDeliverIdx(), ARD);
                }
            }
        }
        map.put(dto.getTradeHistoryIdx(),dellist);
        servletContext.setAttribute("map",map);
        map=(Map<String, List<Long>>) servletContext.getAttribute("map");
        List<Long> list2 = map.get(dto.getTradeHistoryIdx());
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
