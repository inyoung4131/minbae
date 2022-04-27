package com.minbae.store.Entity;

import com.minbae.comm.Role;
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
    private Long idx;

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

    @Column(length=8)
    @NotNull
    private Double lat;

    @Column(length=8)
    @NotNull
    private Double lng;

    @Column
    private String owner;

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
