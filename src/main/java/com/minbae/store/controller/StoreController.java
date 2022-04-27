package com.minbae.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StoreController {

    @GetMapping("/createStore")
    public String createStore(){
        return "store/CreateStore";
    }
    @GetMapping("/createStoreOwner")
    public String createStoreOwner(){
        return "store/CreateStoreOwner";
    }
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
