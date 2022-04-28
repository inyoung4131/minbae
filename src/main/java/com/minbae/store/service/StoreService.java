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

    // 가게 목록 조회
    public List<Store> index(Long id){
        return (List<Store>) storeRepository.findAllById(Collections.singleton(id));
    }

    @Transactional
    public long save(StoreSaveRequestDto storeSaveDto){
        return storeRepository.save(storeSaveDto.toEntity()).getStoreIdx();
    }

    @Transactional
    public Store create(StoreSaveRequestDto storeSaveRequestDto){
        return storeRepository.save(storeSaveRequestDto.toEntity());
    }


}
