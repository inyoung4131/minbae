package com.minbae.user.dto;

import lombok.*;


@Data
public class UserResponseStoreListDto {

    private long store_idx;
    private String store_name;
    private int store_detail_minimum_price;
    private String store_detail_info_img;
    private int cou;
    private float avger_star;


    public UserResponseStoreListDto(long store_idx,String store_name,int store_detail_minimum_price,String store_detail_info_img){
        this.store_idx=store_idx;
        this.store_name=store_name;
        this.store_detail_minimum_price=store_detail_minimum_price;
        this.store_detail_info_img=store_detail_info_img;
    }
}
