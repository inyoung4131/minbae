package com.minbae.storedetail.repository;

import com.minbae.storedetail.entity.StoreDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StoreDetailRepository extends JpaRepository<StoreDetail, Long> {

    // jpa 에러 - 미해결 - 네이티브 쿼리로 대체
    @Query(value = "select * from store_detail where store_idx = :storeIdx", nativeQuery = true)
    public StoreDetail findByStore(Long storeIdx); // 메소드명을 작명할 땐, 엔티티의 변수명을 따라야 한다. 테이블컬럼명이 아닌.

    // 왜 values에 값이 전달이 안될까?
    @Query(value = "INSERT INTO store_detail(store_idx) VALUES (:temp)", nativeQuery = true)
    public StoreDetail saveByStoreIdx(@Param("temp")Long temp);

    @Query(value = "SELECT store_detail_info_img from store_detail where store_detail_idx = :storeDetailIdx", nativeQuery = true)
    public String findImgNameById(Long storeDetailIdx);
}
