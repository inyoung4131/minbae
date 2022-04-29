package com.minbae.storedetail.service;

import com.minbae.storedetail.entity.StoreDetail;
import com.minbae.storedetail.repository.StoreDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StoreDetailService {

    private final StoreDetailRepository storeDetailRepository;

    public StoreDetail storeDetailInfo(Long storeIdx) {
        StoreDetail allByStoreIdx = storeDetailRepository.findByStore(storeIdx);
        return allByStoreIdx;
    }

    // 특정 가게 세부 정보 반환
    //public StoreDetail storeDetailInfo(Long storeIdx){

    //}

}
