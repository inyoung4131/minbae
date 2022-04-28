package com.minbae.menu.entity;


import com.minbae.store.entity.Store;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="menu_idx")
    private Long menuIdx;

    @ManyToOne
    @JoinColumn(name="store_idx",nullable = false)
    private Store storeIdx;

    @Column(nullable = false,name="menu_name")
    private String menuName;

    @Column(nullable = false,name="menu_price")
    private int menuPrice;

    @Column(name="menu_img")
    private String menuImg;

}
