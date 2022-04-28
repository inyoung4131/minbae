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

    @Column(nullable = false,name="coupon_datail_state")
    private int couponDatailState;
    @Column(nullable = false,name="coupon_datail_start_date")
    private LocalDateTime couponDatailStartDate;
    @Column(nullable = false,name="coupon_datail_end_date")
    private LocalDateTime couponDatailEndDate;
}
