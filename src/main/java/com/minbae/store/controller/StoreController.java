package com.minbae.store.controller;

import com.minbae.store.entity.Store;
import com.minbae.store.service.StoreService;
import com.minbae.storedetail.entity.StoreDetail;
import com.minbae.storedetail.service.StoreDetailService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    // 사장님 광장 메인 페이지 이동
    @GetMapping("store/main")
    public String goStoreMainPage(){
        return "store/main_store";
    }

    // 가게 등록 페이지 이동
    @GetMapping("/create/store")
    public String goCreateStorePage(){
        return "store/create_store";
    }

    // 특정 사장님의 가게 목록 조회
    @GetMapping("/store/list/{ownerIdx}")
    public String index(@PathVariable Long ownerIdx, Model model){
        List<Store> storeList = storeService.index(ownerIdx);
        model.addAttribute("storeList", storeList);
        return "/store/show_store";
    }

    // 특정 사장님의 가게 정보 수정 페이지로 이동
    @GetMapping("/store/update/{ownerIdx}")
    public String updateForm(@PathVariable Long ownerIdx, Model model){
        //Store store = storeService;
        //StoreDetail storeDetail = storeDetailService;
        return "/store/update_store_form";
    }
}
