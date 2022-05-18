package com.minbae.comm.tradehistory.controller;

import com.minbae.comm.tradehistory.service.TradeHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class TradeHistoryRestController {

    private final TradeHistoryService tradeHistoryService;

    @GetMapping("/owner/storeMoney/{storeIdx}")
    public ResponseEntity<List<Map<String, Object>>> getStoreMoneyData(@PathVariable String storeIdx){
        List<Map<String, Object>> resultList = tradeHistoryService.getStoreWeekMoneyData(storeIdx);
        return ResponseEntity.status(HttpStatus.OK).body(resultList);
    }

}
