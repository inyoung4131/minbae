package com.minbae.store.entity;


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
    @Column
    private Long store_idx;
    @Column
    private String menu_name;
    @Column
    private int menu_price;
    @Column
    private String menu_img;
    @Column
    private String menu_img2;

}
