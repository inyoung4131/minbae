package com.minbae.review.entity;


import com.minbae.store.entity.Store;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ReviewMacro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_macro_idx")
    private Long reviewMacroIdx;

    @ManyToOne
    @JoinColumn(name="store_idx")
    private Store storeIdx;
    @Column(name="review_macro_pre_review")
    private String reviewMacroPreReview;
    @Column(unique = true,name="review_macro_review_num")
    private int reviewMacroReviewNum;
}
