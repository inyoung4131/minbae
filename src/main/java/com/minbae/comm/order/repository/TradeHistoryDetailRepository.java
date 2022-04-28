package com.minbae.comm.order.repository;

import com.minbae.comm.order.entity.TradeHistoryDetail;
import com.minbae.comm.order.entity.TradeHistoryDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeHistoryDetailRepository extends JpaRepository<TradeHistoryDetail, TradeHistoryDetailPK> {
}
