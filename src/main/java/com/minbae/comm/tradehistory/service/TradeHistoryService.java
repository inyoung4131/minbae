package com.minbae.comm.tradehistory.service;

import com.minbae.comm.stomp.store.StoreToUserMessage;
import com.minbae.comm.tradehistory.dao.TradeHistoryMapper;
import com.minbae.comm.tradehistory.dto.StoreTradeHistoryListDto;
import com.minbae.comm.tradehistory.repository.TradeHistoryRepository;
import com.minbae.store.entity.Store;
import com.minbae.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class TradeHistoryService {

    private final TradeHistoryMapper tradeHistoryMapper;
    private final TradeHistoryRepository tradeHistoryRepository;
    private final StoreRepository storeRepository;

    public List<StoreTradeHistoryListDto> getStoreTradeList(String storeIdx) {
        List<StoreTradeHistoryListDto> tradeList = tradeHistoryMapper.findTradeListByStoreIdx(storeIdx);
        return tradeList;
    }

    public void changeOrderState(StoreToUserMessage message) {
        Integer resultNum = 0;
        if(message.getMessage().equals("수락")){
            resultNum = tradeHistoryMapper.updateOrderState(message.getTradehistoryidx(), 1);
        }else{
            resultNum = tradeHistoryMapper.updateOrderState(message.getTradehistoryidx(), 4);
        }

        if(resultNum < 1){
            throw new IllegalArgumentException("TradeHistoryService.java order_state 변경 실패!");
        }

    }

    public List<Map<String, Object>> getStoreWeekMoneyData(String storeIdx) {
        LocalDateTime now = LocalDateTime.now();
        String today = now.plusDays(1).format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
        String oneMonthBefore = now.minusDays(13).format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
        List<Map<String, Object>> resultList = tradeHistoryMapper.selectOrderPrice(storeIdx, today, oneMonthBefore);
        //System.out.println("리스트에 담긴 값>>"+resultList.get(0));
        return resultList;
    }

    public List<Map<String, Object>> getStoreWeekMoneyData2(String storeIdx, String xSize) {
        int target = Integer.parseInt(xSize)-1;
        LocalDateTime now = LocalDateTime.now();
        String today = now.plusDays(1).format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
        String oneMonthBefore = now.minusDays(target).format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
        List<Map<String, Object>> resultList = tradeHistoryMapper.selectOrderPrice(storeIdx, today, oneMonthBefore);
        //System.out.println("리스트에 담긴 값>>"+resultList.get(0));
        return resultList;
    }



    // 0518 21:00
    public List<StoreTradeHistoryListDto> getTradeHistoryInfiniteList(String storeIdx, String page, String size) {
        Integer integerPage = Integer.valueOf(page);
        integerPage = ((integerPage-1)*10);
        Integer integerSize = Integer.valueOf(size);
        return tradeHistoryMapper.findAllTradeHistoryListDtoInfinity(storeIdx, integerSize, integerPage);
    }
}
