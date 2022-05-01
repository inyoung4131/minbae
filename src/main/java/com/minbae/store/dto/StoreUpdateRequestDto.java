package com.minbae.store.dto;

import com.minbae.owner.entity.Owner;
import com.minbae.store.entity.Store;
import lombok.*;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StoreUpdateRequestDto {

    private Long storeIdx;
    private Owner ownerIdx;
    private String storeName;
    private String storeCategory;
    private String storeBasicAddr;
    private String storeDetailAddr;
    private Double storeLat;
    private Double storeLng;
    private String storeTel;

    public Store toEntity(){
        return new Store(storeIdx,ownerIdx,storeName, storeCategory, storeBasicAddr, storeDetailAddr, storeLat, storeLng,storeTel);
    }

}
