package com.minbae.user.dao;

import com.minbae.user.dto.UserReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    List<Map<String, Object>> findStoreByCategoryOrder(String category);

    List<Map<String, Object>> findStoreByOrderNotInIdxs(List<Long> idxs, String category);

    List<Map<String, Object>> findStoreByStarNotInIdxs(List<Long> idxs, String category);

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

    List<Map<String, Object>> orderHistory(Long user_idx);

    Map<String, Object> reviewState(Long trade_history_idx);


    List<Map<String, Object>> storeDetailReview(Long store_idx);

    Map<String, Object> get_user_info(Long user_idx);

    int trade_history_deliver_insert(Map<String, Object> map);

    int trade_history_take_out_insert(Map<String, Object> map);

    Integer get_last_trade_history_idx();

    int trade_history_detail_insert(String count, String menu_idx, Integer trade_history_idx);

    String minimum_price(Long store_idx);

    List<Map<String, String>> get_order_store(Integer user_idx);

    Map<String, Object> kakaoLogin(Map<String, Object> param);
    int insertKakaoUser(Map<String, Object> param);

    Map<String, Integer> get_store_lication(Integer store_idx);

    int tel_update(String user_tel, Long user_idx);
}
