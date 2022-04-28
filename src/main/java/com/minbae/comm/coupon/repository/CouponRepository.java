package com.minbae.comm.coupon.repository;

import com.minbae.comm.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon,Long> {
}
