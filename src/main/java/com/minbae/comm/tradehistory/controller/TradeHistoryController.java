package com.minbae.comm.tradehistory.controller;

import com.minbae.comm.tradehistory.dto.StoreTradeHistoryListDto;
import com.minbae.comm.tradehistory.service.TradeHistoryService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/owner/store/tradeHistory/{storeIdx}")
    public String goStoreTradeHistoryPage(@PathVariable String storeIdx, Model model){
        List<StoreTradeHistoryListDto> tradeList = tradeHistoryService.getStoreTradeList(storeIdx);
        model.addAttribute("tradeList", tradeList);
        return  "/tradehistory/store_trade_history_list";
    }


}
