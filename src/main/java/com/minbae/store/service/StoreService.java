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

    // 가게 목록 조회 - before
    public List<Store> index(Long ownerId){
        return (List<Store>) storeRepository.findStoresByOwnerIdx(ownerId);
    }

    @Transactional
    public long save(StoreSaveRequestDto storeSaveDto){
        return storeRepository.save(storeSaveDto.toEntity()).getStoreIdx();
    }

    // 신규 가게 추가
    @Transactional
    public Store create(StoreSaveRequestDto storeSaveRequestDto){
        return storeRepository.save(storeSaveRequestDto.toEntity());
    }

    // 가게 정보 가져오기
    public List<Store> stores(Long ownerIdx){
        // DB에서 조회 - 엔티티리스트 반환
        List<Store> allByOwnerIdx = storeRepository.findAllByOwnerIdx(ownerIdx);
        return allByOwnerIdx;
    }


}
