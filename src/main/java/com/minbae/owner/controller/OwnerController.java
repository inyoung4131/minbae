package com.minbae.owner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OwnerController {

    @GetMapping("/create/owner")
    public String goCreateOwner(){
        return "owner/create_owner";
    }


}
