package com.minbae.review.dto;

import com.minbae.store.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StoreReviewRequestDto {

    private Long reviewIdx;
    private Store storeIdx;
    private String reviewReply;

}
