package com.minbae.comm.coupon.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coupon_idx;
    @Column(nullable = false)
    private String coupon_name;
    @Column(nullable = false)
    private int coupon_discount;
    @Column(nullable = false)
    private int coupon_usable_price;

}
