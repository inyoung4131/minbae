package com.minbae.comm.coupon.entity;


import com.minbae.user.entity.User;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class CouponDetailPK implements Serializable {


    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name="coupon_idx",nullable = false)
    private Coupon coupon_idx;

    @ManyToOne
    @JoinColumn(name="user_idx",nullable = false)
    private User user_idx;

    public CouponDetailPK() {
    }

    public CouponDetailPK(Coupon coupon_idx, User user_idx) {
        super();
        this.user_idx = user_idx;
        this.coupon_idx = coupon_idx;
    }
}
