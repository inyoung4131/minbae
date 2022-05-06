package com.minbae.menu.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface MenuMapper {

    int deleteMenuImageOnly(Long menuIdx, String menuImg);
}
