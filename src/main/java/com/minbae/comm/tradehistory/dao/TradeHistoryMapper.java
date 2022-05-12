package com.minbae.comm.tradehistory.dao;

import com.minbae.comm.tradehistory.dto.StoreTradeHistoryListDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TradeHistoryMapper {

    List<StoreTradeHistoryListDto> findTradeListByStoreIdx(String storeIdx);


}
