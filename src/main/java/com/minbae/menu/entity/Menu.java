package com.minbae.menu.entity;


import com.minbae.store.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name="menu_king_menu")
    private Integer menuKingMenu; // 변경됨 ㄱㅊ?

    @Column(name="menu_sunbun")
    private int menuSunbun;

    public void addExistImage(Menu existMenu) {
        if(existMenu.menuImg != null){this.menuImg = existMenu.menuImg;}
    }

    public void updateNewData(Menu newMenuEntity) {
        if(newMenuEntity.menuName != null){this.menuName = newMenuEntity.menuName;}
        if(newMenuEntity.menuPrice != 0){this.menuPrice = newMenuEntity.menuPrice;}
        if(newMenuEntity.menuImg != null){this.menuImg = newMenuEntity.menuImg;}
        if(newMenuEntity.menuKingMenu != null){this.menuKingMenu = newMenuEntity.menuKingMenu;}
        if(newMenuEntity.menuSunbun != 0){this.menuSunbun = newMenuEntity.menuSunbun;}

    }

    public void setMenuImage(String originalFilename) {
        if(originalFilename != null){this.menuImg = originalFilename;}
    }
}
