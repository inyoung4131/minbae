package com.minbae.user.dto;

import com.minbae.menu.entity.Menu;
import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class CurrentTradeHistoryForUserDto {
    private Long userIdx;

    private Long tradeHistoryIdx;

    private String userBasicAddr;

    private String orderState;
    private int deliverWorkState;
    private Long deliverIdx;
    private Long storeIdx;

    private String storeName;
    private Double storeLat;
    private Double storeLng;

    private String storeDetailInfoImg;
    private String storeBasicAddr;

    private Map<Integer, Menu> menu;
    private Map<Integer, MenuCount> menuCount;

    public CurrentTradeHistoryForUserDto(Long userIdx,String userBasicAddr,String orderState, int deliverWorkState
    ,Long deliverIdx,Long storeIdx,String storeName,String storeDetailInfoImg,String storeBasicAddr,Map<Integer, Menu> menu
    ,Map<Integer, MenuCount> menuCount,Long tradeHistoryIdx,Double storeLat,Double storeLng){
        this.userIdx=userIdx;
        this.userBasicAddr=userBasicAddr;
        this.orderState=orderState;
        this.deliverWorkState=deliverWorkState;
        this.deliverIdx=deliverIdx;
        this.storeIdx=storeIdx;
        this.storeName=storeName;
        this.storeDetailInfoImg=storeDetailInfoImg;
        this.storeBasicAddr=storeBasicAddr;
        this.menu=menu;
        this.menuCount=menuCount;
        this.tradeHistoryIdx=tradeHistoryIdx;
        this.storeLat=storeLat;
        this.storeLng=storeLng;

    }

}
