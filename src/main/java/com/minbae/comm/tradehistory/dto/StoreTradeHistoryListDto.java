package com.minbae.comm.tradehistory.dto;

import com.minbae.comm.tradehistory.entity.TradeHistory;
import com.minbae.deliver.entity.Deliver;
import com.minbae.user.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StoreTradeHistoryListDto {

    private Long tradeHistoryIdx;
    private Deliver deliverIdx;
    private String orderState;

}
