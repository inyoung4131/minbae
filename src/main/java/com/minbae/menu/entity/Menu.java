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
    private Long menu_idx;

    @ManyToOne
    @JoinColumn(name="store_idx",nullable = false)
    private Store store_idx;

    @Column(nullable = false)
    private String menu_name;

    @Column(nullable = false)
    private int menu_price;

    @Column
    private String menu_img;

}
