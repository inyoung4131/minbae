package com.minbae.user.controller;

import com.minbae.store.comm.StoreCategory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @GetMapping("/category/click/{categoryKo}/{categoryEn}")
    public ModelAndView categoryClick(@PathVariable String categoryKo, @PathVariable String categoryEn){
        ModelAndView mav = new ModelAndView();

//        List<String> list = new ArrayList<>();
//        for(StoreCategory type : StoreCategory.values()) {
//            list.add(type.getCategory());
//            System.out.println(type.getCategory());
//        }
//        mav.addObject("list", list);
        System.out.println(categoryKo);
        mav.addObject("category", categoryKo);
        mav.setViewName("user/category_click");
        return mav;
    }


}
