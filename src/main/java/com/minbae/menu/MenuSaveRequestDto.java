package com.minbae.menu;

import com.minbae.menu.entity.Menu;
import com.minbae.store.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MenuSaveRequestDto {

    private Long menuIdx;
    private Store storeIdx;
    private String menuName;
    private int menuPrice;
    private String menuImage;
    private int menuKingMenu;
    private int menuSunbun;

    public Menu toEntity(){
        return new Menu(null, storeIdx, menuName, menuPrice, menuImage, menuKingMenu, menuSunbun);
    }

}
