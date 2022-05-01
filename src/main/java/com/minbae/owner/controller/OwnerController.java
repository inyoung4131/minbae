package com.minbae.owner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OwnerController {

    // 로그인 폼 이동
    @GetMapping("/loginForm/owner")
    public String loginOwnerForm(){return "owner/login_form";}

    @GetMapping("/create/owner")
    public String goCreateOwner(){
        return "owner/create_owner";
    }


}
