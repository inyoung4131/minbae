package com.minbae.store.dto;

import com.minbae.store.Entity.Store;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StoreSaveRequestDto {
    private String storeName;
    private String storeCategory;
    private String addrRoad;
    private String addrDetail;
    private Double lat;
    private Double lng;

    @Builder
    public StoreSaveRequestDto(String storeName, String storeCategory, String addrRoad, String addrDetail, Double lat, Double lng){
        this.storeName=storeName;
        this.storeCategory=storeCategory;
        this.addrRoad=addrRoad;
        this.addrDetail=addrDetail;
        this.lat=lat;
        this.lng=lng;
    }

    public Store toEntity(){
        return Store.builder()
                .storeName(storeName)
                .storeCategory(storeCategory)
                .addrRoad(addrRoad)
                .addrDetail(addrDetail)
                .lat(lat)
                .lng(lng)
                .build();
    }
}
