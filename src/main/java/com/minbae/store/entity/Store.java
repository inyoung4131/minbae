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
    private Long store_idx;

    @Column
    private Long owner_idx;

    @Column
    @NotNull
    private String store_name;

    @Column
    @NotNull
    private String store_category;

    @Column
    @NotNull
    private String store_basic_addr;

    @Column
    @NotNull
    private String store_detail_addr;

    @Column(length=8)
    @NotNull
    private Double store_lat;

    @Column(length=8)
    @NotNull
    private Double store_lng;

    @Column
    private String store_tel;

    @Builder
    public Store(long owner_idx,String store_name, String store_category, String store_basic_addr, String store_detail_addr, Double store_lat, Double store_lng,String store_tel){
        this.owner_idx=owner_idx;
        this.store_name=store_name;
        this.store_category=store_category;
        this.store_basic_addr=store_basic_addr;
        this.store_detail_addr=store_detail_addr;
        this.store_lat=store_lat;
        this.store_lng=store_lng;
        this.store_tel=store_tel;
    }

}
