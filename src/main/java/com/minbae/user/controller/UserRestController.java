package com.minbae.user.controller;

import com.minbae.user.comm.UserApiResponse;
import com.minbae.user.comm.UserApiStatus;
import com.minbae.user.exception.UserCommException;
import com.minbae.user.exception.comm.UserExceptionType;
import com.minbae.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

//    //주문 많은 순, 별점 많은 순
//    @GetMapping("/store/{category}/{type}")
//    public UserApiResponse getStoreByCategory(@PathVariable("category") String category,
//                                              @PathVariable("type") String type) {
//
//        List<String> categorys = new ArrayList<>(Arrays.asList("CHICKEN", "CHINESEFOOD", "DESSERT", "BUNSIK", "PIZZA", "JAPANESEFOOD"));
//
//        if(!categorys.contains(category)) {
//            throw new UserCommException(UserExceptionType.NotExistCategoryException);
//        }
//
//
//        if(type.equals("star")){
//            return null;
//        } else if(type.equals("order")){
//            return new UserApiResponse(UserApiStatus.SUCCESS, userService.getStoreByCategory(category));
//        } else {
//            throw new UserCommException(UserExceptionType.NOT_EXIST_TYPE);
//        }
//    }

}
