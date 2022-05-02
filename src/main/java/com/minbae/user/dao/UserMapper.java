package com.minbae.user.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    List<Map<String, Object>> findStoreByCategory(String category);
    List<Map<String, Object>> findStoreByNotInIdxs(List<Long> idxs, String category);

}
