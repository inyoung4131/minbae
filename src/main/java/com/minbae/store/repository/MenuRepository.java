package com.minbae.store.repository;

import com.minbae.store.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu,Long> {
}
