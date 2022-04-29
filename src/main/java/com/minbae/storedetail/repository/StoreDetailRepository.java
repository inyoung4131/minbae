package com.minbae.storedetail.repository;

import com.minbae.storedetail.entity.StoreDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StoreDetailRepository extends JpaRepository<StoreDetail, Long> {

    // jpa 에러 - 미해결 - 네이티브 쿼리로 대체
    @Query(value = "select * from store_detail where store_idx = :storeIdx", nativeQuery = true)
    public StoreDetail findByStore(Long storeIdx); // 메소드명을 작명할 땐, 엔티티의 변수명을 따라야 한다. 테이블컬럼명이 아닌.

}
