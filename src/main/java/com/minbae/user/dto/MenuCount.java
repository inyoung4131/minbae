package com.minbae.user.dto;

import lombok.Data;
import lombok.Getter;

@Getter
public class MenuCount {
    private Long menuIdx;
    private Integer count;

    public MenuCount(Long menuIdx,Integer count){
        this.menuIdx=menuIdx;
        this.count=count;
    }
}
