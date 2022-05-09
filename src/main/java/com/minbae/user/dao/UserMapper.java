package com.minbae.user.dao;

import com.minbae.user.dto.UserReviewDTO;
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

    List<Map<String, Object>> findByMenuList(Long store_idx);

    Map<String, Object> findStoreByMenuIdx(Long menu_idx);

    int reviewCreate(UserReviewDTO dto);

    List<Map<String, Object>> reviewList(Long user_idx);

    Map<String, Object> reviewCnt(Long user_idx);

    int reviewDel(String review_idx);
}
