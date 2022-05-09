package com.minbae.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StoreReviewUpdateDto {

    private Long review_idx;
    private String review_reply;

}
