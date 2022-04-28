package com.minbae.store.entity;

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
    private Long store_detail_idx;

    @OneToOne
    @JoinColumn(name="store_idx")
    private Store store_idx;

    @Column(nullable = false)
    private int store_detail_minimum_price;
    @Column(nullable = false)
    private int store_detail_deliver_price;
    @Column
    private String store_detail_menu_text;
    @Column
    private String store_detail_info_text;
    @Column
    private String store_detail_review_text;
    @Column
    private String store_detail_info_img;
    @Column
    private int store_detail_take_out_discount;

}
