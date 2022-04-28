package com.minbae.comm.coupon.repository;

import com.minbae.comm.coupon.entity.CouponDatail;
import com.minbae.comm.coupon.entity.CouponDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponDetailRepository extends JpaRepository<CouponDatail, CouponDetailPK> {
}
