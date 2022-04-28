package com.minbae.store.service;

import com.minbae.store.repository.StoreRepository;
import com.minbae.store.dto.StoreSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;

    @Transactional
    public long save(StoreSaveRequestDto storeSaveDto){
        return storeRepository.save(storeSaveDto.toEntity()).getStoreIdx();
    }
}
