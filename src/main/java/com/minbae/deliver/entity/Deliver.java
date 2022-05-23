package com.minbae.deliver.entity;


import com.minbae.comm.tradehistory.entity.TradeHistory;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Deliver {

    @Setter
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

    @Setter
    @Column(nullable = false,name="deliver_work_state")
    private int deliverWorkState;

    @Column(name="deliver_money")
    private int deliverMoney;

    @Setter
    @Column(name="deliver_lng")
    private Double deliverLng;

    @Setter
    @Column(name="deliver_lat")
    private Double deliverLat;

    @Builder
    public Deliver(Long deliverIdx,String deliverEmail,String deliverPwd,String deliverNickname,int deliverWorkState,int deliverMoney,Double deliverLat
    ,Double deliverLng){
        this.deliverIdx=deliverIdx;
        this.deliverEmail=deliverEmail;
        this.deliverPwd=deliverPwd;
        this.deliverNickname=deliverNickname;
        this.deliverWorkState=deliverWorkState;
        this.deliverMoney=deliverMoney;
        this.deliverLat=deliverLat;
        this.deliverLng=deliverLng;
    }

}
