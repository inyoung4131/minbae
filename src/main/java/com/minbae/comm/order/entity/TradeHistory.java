package com.minbae.comm.order.entity;

import com.minbae.deliver.entity.Deliver;
import com.minbae.store.entity.Store;
import com.minbae.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class TradeHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_idx;

    @ManyToOne
    @JoinColumn(name="store_idx",nullable = false)
    private Store store_idx;

    @ManyToOne
    @JoinColumn(name="user_idx",nullable = false)
    private User user_idx;

    @ManyToOne
    @JoinColumn(name="deliver_idx")
    private Deliver deliver_idx;

    @Column(nullable = false)
    private int order_price;

    @Column(nullable = false)
    private int order_deliver_price;

    @Column(nullable = false)
    private String order_state;

    @Column(nullable = false)
    private String order_payment;

}
