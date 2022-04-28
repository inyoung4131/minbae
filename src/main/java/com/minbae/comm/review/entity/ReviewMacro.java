package com.minbae.comm.review.entity;


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
    private Long review_macro_idx;

    @ManyToOne
    @JoinColumn(name="store_idx")
    private Store store_idx;
    @Column
    private String review_macro_pre_review;
    @Column(unique = true)
    private int review_macro_review_num;
}
