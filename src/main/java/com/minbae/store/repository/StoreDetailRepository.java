package com.minbae.store.repository;

import com.minbae.store.entity.Store;
import com.minbae.store.entity.StoreDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreDetailRepository extends JpaRepository<StoreDetail, Long> {
}
