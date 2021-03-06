package com.minbae.comm.tradehistory.entity;

import com.minbae.store.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class TradeHistoryDetailPK implements Serializable {
    private static final long serialVersionUID = 1L;
    @ManyToOne
    @JoinColumn(name="order_idx",nullable = false)
    private TradeHistory order_idx;

    @ManyToOne
    @JoinColumn(name="menu_idx",nullable = false)
    private Menu menu_idx;


}
