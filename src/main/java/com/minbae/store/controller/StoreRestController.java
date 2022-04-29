package com.minbae.store.controller;

import com.minbae.store.entity.Store;
import com.minbae.store.service.StoreService;
import com.minbae.store.dto.StoreSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class StoreRestController {

    private final StoreService storeService;

    // 특정 사장님의 가게 목록 조회
    @GetMapping("/api/stores/{id}")
    public List<Store> index(@PathVariable Long id){
        return storeService.index(id);
    }

    // 신규 가게 저장 - before
    @PostMapping("/api/v1/store")
    public long save(@RequestBody() StoreSaveRequestDto storeSaveDto,String role){
        return storeService.save(storeSaveDto);
    }

    // 신규 가게 저장 - after
    @PostMapping("/api/store/create")
    public ResponseEntity<Store> create(@RequestBody StoreSaveRequestDto storeSaveRequestDto){
        Store createStore = storeService.create(storeSaveRequestDto);
        return (createStore != null) ?
                ResponseEntity.status(HttpStatus.CREATED).body(createStore)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
