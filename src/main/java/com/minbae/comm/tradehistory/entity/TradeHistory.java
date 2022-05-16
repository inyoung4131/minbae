package com.minbae.comm.tradehistory.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.minbae.deliver.entity.Deliver;
import com.minbae.review.entity.Review;
import com.minbae.store.entity.Store;
import com.minbae.storedetail.entity.StoreDetail;
import com.minbae.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class TradeHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="trade_history_idx")
    private Long tradeHistoryIdx;

    @ManyToOne
    @JoinColumn(name="store_idx",nullable = false)
    private Store storeIdx;

    @ManyToOne
    @JoinColumn(name="user_idx",nullable = false)
    private User userIdx;

    @ManyToOne
    @JoinColumn(name="deliver_idx")
    private Deliver deliverIdx;

    @Column(nullable = false,name="order_price")
    private int orderPrice;

    @Column(nullable = false,name="order_deliver_price")
    private int orderDeliverPrice;

    @ColumnDefault("0")
    @Column(nullable = false,name="order_state")
    private String orderState;

    @Column(nullable = false,name="order_payment")
    private String orderPayment;
    @Column(nullable = false,name="order_date")
    private LocalDateTime orderDate;

    @JsonManagedReference
    @OneToOne(mappedBy = "tradeHistory", orphanRemoval = true)
    private Review review;

}
