package com.minbae.deliver.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Deliver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long deliver_idx;

    private String deliver_email;

    private String deliver_pwd;

    private String deliver_nickname;

    private int deliver_work_state;

    private int deliver_money;

    private int deliver_lng;

    private int deliver_lat;

    private long order_num;

}
