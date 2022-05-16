package com.minbae.menu.dao;

import com.minbae.menu.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MenuMapper {

    int deleteMenuImageOnly(Long menuIdx, String menuImg);

    Integer findHighestSunbunByStoreIDx(Long storeIdx);

    List<Menu> findAllByStoreIdxOrderSunbun(Long storeIdx);

    Integer updateSunbun(int sunbun, String menuIdx);
}
