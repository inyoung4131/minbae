package com.minbae.store.repository;

import com.minbae.store.store.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface StoreRepository extends JpaRepository<StoreEntity,Long>{

}
