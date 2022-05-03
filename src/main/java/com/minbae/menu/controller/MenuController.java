package com.minbae.menu.controller;

import com.minbae.menu.entity.Menu;
import com.minbae.menu.service.MenuService;
import com.minbae.store.entity.Store;
import com.minbae.store.service.StoreService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MenuController {

    public final MenuService menuService;
    public final StoreService storeService;

    // 특정 가게의 메뉴관리 페이지 이동 및 메뉴 조회
    @GetMapping("/owne/menu/{storeIdx}")
    public String menuListPage(@PathVariable Long storeIdx, Model model){
        List<Menu> menuList = menuService.getStoreMenuList(storeIdx);
        model.addAttribute("menuList", menuList);

        Store storeEntity = storeService.storeInfo(storeIdx);
        model.addAttribute("storeEntity", storeEntity);
        return "/menu/menu_list";
    }

    // 특정 가게의 신규메뉴 등록 페이지 이동
    @GetMapping("/owne/menu/create/{storeIdx}")
    public String menuCreate(@PathVariable Long storeIdx, Model model){
        model.addAttribute("storeIdx",storeIdx);
        return "/menu/menu_create_form";
    }
}
