package com.minbae.user.service;

import com.minbae.review.dao.ReviewMapper;
import com.minbae.review.dto.ReviewCountAndAvgStar;
import com.minbae.store.dao.StoreMapper;
import com.minbae.store.entity.Store;
import com.minbae.store.repository.StoreRepository;
import com.minbae.user.comm.UserApiStatus;
import com.minbae.user.dao.UserMapper;
import com.minbae.user.dto.UserAddrChangeDto;
import com.minbae.user.dto.UserJoinDto;
import com.minbae.user.dto.UserResponseStoreListDto;
import com.minbae.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class YangsUserService {

    private final StoreMapper storeMapper;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final ReviewMapper reviewMapper;

    public boolean join(UserJoinDto userJoinDto){
        return userRepository.save(userJoinDto.toEntity()).getUserEmail()==null?false:true;
    }

    public UserApiStatus userEmailCheck(String userEmail){
        return userRepository.findByUserEmail(userEmail)==null?UserApiStatus.SUCCESS:UserApiStatus.FAIL;
    }

    public long userAddrChange(UserAddrChangeDto userAddrChangeDto){
        userRepository.findByUserIdx(userAddrChangeDto.toEntity());
        return userRepository.save(userAddrChangeDto.toEntity()).getUserIdx();
    }

    //기본순 출력
    public List<Map<String, Object>> getStoreByCategoryAndStandard(int page, String category,double user_lat,double user_lng){
        page = page*20;
        int page2=page+19;
        List<Map<String, Object>> list = storeMapper.findStandardList(page,page2,category,user_lat,user_lng);
        if(list!=null&&list.size()!=0)
        for(int i=0;i<list.size();i++) {
            ReviewCountAndAvgStar reviewCountAndAvgStar = reviewMapper.getReviewCountAndStarAvg((Long)list.get(i).get("store_idx"));
            if(reviewCountAndAvgStar!=null) {
                list.get(i).put("avger_star", reviewCountAndAvgStar.getAvger_star());
                list.get(i).put("cou", reviewCountAndAvgStar.getCou());
            }
        }
        return list;
    }

    public Store getStoreinfo(long storeIdx){
        return storeRepository.findByStoreIdx(storeIdx);
    }

}
