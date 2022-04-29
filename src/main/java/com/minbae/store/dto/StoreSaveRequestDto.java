package com.minbae.store.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.minbae.comm.Role;
import com.minbae.owner.entity.Owner;
import com.minbae.store.entity.Store;
import com.minbae.storedetail.entity.StoreDetail;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
public class StoreSaveRequestDto {

    private Long storeIdx;
    private Owner ownerIdx;
    private String storeName;
    private String storeCategory;
    private String storeBasicAddr;
    private String storeDetailAddr;
    private Double storeLat;
    private Double storeLng;
    private String storeTel;

    @Builder
    public StoreSaveRequestDto(Owner ownerIdx,String storeName, String storeCategory, String storeBasicAddr, String storeDetailAddr, Double storeLat, Double storeLng,String storeTel){
        this.ownerIdx=ownerIdx;
        this.storeName=storeName;
        this.storeCategory=storeCategory;
        this.storeBasicAddr=storeBasicAddr;
        this.storeDetailAddr=storeDetailAddr;
        this.storeLat=storeLat;
        this.storeLng=storeLng;
        this.storeTel=storeTel;
    }

    public Store toEntity(){
        return Store.builder()
                .ownerIdx(ownerIdx)
                .storeName(storeName)
                .storeCategory(storeCategory)
                .storeBasicAddr(storeBasicAddr)
                .storeDetailAddr(storeDetailAddr)
                .storeLat(storeLat)
                .storeLng(storeLng)
                .storeTel(storeTel)
                .build();
    }
}





