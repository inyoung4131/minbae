package com.minbae.map.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {

    @GetMapping("/map")
    public String goMapPage(){
        return "map_for_store_detail";
    }
}
