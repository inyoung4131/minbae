package com.minbae.comm.tradehistory.repository;

import com.minbae.comm.tradehistory.entity.TradeHistoryDetail;
import com.minbae.comm.tradehistory.entity.TradeHistoryDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeHistoryDetailRepository extends JpaRepository<TradeHistoryDetail, TradeHistoryDetailPK> {
}
