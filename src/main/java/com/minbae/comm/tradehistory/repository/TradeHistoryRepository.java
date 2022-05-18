package com.minbae.comm.tradehistory.repository;

import com.minbae.comm.tradehistory.entity.TradeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeHistoryRepository extends JpaRepository<TradeHistory,Long> {

    TradeHistory findByTradeHistoryIdx(Long deliverIdx);
}
