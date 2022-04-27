package com.minbae.store.dto;

import com.minbae.comm.Role;
import com.minbae.store.Entity.Store;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
public class StoreSaveRequestDto {

    private Long owner_idx;
    private String store_name;
    private String store_category;
    private String store_basic_addr;
    private String store_detail_addr;
    private Double store_lat;
    private Double store_lng;
    private String store_tel;

    @Builder
    public StoreSaveRequestDto(long owner_idx,String store_name, String store_category, String store_basic_addr, String store_detail_addr, Double store_lat, Double store_lng,String store_tel){
        this.owner_idx=owner_idx;
        this.store_name=store_name;
        this.store_category=store_category;
        this.store_basic_addr=store_basic_addr;
        this.store_detail_addr=store_detail_addr;
        this.store_lat=store_lat;
        this.store_lng=store_lng;
        this.store_tel=store_tel;
    }

    public Store toEntity(){
        return Store.builder()
                .owner_idx(owner_idx)
                .store_name(store_name)
                .store_category(store_category)
                .store_basic_addr(store_basic_addr)
                .store_detail_addr(store_detail_addr)
                .store_lat(store_lat)
                .store_lng(store_lng)
                .store_tel(store_tel)
                .build();
    }
}
