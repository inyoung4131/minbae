package com.minbae.review.controller;

import com.minbae.review.entity.Review;
import com.minbae.review.service.StoreReviewService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class storeReviewController {

    private final StoreReviewService storeReviewService;

    @GetMapping("/owne/store/{storeIdx}/reviews")
    public String goReviewPage(@PathVariable String storeIdx, Model model){
        model.addAttribute("storeIdx",storeIdx);
        List<Map<String, Object>> reviewList = storeReviewService.getReviewList(Long.valueOf(storeIdx));
        model.addAttribute("reviewList", reviewList);
        return "/review/store_review_main";
    }

    @GetMapping("/owne/store/{storeIdx}/macroReview")
    public String goMacroReviewPage(@PathVariable String storeIdx){
        return "/review/review_macro_main";
    }


}
