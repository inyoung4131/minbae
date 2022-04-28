package com.minbae.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StoreController {

    @GetMapping("store/main")
    public String goStoreMainPage(){
        return "store/main_store";
    }

    @GetMapping("/create/store")
    public String goCreateStorePage(){
        return "store/create_store";
    }

}
