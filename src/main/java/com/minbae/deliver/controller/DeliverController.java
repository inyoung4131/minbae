package com.minbae.deliver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeliverController {

    @GetMapping("/test/del")
    public String asdfasfd(){
        return "deliver/deliver_main";
    }
}
