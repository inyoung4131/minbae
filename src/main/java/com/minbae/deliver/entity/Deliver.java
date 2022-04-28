package com.minbae.deliver.entity;


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
    private long deliver_idx;

    @Column(nullable = false,unique = true)
    private String deliver_email;

    @Column(nullable = false)
    private String deliver_pwd;

    @Column(nullable = false)
    private String deliver_nickname;

    @Column(nullable = false)
    private int deliver_work_state;

    @Column()
    private int deliver_money;

    @Column()
    private int deliver_lng;

    @Column()
    private int deliver_lat;

    private long order_num;

}
