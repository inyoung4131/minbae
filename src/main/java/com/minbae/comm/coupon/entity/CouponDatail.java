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
}
