package com.minbae.store.repository;

import com.minbae.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface StoreRepository extends JpaRepository<Store,Long>{

    @Query(value = "SELECT * FROM store WHERE owner_idx = :ownerIdx", nativeQuery = true)
    public List<Store> findStoresByOwnerIdx(Long ownerIdx);

}
