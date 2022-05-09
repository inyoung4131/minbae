package com.minbae.review.dto;

import com.minbae.store.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StoreReviewDto {

    private Long reviewIdx;
    private Store storeIdx;
    private String reviewImg1;
    private String reviewImg2;
    private String reviewImg3;
    private String reviewComment;
    private int reviewStar;
    private String reviewReply;
    private LocalDateTime reviewWriteDate;
}
