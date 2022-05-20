package com.minbae.comm.tradehistory.controller;

import com.minbae.comm.tradehistory.dto.StoreTradeHistoryListDto;
import com.minbae.comm.tradehistory.entity.TradeHistory;
import com.minbae.comm.tradehistory.service.TradeHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class TradeHistoryRestController {

    private final TradeHistoryService tradeHistoryService;
// 테스트
//    @GetMapping("/owner/storeMoney/{storeIdx}")
//    public ResponseEntity<List<Map<String, Object>>> getStoreMoneyData(@PathVariable String storeIdx){
//        List<Map<String, Object>> resultList = tradeHistoryService.getStoreWeekMoneyData(storeIdx);
//        return ResponseEntity.status(HttpStatus.OK).body(resultList);
//    }

    @GetMapping("/owner/storeMoney/{storeIdx}/{xSize}")
    public ResponseEntity<List<Map<String, Object>>> getStoreMoneyData(@PathVariable String storeIdx, @PathVariable String xSize){
        List<Map<String, Object>> resultList = tradeHistoryService.getStoreWeekMoneyData2(storeIdx, xSize);
        return ResponseEntity.status(HttpStatus.OK).body(resultList);
    }

    // 0518 21:00
    @GetMapping("/owner/tradeHistoryList/infinite/{storeIdx}")
    public ResponseEntity<List<StoreTradeHistoryListDto>> getTradeHistory(@PathVariable String storeIdx, @RequestParam("page") String page, @RequestParam("size") String size){
        List<StoreTradeHistoryListDto> resultList = tradeHistoryService.getTradeHistoryInfiniteList(storeIdx, page, size);

        return (resultList != null) ? ResponseEntity.status(HttpStatus.OK).body(resultList)
                                    : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }


}
