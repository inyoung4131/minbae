package com.minbae.user.dto;

import lombok.Getter;

public class UserResponseStoreListDto {

    @Getter
    private long store_idx;
    private String store_name;
    private int store_detail_info_img;
    private String store_detail_minimum_price;
    private int review_star;
    private int review_count;
}
