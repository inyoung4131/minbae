package com.minbae.storedetail.service;

import com.minbae.storedetail.dto.StoreDetailSaveRequestDto;
import com.minbae.storedetail.dto.StoreDetailUpdateRequestDto;
import com.minbae.storedetail.entity.StoreDetail;
import com.minbae.storedetail.repository.StoreDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StoreDetailService {

    private final StoreDetailRepository storeDetailRepository;

    // 특정 가게 세부 정보 반환
    public StoreDetail storeDetailInfo(Long storeIdx) {
        StoreDetail allByStoreIdx = storeDetailRepository.findByStore(storeIdx);
        return allByStoreIdx;
    }

    // 특정 가게 세부 정보 수정
    public StoreDetail update(Long storeDetailIdx, StoreDetailUpdateRequestDto storeDetailUpdateRequestDto) {
        // dto -> entity
        StoreDetail target = storeDetailUpdateRequestDto.toEntity();
        // 올바르지 않은 storeIdx 검증
        if(storeDetailIdx != target.getStoreDetailIdx()){return null;}
        // repository db
        StoreDetail updated = storeDetailRepository.save(target);
        return updated;
    }

    // 특정 가게 세부 정보 생성
    @Transactional
    public StoreDetail create(StoreDetailSaveRequestDto storeDetailSaveRequestDto){
        return storeDetailRepository.save(storeDetailSaveRequestDto.toEntity());
    }


}
