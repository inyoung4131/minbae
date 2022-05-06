package com.minbae.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserReviewDTO {

    private Long review_idx;
    private String review_comment;
    private String review_img1;
    private String review_img2;
    private String review_img3;
    private String review_reply;
    private String review_star;
    private Date review_write_date;
    private Long store_idx;

}
