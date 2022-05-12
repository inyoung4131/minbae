package com.minbae.comm.tradehistory.dto;

import com.minbae.deliver.entity.Deliver;
import com.minbae.user.entity.User;
import java.time.LocalDateTime;

public class StoreTradeHistoryListDto {

    private Long tradeHistoryIdx;
    private String orderDate;
    private int orderPrice;
    private String orderPayment;
    private String userBasicAddr;
    private String userDetailAddr;
    private String deliverIdx;
    private String userTel;
    private int orderState;

}
