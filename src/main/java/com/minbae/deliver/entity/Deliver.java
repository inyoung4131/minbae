package com.minbae.deliver.entity;


import com.minbae.comm.tradehistory.entity.TradeHistory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Deliver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="deliver_idx")
    private long deliverIdx;

    @Column(nullable = false,unique = true,name="deliver_email")
    private String deliverEmail;

    @Column(nullable = false,name="deliver_pwd")
    private String deliverPwd;

    @Column(nullable = false,name="deliver_nickname")
    private String deliverNickname;

    @Column(nullable = false,name="deliver_work_state")
    private int deliverWorkState;

    @Column(name="deliver_money")
    private int deliverMoney;

    @Column(name="deliver_lng")

    private Double deliverLng;

    @Column(name="deliver_lat")
    private Double deliverLat;


}
