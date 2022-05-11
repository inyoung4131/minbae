package com.minbae.storedetail.controller;

import com.minbae.menu.dto.MenuSaveRequestDto;
import com.minbae.menu.entity.Menu;
import com.minbae.storedetail.dto.StoreDetailSaveRequestDto;
import com.minbae.storedetail.dto.StoreDetailUpdateRequestDto;
import com.minbae.storedetail.entity.StoreDetail;
import com.minbae.storedetail.service.StoreDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RequiredArgsConstructor
@RestController
public class StoreDetailRestController {

    private final StoreDetailService storeDetailService;

    // 특정 가게 세부 정보 수정 -  아마 안쓰이는거 같은데 확실 ㄴ 나중에 지울게여
//    @PatchMapping("/api/storeDetailInfo/update/{storeDetailIdx}")
//    public ResponseEntity<StoreDetail> update(@PathVariable Long storeDetailIdx, @RequestBody StoreDetailUpdateRequestDto storeDetailUpdateRequestDto){
//        StoreDetail updatedStoreDetail = storeDetailService.update(storeDetailIdx, storeDetailUpdateRequestDto);
//        return (updatedStoreDetail != null) ?
//                ResponseEntity.status(HttpStatus.CREATED).body(updatedStoreDetail)
//                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }

    // 특정 가게 세부 정보 생성
    @PostMapping("/api/storeDetail/create")
    public ResponseEntity<StoreDetail> create(@RequestBody StoreDetailSaveRequestDto storeDetailSaveRequestDto){
        StoreDetail createStoreDetail = storeDetailService.create(storeDetailSaveRequestDto);
        return (createStoreDetail != null) ?
                ResponseEntity.status(HttpStatus.CREATED).body(createStoreDetail)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 특정가게 세부 정보 수정 + 이미지 저장
    @PatchMapping(value ="/api/storeDetailInfo/update/{storeDetailIdx}/img", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<StoreDetail> updateNew(@PathVariable Long storeDetailIdx,
                                                 @RequestPart(value = "key") StoreDetailUpdateRequestDto storeDetailUpdateRequestDto,
                                                 @RequestPart(value = "storeImg", required = false) MultipartFile file) throws Exception{

        StoreDetail updatedStoreDetail = storeDetailService.updateNew(storeDetailIdx, storeDetailUpdateRequestDto, file);
        return (updatedStoreDetail != null) ?
                ResponseEntity.status(HttpStatus.CREATED).body(updatedStoreDetail)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
