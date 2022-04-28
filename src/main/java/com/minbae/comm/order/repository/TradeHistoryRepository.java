package com.minbae.comm.order.repository;

import com.minbae.comm.order.entity.TradeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeHistoryRepository extends JpaRepository<TradeHistory,Long> {
}
