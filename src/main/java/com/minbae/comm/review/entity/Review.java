package com.minbae.comm.review.entity;

import com.minbae.store.entity.Store;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long review_idx;

    @ManyToOne
    @JoinColumn(name="store_idx",nullable = false)
    private Store store_idx;

    @Column
    private String review_img1;
    @Column
    private String review_img2;
    @Column
    private String review_img3;

    @Column(nullable = false)
    private String review_comment;
    @Column(nullable = false)
    private int review_star;
    @Column
    private String review_reply;
    @Column(nullable = false)
    private LocalDateTime review_write_date;

}
