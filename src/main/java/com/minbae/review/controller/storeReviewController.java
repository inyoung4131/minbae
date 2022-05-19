package com.minbae.review.controller;

import com.minbae.review.service.StoreReviewService;
import com.minbae.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class storeReviewController {

    private final StoreReviewService storeReviewService;
    private final StoreService storeService;

    // 특정가게의 리뷰조회 페이지 이동 - 전체 또는 미답변 조회
//    @GetMapping("/owne/store/{storeIdx}/reviews/{allOrNoReply}")
//    public String goReviewPage(@PathVariable String storeIdx, Model model, @PathVariable String allOrNoReply){
//        model.addAttribute("storeIdx",storeIdx);
//
//        List<Map<String, Object>> reviewList = storeReviewService.getReviewList(Long.valueOf(storeIdx), allOrNoReply);
//        model.addAttribute("reviewList", reviewList);
//
//        Map<String, Integer> countMap = storeReviewService.getReviewCountNum(Long.valueOf(storeIdx));
//        model.addAttribute("countMap", countMap);
//        return "/review/store_review_main";
//    }

    // 특정가게의 리뷰조회 페이지 이동 - 전체조회
    @GetMapping("/owner/store/{storeIdx}/reviews")
    public String goReviewPage2(@PathVariable String storeIdx, Model model){
        String storeName = storeService.storeInfo(Long.valueOf(storeIdx)).getStoreName();
        model.addAttribute("storeIdx",storeIdx);
        model.addAttribute("storeName",storeName);

        List<Map<String, Object>> reviewList = storeReviewService.getReviewList(Long.valueOf(storeIdx), null);
        model.addAttribute("reviewList", reviewList);

        Map<String, Integer> countMap = storeReviewService.getReviewCountNum(Long.valueOf(storeIdx));
        model.addAttribute("countMap", countMap);
        return "review/store_review_main";
    }

//    @GetMapping("/owne/store/{storeIdx}/macroReview")
//    public String goMacroReviewPage(@PathVariable String storeIdx){
//        return "review/review_macro_main";
//    }



    @GetMapping("/owner/store/{storeIdxVal}/reviews/{selectedRadio}/{startDate}/{endDate}")
    public String goReviewPage33(@PathVariable("selectedRadio") String selectedRadio,
                                 @PathVariable("storeIdxVal") String storeIdxVal,
                                 @PathVariable(required = false) String startDate,
                                 @PathVariable(required = false) String endDate, Model model){

        model.addAttribute("selectedRadio", selectedRadio);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        List<Map<String, Object>> reviewList = storeReviewService.getReviewListNew(selectedRadio, storeIdxVal, startDate, endDate);
        model.addAttribute("reviewList", reviewList);

        Map<String, Integer> countMap = storeReviewService.getReviewCountNum(Long.valueOf(storeIdxVal));
        model.addAttribute("countMap", countMap);
        model.addAttribute("storeIdx",storeIdxVal);

        String storeName = storeService.storeInfo(Long.valueOf(storeIdxVal)).getStoreName();
        model.addAttribute("storeName",storeName);
        return "review/store_review_main";
    }




}
