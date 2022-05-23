package com.minbae.comm.tradehistory.repository;

import com.minbae.comm.tradehistory.entity.TradeHistoryDetail;
import com.minbae.comm.tradehistory.entity.TradeHistoryDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TradeHistoryDetailRepository extends JpaRepository<TradeHistoryDetail, TradeHistoryDetailPK> {

    @Query(value = "select * from trade_history_detail where trade_history_idx=:tradeHistoryIdx",nativeQuery = true)
    List<TradeHistoryDetail> findByTradeHistoryIdx(Long tradeHistoryIdx);
}
