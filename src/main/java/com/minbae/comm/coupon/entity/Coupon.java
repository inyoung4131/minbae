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
    @Column(name="coupon_idx")
    private Long couponIdx;
    @Column(nullable = false,name="coupon_name")
    private String couponName;
    @Column(nullable = false,name="coupon_discount")
    private int couponDiscount;
    @Column(nullable = false,name="coupon_usable_price")
    private int couponUsablePrice;

}
