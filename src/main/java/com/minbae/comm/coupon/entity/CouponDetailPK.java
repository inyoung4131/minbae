package com.minbae.comm.coupon.entity;


import com.minbae.user.entity.User;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class CouponDetailPK implements Serializable {


    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name="coupon_idx",nullable = false)
    private Coupon couponIdx;

    @ManyToOne
    @JoinColumn(name="user_idx",nullable = false)
    private User userIdx;

    public CouponDetailPK() {
    }

    public CouponDetailPK(Coupon couponIdx, User userIdx) {
        super();
        this.userIdx = userIdx;
        this.couponIdx = couponIdx;
    }
}
