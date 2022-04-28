package com.minbae.storedetail.entity;

import com.minbae.store.entity.Store;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
public class StoreDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="store_detail_idx")
    private Long storeDetailIdx;

    @OneToOne(mappedBy = "storeDetail")
    private Store store;

    @Column(nullable = false,name="store_detail_minimum_price")
    private int storeDetailMinimumPrice;
    @Column(nullable = false,name="store_detail_deliver_price")
    private int storeDetailDeliverPrice;
    @Column(name="store_detail_menu_text")
    private String storeDetailMenuText;
    @Column(name="store_detail_info_text")
    private String storeDetailInfoText;
    @Column(name="store_detail_review_text")
    private String storeDetailReviewText;
    @Column(name="store_detail_info_img")
    private String storeDetailInfoImg;
    @Column(name="store_detail_take_out_discount")
    private int storeDetailTakeOutDiscount;

}