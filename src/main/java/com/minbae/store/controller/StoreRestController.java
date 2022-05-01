package com.minbae.store.controller;

import com.minbae.store.dto.StoreSaveRequestDto;
import com.minbae.store.dto.StoreUpdateRequestDto;
import com.minbae.store.entity.Store;
import com.minbae.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class StoreRestController {

    private final StoreService storeService;

    // 신규 가게 저장 - before
    @PostMapping("/api/v1/store")
    public long save(@RequestBody() StoreSaveRequestDto storeSaveDto, String role){
        return storeService.save(storeSaveDto);
    }

    // 신규 가게 저장 - after
    @PostMapping("/api/store/create")
    public ResponseEntity<Store> create(@RequestBody StoreSaveRequestDto storeSaveRequestDto){
        Store createStore = storeService.create2(storeSaveRequestDto);
        return (createStore != null) ?
                ResponseEntity.status(HttpStatus.CREATED).body(createStore)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 특정 사장님의 가게 정보 수정
    @PatchMapping("/api/storeInfo/update/{storeIdx}")
    public ResponseEntity<Store> update(@PathVariable Long storeIdx, @RequestBody StoreUpdateRequestDto storeUpdateRequestDto){
        Store updatedStore = storeService.update(storeIdx, storeUpdateRequestDto);
        return (updatedStore != null) ?
                ResponseEntity.status(HttpStatus.CREATED).body(updatedStore)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 특정 사장님의 가게 삭제 요청
    @DeleteMapping("/api/store/delete/{storeIdx}")
    public ResponseEntity<Store> delete(@PathVariable Long storeIdx){
        Store delete = storeService.deleteStore(storeIdx);
        return (delete != null) ?
                ResponseEntity.status(HttpStatus.OK).build():
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }



}
