package com.minbae.user.controller;

import com.minbae.store.comm.StoreCategory;
import com.minbae.user.comm.UserApiResponse;
import com.minbae.user.comm.UserApiStatus;
import com.minbae.user.dao.UserMapper;
import com.minbae.user.exception.UserCommException;
import com.minbae.user.exception.comm.UserExceptionType;
import com.minbae.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/category/click/{categoryKo}/{categoryEn}")    //기본순은 여기서 뿌리기
    public ModelAndView categoryClick(@PathVariable String categoryKo, @PathVariable String categoryEn){

        ModelAndView mav = new ModelAndView();

        mav.addObject("categoryKo", categoryKo);
        mav.addObject("categoryEn", categoryEn);
        mav.setViewName("user/category_click");
        return mav;
    }

    //주문 많은 순, 별점 많은 순
    @GetMapping("/store/{categoryKo}/{categoryEn}/{type}")
    public ModelAndView getStoreByCategory(@PathVariable("categoryKo") String categoryKo,
                                           @PathVariable("categoryEn") String categoryEn,
                                           @PathVariable("type") String type) {

        //정해진 카테고리에 포함되지 않을 경우
        List<String> categorys = new ArrayList<>(Arrays.asList("CHICKEN", "CHINESEFOOD", "DESSERT", "BUNSIK", "PIZZA", "JAPANESEFOOD"));
        if(!categorys.contains(categoryEn)) {
            throw new UserCommException(UserExceptionType.NotExistCategoryException);
        }

        ModelAndView mav = new ModelAndView();
        List<Map<String, Object>> starAndOrderCntList = userService.getStoreByCategoryOrderAndStar(categoryEn, type);


        mav.addObject("starAndOrderCntList", starAndOrderCntList);
        mav.addObject("categoryKo", categoryKo);
        mav.addObject("categoryEn", categoryEn);
        mav.setViewName("user/category_click");

        return mav;
    }

    //가게 상세 정보
    @GetMapping("/store/detail/{categoryKo}/{store_idx}")
    public ModelAndView storeDetail(@PathVariable("store_idx") Long store_idx,
                                    @PathVariable("categoryKo") String categoryKo){

        //해당 가게 상세 정보
        Map<String, Object> selectStore = userService.findStoreById(store_idx);

        //해당 가게 리뷰 개수(사장, 손님)
        Map<String, Object> selectStoreReview = userService.findReviewByCount(store_idx);

        //해당 가게 대표 메뉴 리스트
        List<Map<String, Object>> kingMenu = userService.findReviewBykingMenu(store_idx);

        //해당 가게 메뉴 리스트
        List<Map<String, Object>> menuList = userService.findByMenuList(store_idx);

        ModelAndView mav = new ModelAndView();
        mav.addObject("selectStore", selectStore);
        mav.addObject("selectStoreReview", selectStoreReview);
        mav.addObject("kingMenu", kingMenu);
        mav.addObject("menuList", menuList);
        mav.addObject("categoryKo", categoryKo);
        mav.setViewName("user/store_detail");

        return mav;
    }

    //선택한 메뉴 담는 페이지
    @GetMapping("/store/menu/detail/{menu_idx}")
    public ModelAndView selectMenu(@PathVariable("menu_idx") Long menu_idx){

        //특정 메뉴idx에 해당하는 가게 정보
        Map<String, Object> storeMenuIdx = userService.findStoreByMenuIdx(menu_idx);
        System.out.println("store_detail_deliver_price => " + storeMenuIdx.get("store_detail_deliver_price"));

        ModelAndView mav = new ModelAndView();
        mav.addObject("storeMenuIdx", storeMenuIdx);
        mav.setViewName("user/munu_click");

        return mav;
    }

    //리뷰 작성 페이지 이동
    @GetMapping("/review/form")
    public String reviewForm(){
        return "user/user_review_form";
    }
}