package com.minbae.storedetail.controller;

import com.minbae.storedetail.dto.StoreDetailSaveRequestDto;
import com.minbae.storedetail.dto.StoreDetailUpdateRequestDto;
import com.minbae.storedetail.entity.StoreDetail;
import com.minbae.storedetail.service.StoreDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class StoreDetailRestController {

    private final StoreDetailService storeDetailService;

    // 특정 가게 세부 정보 수정
    @PatchMapping("/api/storeDetailInfo/update/{storeDetailIdx}")
    public ResponseEntity<StoreDetail> update(@PathVariable Long storeDetailIdx, @RequestBody StoreDetailUpdateRequestDto storeDetailUpdateRequestDto){
        StoreDetail updatedStoreDetail = storeDetailService.update(storeDetailIdx, storeDetailUpdateRequestDto);
        return (updatedStoreDetail != null) ?
                ResponseEntity.status(HttpStatus.CREATED).body(updatedStoreDetail)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 특정 가게 세부 정보 생성
    @PostMapping("/api/storeDetail/create")
    public ResponseEntity<StoreDetail> create(@RequestBody StoreDetailSaveRequestDto storeDetailSaveRequestDto){
        StoreDetail createStoreDetail = storeDetailService.create(storeDetailSaveRequestDto);
        return (createStoreDetail != null) ?
                ResponseEntity.status(HttpStatus.CREATED).body(createStoreDetail)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
