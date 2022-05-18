package com.minbae.review.controller;

import com.minbae.review.dto.StoreReviewRequestDto;
import com.minbae.review.dto.StoreReviewUpdateDto;
import com.minbae.review.service.StoreReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class storeReviewRestController {

    private final StoreReviewService storeReviewService;

    // 특정 가게의 사장님 답변 생성
    @PatchMapping("/owner/store/{storeIdx}/addreply")
    public ResponseEntity<Map<String, Integer>> createStoreReview(@PathVariable String storeIdx, @RequestBody StoreReviewRequestDto storeReviewRequestDto){
        Integer resultNum = storeReviewService.addStoreReview(storeIdx, storeReviewRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("resultNum", resultNum));

    }

    // 특정 가게의 사장님 답변 삭제
    @DeleteMapping("/owner/delete/reply/{reviewIdx}")
    public Map<String, Integer> deleteStoreReply(@PathVariable String reviewIdx){
        Integer resultNum = storeReviewService.deleteStoreReply(reviewIdx);
        Map<String, Integer> map = new HashMap<>();
        map.put("resultNum", resultNum);
        return map;
    }

    //특정 가게의 사장님 답변 수정
    @PatchMapping("/owner/patch/reply")
    public Map<String, Integer> updateStoreReply(@RequestBody StoreReviewUpdateDto updateDto) {
        Integer resultNum = storeReviewService.updateStoreReply(updateDto);
        Map<String, Integer> map = new HashMap<>();
        map.put("resultNum", resultNum);
        return map;
    }



}
