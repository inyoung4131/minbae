package com.minbae.store.service;

import com.minbae.store.entity.Store;
import com.minbae.store.repository.StoreRepository;
import com.minbae.store.dto.StoreSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;


@RequiredArgsConstructor
@Service
public class StoreService {

    private final StoreRepository storeRepository;

    // 특정 사장님 가게 목록 조회
    public List<Store> index(Long ownerId){
        return (List<Store>) storeRepository.findStoresByOwnerIdx(ownerId);
    }

    // 신규 가게 추가 - before
    @Transactional
    public long save(StoreSaveRequestDto storeSaveDto){
        return storeRepository.save(storeSaveDto.toEntity()).getStoreIdx();
    }

    // 신규 가게 추가 - after
    @Transactional
    public Store create(StoreSaveRequestDto storeSaveRequestDto){
        return storeRepository.save(storeSaveRequestDto.toEntity());
    }

    // 특정 가게 일반 정보 가져오기
    public Store storeInfo(Long storeIdx){
        Store byStoreIdx = storeRepository.findByStoreIdx(storeIdx);
        return byStoreIdx;
    }


}
