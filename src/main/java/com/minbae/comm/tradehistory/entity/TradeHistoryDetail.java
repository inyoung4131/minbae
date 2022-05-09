package com.minbae.comm.tradehistory.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class TradeHistoryDetail {

    @EmbeddedId
    private TradeHistoryDetailPK orderDetailPK;

    @Column(nullable = false)
    private int count;

}
