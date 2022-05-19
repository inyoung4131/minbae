package com.minbae.comm.tradehistory.repository;

import com.minbae.comm.tradehistory.dto.StoreTradeHistoryListDto;
import com.minbae.comm.tradehistory.entity.TradeHistory;
import com.minbae.store.entity.Store;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeHistoryRepository extends JpaRepository<TradeHistory,Long> {

    TradeHistory findByTradeHistoryIdx(Long deliverIdx);

}
