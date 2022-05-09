package com.minbae.review.controller;

import com.minbae.review.dto.StoreReviewRequestDto;
import com.minbae.review.service.StoreReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class storeReviewRestController {

    private final StoreReviewService storeReviewService;

    // 특정 가게의 사장님 답변 생성
    @PatchMapping("/owne/store/{storeIdx}/addreply")
    public ResponseEntity<Map<String, Integer>> createStoreReview(@PathVariable String storeIdx, @RequestBody StoreReviewRequestDto storeReviewRequestDto){
        Integer resultNum = storeReviewService.addStoreReview(storeIdx, storeReviewRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("resultNum", resultNum));

    }

    // 특정 가게의 사장님 답변 삭제
    @DeleteMapping("/owne/delete/reply/{reviewIdx}")
    public Map<String, Integer> deleteStoreReply(@PathVariable String reviewIdx){
        Integer resultNum = storeReviewService.deleteStoreReply(reviewIdx);
        Map<String, Integer> map = new HashMap<>();
        map.put("resultNum", resultNum);
        return map;
    }

}