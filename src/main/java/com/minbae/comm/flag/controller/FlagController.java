package com.minbae.comm.flag.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class FlagController {

    @GetMapping("/owner/create/flag/{storeIdx}")
    public String goFlagPage(@PathVariable String storeIdx){
        return "flag/flagcreate";
    }

}
