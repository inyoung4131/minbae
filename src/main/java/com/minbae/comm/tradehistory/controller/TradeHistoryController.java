package com.minbae.comm.tradehistory.controller;

import com.minbae.comm.tradehistory.dto.StoreTradeHistoryListDto;
import com.minbae.comm.tradehistory.service.TradeHistoryService;
import com.minbae.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class TradeHistoryController {

    private final TradeHistoryService tradeHistoryService;
    private final StoreService storeService;

    @GetMapping("/owner/store/tradeHistory/{storeIdx}")
    public String goStoreTradeHistoryPage(@PathVariable String storeIdx, Model model){
        List<StoreTradeHistoryListDto> tradeList = tradeHistoryService.getStoreTradeList(storeIdx);
        model.addAttribute("tradeList", tradeList);
        return  "tradehistory/store_trade_history_list";
    }

    @GetMapping("/owner/store/tradeHistory/infinity/{storeIdx}")
    public String goStoreTradeHistoryInfinityPage(@PathVariable String storeIdx, Model model){
        model.addAttribute("storeName", storeService.storeInfo(Long.valueOf(storeIdx)).getStoreName());
        return  "tradehistory/store_trade_history_list_infinity";
    }




}
