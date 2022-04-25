package com.minbae.store.Entity;

import com.sun.istack.NotNull;
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
    private Long id;

    @Column
    @NotNull
    private String storeName;

    @Column
    @NotNull
    private String storeCategory;

    @Column
    @NotNull
    private String addrRoad;

    @Column
    @NotNull
    private String addrDetail;

    @Column
    private Double lat;

    @Column
    private Double lng;

    @Builder
    public Store(String storeName, String storeCategory, String addrRoad, String addrDetail, Double lat, Double lng){
        this.storeName=storeName;
        this.storeCategory=storeCategory;
        this.addrRoad=addrRoad;
        this.addrDetail=addrDetail;
        this.lat=lat;
        this.lng=lng;
    }

}
