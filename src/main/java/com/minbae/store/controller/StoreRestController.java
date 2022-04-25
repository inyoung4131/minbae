package com.minbae.store.controller;

import com.minbae.store.service.StoreService;
import com.minbae.store.dto.StoreSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StoreRestController {

    private final StoreService storeService;

    @PostMapping("/api/v1/store")
    public long save(@RequestBody() StoreSaveRequestDto storeSaveDto){
        return storeService.save(storeSaveDto);
    }
}
