package com.minbae.menu.repository;

import com.minbae.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu,Long> {

    @Query(value = "SELECT * FROM menu WHERE store_idx = :storeIdx", nativeQuery = true)
    List<Menu> findAllByStoreIdx(Long storeIdx);

    @Query(value = "SELECT * FROM menu WHERE store_idx = :storeIdx ORDER BY menu_king_menu desc, menu_sunbun ASC", nativeQuery = true)
    List<Menu> findAllByStoreIdxOrderByMenuSunbunAsc(Long storeIdx);
}
