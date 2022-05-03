package com.minbae.menu.controller;

import com.minbae.menu.entity.Menu;
import com.minbae.menu.service.MenuService;
import com.minbae.store.entity.Store;
import com.minbae.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.io.*;

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

    // 이미지 출력
    @GetMapping(value = "/image/{imagename}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> userSearch(@PathVariable("imagename") String imagename) throws IOException {
        InputStream imageStream = new FileInputStream("C:\\workspace\\upload\\" + imagename);
        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
        imageStream.close();
        return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
    }


}
