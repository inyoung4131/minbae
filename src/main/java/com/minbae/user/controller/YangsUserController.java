package com.minbae.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class YangsUserController {

    @GetMapping("/user/main/addr/main")
    public String mainPageAddrPage(){
        return "user/addr/main_page_addr_change_view_page";
    }
    @GetMapping("/user/main/addr/edit")
    public String mainPageAddrEditPage(){
        return "user/addr/main_page_addr_change_view_page_edit";
    }
    @GetMapping("/user/main/addr/map")
    public String mainPageAddrMapPage(){
        return "user/addr/main_page_addr_change_view_page_map";
    }
    @GetMapping("/user/main/addr/search")
    public String mainPageSearchPage(){
        return "user/addr/main_page_addr_change_view_page_search";
    }
    @GetMapping("/user/main/addr/search/result")
    public ModelAndView mainPageSearchResultPage(String basic, String road, ModelAndView model){
        model.addObject("basic",basic);
        model.addObject("road",road);
        model.setViewName("user/addr/main_page_addr_change_view_page_search_result");
        return model;
    }
    @GetMapping("/test")
    public String test(){return "user/join/join_main";}
}
