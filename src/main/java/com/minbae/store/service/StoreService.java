package com.minbae.store.service;

import com.minbae.store.dto.StoreSaveRequestDto;
import com.minbae.store.dto.StoreUpdateRequestDto;
import com.minbae.store.entity.Store;
import com.minbae.store.repository.StoreRepository;
import com.minbae.storedetail.dto.StoreDetailSaveRequestDto;
import com.minbae.storedetail.entity.StoreDetail;
import com.minbae.storedetail.repository.StoreDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Service
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreDetailRepository storeDetailRepository;


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

    // 신규 가게 추가 - after - 2
    @Transactional
    public Store create2(StoreSaveRequestDto storeSaveRequestDto){
        Store newStore = storeRepository.save(storeSaveRequestDto.toEntity());
        if(newStore == null){
            throw new IllegalArgumentException("예외발생 - create store");
        };
        Long temp = newStore.getStoreIdx();
        StoreDetailSaveRequestDto target = new StoreDetailSaveRequestDto();
        target.setStoreIdx(newStore);
        StoreDetail newStoreDetail = storeDetailRepository.save(target.toEntity());

        if(newStoreDetail == null){
            throw new IllegalArgumentException("예외발생 - create storeDetail");
        };
        return newStore;
    }


    // 특정 가게 일반 정보 가져오기
    public Store storeInfo(Long storeIdx){
        Store byStoreIdx = storeRepository.findByStoreIdx(storeIdx);
        return byStoreIdx;
    }

    // 특정 가게 일반 정보 수정하기
    public Store update(Long storeIdx, StoreUpdateRequestDto storeUpdateRequestDto){
        // 요청 url과 dto의 불일치 검증
        if(storeIdx != storeUpdateRequestDto.getStoreIdx()){
            throw new IllegalArgumentException("잘못된 접근입니다.");
        }

        // 이전의 가게
        Store existStore = storeRepository.findById(storeIdx).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않은 가게 입니다.")
        );

        // dto -> 새로운 가게 entity
        Store target = storeUpdateRequestDto.toEntity();

        // 수정
        existStore.patch(target);

        // repository db save
        Store updated = storeRepository.save(existStore);
        return updated;
    }

    // 특정 사장님의 가게 삭제 요청
    public Store deleteStore(Long storeIdx) {
        Store deleteTarget = storeRepository.findById(storeIdx).orElse(null);
        if(deleteTarget == null){
            return null;
        }
        storeRepository.delete(deleteTarget);
        return deleteTarget;
    }



}
