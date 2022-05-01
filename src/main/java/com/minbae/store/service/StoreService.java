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
        System.out.println("store_idx는 잘 가져와지니?"+temp);
        StoreDetailSaveRequestDto target = new StoreDetailSaveRequestDto();
        target.setStoreIdx(newStore);
        StoreDetail newStoreDetail = storeDetailRepository.save(target.toEntity());
        // 왜 아래 방법은 안통하지..
        //StoreDetail newStoreDetail = storeDetailRepository.saveByStoreIdx(temp);
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
        // dto -> entity
        Store target = storeUpdateRequestDto.toEntity();
        // 올바르지 않은 storeIdx 검증
        if(storeIdx != target.getStoreIdx()){return null;}
        // repository db
        Store updated = storeRepository.save(target);
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
