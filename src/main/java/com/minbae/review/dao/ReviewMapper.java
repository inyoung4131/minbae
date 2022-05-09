package com.minbae.review.dao;

import com.minbae.review.dto.StoreReviewDto;
import com.minbae.review.dto.StoreReviewRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReviewMapper {

    List<Map<String, Object>> findReviewListByStoreIdx(Long storeIdx);

    Integer updateStoreReview(StoreReviewRequestDto storeReviewRequestDto);

    // 리뷰식별번호로 리뷰 row 찾기
    StoreReviewDto findByReviewIdx(Long reviewIdx);

    // 특정 가게의 사장님 답변 삭제
    Integer deleteReplyByReviewIdx(Long reviewIdx);

    // 특정 가게의 전체 리뷰 개수
    Integer countAllReviewByStoreIdx(Long storeIdx);

    // 특정 가게의 미답변 리뷰 개수
    Integer countNotReplyReviewByStoreIdx(Long storeIdx);

}
