package com.minbae.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StoreController {

    @GetMapping("/createStore")
    public String createStore(){
        return "store/CreateStore";
    }
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
