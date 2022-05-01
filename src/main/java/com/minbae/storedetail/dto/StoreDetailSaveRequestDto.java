package com.minbae.storedetail.dto;

import com.minbae.store.entity.Store;
import com.minbae.storedetail.entity.StoreDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StoreDetailSaveRequestDto {

    private Long storeDetailIdx;
    private Store storeIdx;
    private int storeDetailMinimumPrice;
    private int storeDetailDeliverPrice;
    private String storeDetailMenuText;
    private String storeDetailInfoText;
    private String storeDetailReviewText;
    private String storeDetailInfoImg;
    private int storeDetailTakeOutDiscount;

    public StoreDetail toEntity(){
        return new StoreDetail(storeDetailIdx, storeIdx, storeDetailMinimumPrice, storeDetailDeliverPrice, storeDetailMenuText, storeDetailInfoText, storeDetailReviewText, storeDetailInfoImg, storeDetailTakeOutDiscount);
    }

}
