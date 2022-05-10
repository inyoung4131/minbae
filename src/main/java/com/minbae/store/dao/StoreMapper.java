package com.minbae.store.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StoreMapper {

    List<Map<String, Object>> findStandardList(@Param("page") int page, @Param("page2") int page2
            , @Param("category") String category, @Param("userlat") double user_lat
            , @Param("userlng") double user_lng);
}
