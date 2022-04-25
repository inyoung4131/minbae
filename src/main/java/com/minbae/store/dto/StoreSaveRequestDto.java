package com.minbae.store.dto;

import com.minbae.store.store.StoreEntity;
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
    private long lat;
    private long lng;

    @Builder
    public StoreSaveRequestDto(String storeName, String storeCategory, String addrRoad, String addrDetail, long lat, long lng){
        this.storeName=storeName;
        this.storeCategory=storeCategory;
        this.addrRoad=addrRoad;
        this.addrDetail=addrDetail;
        this.lat=lat;
        this.lng=lng;
    }

    public StoreEntity toEntity(){
        return StoreEntity.builder()
                .storeName(storeName)
                .storeCategory(storeCategory)
                .addrRoad(addrRoad)
                .addrDetail(addrDetail)
                .lat(lat)
                .lng(lng)
                .build();
    }
}
