package com.minbae.review.service;

import com.minbae.review.dao.ReviewMapper;
import com.minbae.review.dto.StoreReviewDto;
import com.minbae.review.dto.StoreReviewRequestDto;
import com.minbae.review.dto.StoreReviewUpdateDto;
import com.minbae.review.entity.Review;
import com.minbae.store.entity.Store;
import com.minbae.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class StoreReviewService {

    private final ReviewMapper reviewMapper;
    private final StoreRepository storeRepository;

    // 특정 가게의 전체리뷰 조회
    public List<Map<String, Object>> getReviewList(Long storeIdx, String allOrNoReply) {

        List<Map<String, Object>> reviewListByStoreIdx = new ArrayList<>();

        if(allOrNoReply == null || allOrNoReply.equals("all")){
            reviewListByStoreIdx = reviewMapper.findReviewListByStoreIdx(storeIdx);
        }else if(allOrNoReply.equals("noreply")){
            reviewListByStoreIdx = reviewMapper.findNoReplyReviewListByStoreIdx(storeIdx);
        }

        if(reviewListByStoreIdx == null){
            new IllegalArgumentException("존재하지 않는 가게 입니다.");
        }
        return reviewListByStoreIdx;
    }

    // 특정가게의 리뷰개수 조회
    public Map<String, Integer> getReviewCountNum(Long storeIdx){
        Map<String, Integer> map = new HashMap<>();
        map.put("allReviewCount", reviewMapper.countAllReviewByStoreIdx(storeIdx));
        map.put("notReplyReviewCount", reviewMapper.countNotReplyReviewByStoreIdx(storeIdx));
        return map;
    }
    // 특정가게의 리뷰개수 조회 - 조건 있는 경우
    public Map<String, Integer> getReviewCountNumByDate(Long storeIdx, String startDate, String endDate){
        Map<String, Integer> map = new HashMap<>();
        map.put("allReviewCount", reviewMapper.countAllReviewByStoreIdxAndStateAndDate(storeIdx, startDate, endDate));
        map.put("notReplyReviewCount", reviewMapper.countNotReplyReviewByStoreIdxAndStateAndDate(storeIdx, startDate, endDate));
        return map;
    }

    // 특정 가게의 사장님 리뷰 생성
    @Transactional
    public Integer addStoreReview(String storeIdx, StoreReviewRequestDto storeReviewRequestDto) {
        // 0. 요청 url 과 넘어온 dto의 storeIdx 일치 검증
        if(!storeIdx.equals(storeReviewRequestDto.getStoreIdx().getStoreIdx().toString())){
            throw new IllegalArgumentException("올바른 요청이 아닙니다.");
        }

        // 1. 존재하는 가게인지
        Store targetStore = storeRepository.findById(Long.valueOf(storeIdx)).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 가게입니다.")
        );

        // 2. 존재하는 리뷰인지
        StoreReviewDto targetReview = reviewMapper.findByReviewIdx(Long.valueOf(storeReviewRequestDto.getReviewIdx()));
        if(targetReview == null){
            throw new IllegalArgumentException("존재하지 않는 리뷰입니다.");
        }

        // 3. 사장님 답변을 기존리뷰에 추가업데이트
        Integer resultNum = reviewMapper.updateStoreReview(storeReviewRequestDto);
        return resultNum;
    }

    // 특정 가게의 사장님 답변 삭제
    public Integer deleteStoreReply(String reviewIdx) {
        // 존재하는 리뷰인지
        if(reviewMapper.findByReviewIdx(Long.valueOf(reviewIdx)) == null){
            throw new IllegalArgumentException("존재하지 않는 리뷰입니다.");
        }

        Integer resultNum = reviewMapper.deleteReplyByReviewIdx(Long.valueOf(reviewIdx));
        return resultNum;
    }

    public Integer updateStoreReply(StoreReviewUpdateDto updateDto) {

        Integer resultNum = reviewMapper.updateStoreReply(updateDto);
        return resultNum;

    }


    // 특정가게 리뷰조회 - 조건
    public List<Map<String, Object>> getReviewListNew(String selectedRadio, String storeIdxVal, String startDate, String endDate) {
        List<Map<String, Object>> reviewListByStoreIdx = new ArrayList<>();
        if(selectedRadio == null || selectedRadio.equals("all")){
            if(startDate == null || startDate.equals("null")){
                System.out.println("1번째 쿼리 실행");
                reviewListByStoreIdx = reviewMapper.findReviewListByStoreIdx(Long.valueOf(storeIdxVal));
            }else{
                System.out.println("2번째 쿼리 실행");
                reviewListByStoreIdx = reviewMapper.findReviewListByStoreIdxAndDate(Long.valueOf(storeIdxVal), startDate, endDate);
            }
        }else if(selectedRadio.equals("noreply")){
            if(startDate == null || startDate.equals("null")){
                System.out.println("3번째 쿼리 실행");
                reviewListByStoreIdx = reviewMapper.findNoReplyReviewListByStoreIdx(Long.valueOf(storeIdxVal));
            }else{
                System.out.println("4번째 쿼리 실행");
                reviewListByStoreIdx = reviewMapper.findNoReplyReviewListByStoreIdxAndDate(Long.valueOf(storeIdxVal), startDate, endDate);
            }
        }

        if(reviewListByStoreIdx == null){
            new IllegalArgumentException("해당 조건에 알맞는 리뷰가 없습니다.");
        }
        return reviewListByStoreIdx;
    }
}
