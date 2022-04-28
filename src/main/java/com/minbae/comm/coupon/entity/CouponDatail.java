package com.minbae.comm.coupon.entity;


import com.minbae.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class CouponDatail {

    @EmbeddedId
    private CouponDetailPK couponDetailPK;

    @Column(nullable = false)
    private int coupon_datail_state;
    @Column(nullable = false)
    private LocalDateTime coupon_datail_start_date;
    @Column(nullable = false)
    private LocalDateTime coupon_datail_end_date;
}
