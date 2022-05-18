package com.minbae.store.controller;

import com.minbae.store.entity.Store;
import com.minbae.store.service.StoreService;
import com.minbae.storedetail.entity.StoreDetail;
import com.minbae.storedetail.service.StoreDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class StoreController {

    private final StoreService storeService;
    private final StoreDetailService storeDetailService;

    // 사장님 광장 메인 페이지 이동
    @GetMapping("/owner/store/main")
    public String goStoreMainPage(){
        return "store/main_store";
    }

    // 특정 사장님의 가게 등록 페이지 이동
    @GetMapping("/owner/createStore/{ownerIdx}")
    public String goCreateStorePage(@PathVariable Long ownerIdx, Model model){
        model.addAttribute("ownerIdx", ownerIdx);
        return "store/create_store_new";
    }

    // 특정 사장님의 가게 목록 조회 // 이거 dead page 아닌가?
    @GetMapping("/owne/storeList/{ownerIdx}")
    public String index(@PathVariable Long ownerIdx, Model model){
        List<Store> storeList = storeService.index(ownerIdx);
        model.addAttribute("storeList", storeList);
        return "/store/show_store";
    }

    // 특정 사장님의 가게 정보 페이지로 이동
    @GetMapping("/owner/storeInfo/{storeIdx}")
    public String updateForm(@PathVariable Long storeIdx, Model model){
        Store store = storeService.storeInfo(storeIdx);
        StoreDetail storeDetail = storeDetailService.storeDetailInfo(storeIdx);

        model.addAttribute("storeInfo", store);
        model.addAttribute("StoreDetailInfo", storeDetail);
        return "/store/show_store_info";
    }

    // 특정 사장님의 가게 선택 페이지로 이동
    @GetMapping("/owner/selectStore/{ownerIdx}")
    public String goSelectStorePage(@PathVariable Long ownerIdx, Model model){
        List<Store> storeList = storeService.index(ownerIdx);
        model.addAttribute("storeList", storeList);
        model.addAttribute("ownerIdx", ownerIdx);
        return "/store/choose_store";
    }

    // 특정 사장님의 가게관리 메인 페이지 이동
    @GetMapping("/owner/manageStore/{storeIdx}")
    public String goManageStorePage(@PathVariable Long storeIdx, Model model){
        model.addAttribute("storeIdx", storeIdx);
        model.addAttribute("storeName", storeService.storeInfo(storeIdx).getStoreName());
        return "/store/store_manage_home";
    }

    @GetMapping("/stomptest")
    public String stompTest(){
        return "/tradehistory/stomptest";
    }

}
