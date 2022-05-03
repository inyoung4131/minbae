package com.minbae.user.service;

import com.minbae.user.dao.UserMapper;
import com.minbae.user.exception.UserCommException;
import com.minbae.user.exception.comm.UserExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserMapper userMapper;

    /**주문 많은 순 리스트*/
    public List<Map<String, Object>> getStoreByCategoryOrderAndStar(String category, String type) {

        //주문순이나 별점순 리스트를 담을 놈
        List<Map<String, Object>> orderAndStarCntList = new ArrayList<>();
        //리스트로 나온 놈들의 store idx들을 담을 놈
        List<Long> storeIdxs = new ArrayList<>();

        if(type.equals("order")) {
            //주문 많은 순
            orderAndStarCntList = userMapper.findStoreByCategoryOrder(category);
        }
        else if (type.equals("star")) {
            //별점 높은 순
            orderAndStarCntList = userMapper.findStoreByCategoryStar(category);
        }else{
            throw new UserCommException(UserExceptionType.NOT_EXIST_TYPE);
        }

        //orderAndStarCntList에 담긴 가게 idx들을 따로 담음
        orderAndStarCntList.forEach(data -> storeIdxs.add((Long) data.get("store_idx")));

        //orderAndStarCntList에 담긴 가게 idx들을 제외한 결제 내역, 리뷰 테이블에 없는 가게들 list 출력
        List<Map<String, Object>> storeByNotInIdxs = userMapper.findStoreByNotInIdxs(storeIdxs, category);

        //orderAndStarCntList와 StoreByNotInIdxs를 합침
        List<Map<String, Object>> finalOrderAndStarCntList = Stream.concat(orderAndStarCntList.stream(), storeByNotInIdxs.stream())
                .collect(Collectors.toList());

        return finalOrderAndStarCntList;
    }

    //특정 가게 상세 정보
    public Map<String, Object> findStoreById(Long store_idx) {
        return userMapper.findStoreById(store_idx);
    }

    //특정 가게의 리뷰 사용자, 사장님 개수
    public Map<String, Object> findReviewByCount(Long store_idx) {
        return userMapper.findReviewByCount(store_idx);
    }

    //특정 가게 대표 메뉴 리스트
    public List<Map<String, Object>> findReviewBykingMenu(Long store_idx) {
        return userMapper.findReviewBykingMenu(store_idx);
    }



}
