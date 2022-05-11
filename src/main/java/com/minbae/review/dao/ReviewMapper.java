package com.minbae.review.dao;

import com.minbae.review.dto.StoreReviewDto;
import com.minbae.review.dto.StoreReviewRequestDto;
import com.minbae.review.dto.StoreReviewUpdateDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReviewMapper {

    Integer updateStoreReview(StoreReviewRequestDto storeReviewRequestDto);

    // 리뷰식별번호로 리뷰 row 찾기
    StoreReviewDto findByReviewIdx(Long reviewIdx);

    // 특정 가게의 사장님 답변 삭제
    Integer deleteReplyByReviewIdx(Long reviewIdx);

    // 특정 가게의 전체 리뷰 개수
    Integer countAllReviewByStoreIdx(Long storeIdx);

    // 특정 가게의 미답변 리뷰 개수
    Integer countNotReplyReviewByStoreIdx(Long storeIdx);

    //특정 가게 사장님 답변 수정
    Integer updateStoreReply(StoreReviewUpdateDto updateDto);

    // 전체 리뷰 조회
    List<Map<String, Object>> findReviewListByStoreIdx(Long storeIdx);
    // 미답변 리뷰 조회
    List<Map<String, Object>> findNoReplyReviewListByStoreIdx(Long storeIdx);
    // 전체 리뷰 조회 - 날짜 조건
    List<Map<String, Object>> findReviewListByStoreIdxAndDate(Long storeIdxVal, String startDate, String endDate);
    // 미답변 리뷰 조회 - 날짜 조건
    List<Map<String, Object>> findNoReplyReviewListByStoreIdxAndDate(Long storeIdxVal, String startDate, String endDate);
}
