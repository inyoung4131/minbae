package com.minbae.user.controller;

import com.minbae.user.dto.UserAddrChangeDto;
import com.minbae.user.service.YangsUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class YangsUserRestController {

    private final YangsUserService yangsUserService;

    @PutMapping("/user/main/addr/change")
    public long userAddrChange(UserAddrChangeDto userAddrChangeDto){
        return yangsUserService.userAddrChange(userAddrChangeDto);
    }


    @GetMapping("/user/category/standard")
    public Model getStoreByCategoryAndStandard(Model model, int page, String category, int user_lat, int user_lng){
        List list =yangsUserService.getStoreByCategoryAndStandard(page, category, user_lat, user_lng);
        model.addAttribute("store_list",list);
        return  model;
    }
}
