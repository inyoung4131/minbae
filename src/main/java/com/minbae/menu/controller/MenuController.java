package com.minbae.menu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MenuController {
    @GetMapping("/menu/{storeid}")
    public String menuManage(@PathVariable Long storeid){
        return "menu/manage";
    }
}
