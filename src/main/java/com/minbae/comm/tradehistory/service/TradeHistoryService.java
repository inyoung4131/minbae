package com.minbae.comm.tradehistory.service;

import com.minbae.comm.stomp.store.StoreToUserMessage;
import com.minbae.comm.tradehistory.dao.TradeHistoryMapper;
import com.minbae.comm.tradehistory.dto.StoreTradeHistoryListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class TradeHistoryService {

    private final TradeHistoryMapper tradeHistoryMapper;

    public List<StoreTradeHistoryListDto> getStoreTradeList(String storeIdx) {
        List<StoreTradeHistoryListDto> tradeList = tradeHistoryMapper.findTradeListByStoreIdx(storeIdx);
        return tradeList;
    }

    public void changeOrderState(StoreToUserMessage message) {
        System.out.println("TradeHistoryService에 들어온 message(수락/거절)>>"+message.getMessage());
        if(message.getMessage().equals("수락")){
            tradeHistoryMapper.updateOrderState(message.getTradehistoryidx(), 1);
        }else{
            tradeHistoryMapper.updateOrderState(message.getTradehistoryidx(), 4);
        }
    }
}
