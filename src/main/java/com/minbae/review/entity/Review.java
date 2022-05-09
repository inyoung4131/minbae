package com.minbae.review.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.minbae.comm.tradehistory.entity.TradeHistory;
import com.minbae.store.entity.Store;
import com.minbae.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Review {
    @Id
    @JsonBackReference
    @OneToOne
    @Column(name="review_idx")
    private TradeHistory tradeHistory;

    @ManyToOne
    @JoinColumn(name="store_idx",nullable = false)
    private Store storeIdx;

    @ManyToOne
    @JoinColumn(name="user_idx",nullable = false)
    private User userIdx;

    @Column(name="review_img1")
    private String reviewImg1;
    @Column(name="review_img2")
    private String reviewImg2;
    @Column(name="review_img3")
    private String reviewImg3;
    @Column(nullable = false,name="review_comment")
    private String reviewComment;
    @Column(nullable = false,name="review_star")
    private int reviewStar;
    @Column(name="review_reply")
    private String reviewReply;
    @Column(nullable = false,name="review_write_date")
    private LocalDateTime reviewWriteDate;

}
