package com.minbae.store.entity;

import com.minbae.owner.entity.Owner;
import com.minbae.storedetail.entity.StoreDetail;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Store {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="store_idx")
    private Long storeIdx;

    @ManyToOne
    @JoinColumn(name="ownerIdx") // 어느 변수에서 가져올지 명시
    private Owner ownerIdx;

    @Column(name="store_name")
    @NotNull
    private String storeName;

    @Column(name="store_category")
    @NotNull
    private String storeCategory;

    @Column(name="store_basic_addr")
    @NotNull
    private String storeBasicAddr;

    @Column(name="store_detail_addr")
    @NotNull
    private String storeDetailAddr;

    @Column(length=8,name="store_lat")
    @NotNull
    private Double storeLat;

    @Column(length=8,name="store_lng")
    @NotNull
    private Double storeLng;

    @Column(name="store_tel")
    private String storeTel;

    @OneToOne(mappedBy = "store", orphanRemoval = true)
    private StoreDetail storeDetail;

    @Builder
    public Store(Owner ownerIdx,String storeName, String storeCategory, String storeBasicAddr, String storeDetailAddr, Double storeLat, Double storeLng,String storeTel){
        this.ownerIdx=ownerIdx;
        this.storeName=storeName;
        this.storeCategory=storeCategory;
        this.storeBasicAddr=storeBasicAddr;
        this.storeDetailAddr=storeDetailAddr;
        this.storeLat=storeLat;
        this.storeLng=storeLng;
        this.storeTel=storeTel;
    }

    // 새로만든거
    public Store(Long storeIdx, Owner ownerIdx, String storeName, String storeCategory, String storeBasicAddr, String storeDetailAddr, Double storeLat, Double storeLng, String storeTel) {
        this.storeIdx = storeIdx;
        this.ownerIdx = ownerIdx;
        this.storeName = storeName;
        this.storeCategory = storeCategory;
        this.storeBasicAddr = storeBasicAddr;
        this.storeDetailAddr = storeDetailAddr;
        this.storeLat = storeLat;
        this.storeLng = storeLng;
        this.storeTel = storeTel;
    }
}
