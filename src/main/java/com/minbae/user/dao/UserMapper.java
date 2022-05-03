package com.minbae.user.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    List<Map<String, Object>> findStoreByCategoryOrder(String category);

    List<Map<String, Object>> findStoreByNotInIdxs(List<Long> idxs, String category);

    List<Map<String, Object>> findStoreByCategoryStar(String categoryEn);

    Map<String, Object> findStoreById(Long store_idx);

    Map<String, Object> findReviewByCount(Long store_idx);

    List<Map<String, Object>> findReviewBykingMenu(Long store_idx);

}
