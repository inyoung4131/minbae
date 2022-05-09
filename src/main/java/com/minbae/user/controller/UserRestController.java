package com.minbae.user.controller;

import com.minbae.user.comm.UserApiResponse;
import com.minbae.user.comm.UserApiStatus;
import com.minbae.user.dto.UserReviewDTO;
import com.minbae.user.exception.UserCommException;
import com.minbae.user.exception.comm.UserExceptionType;
import com.minbae.user.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    //사용자 리뷰 작성
    @PostMapping("/review/create")
    public UserApiResponse reviewCreate(MultipartHttpServletRequest req, UserReviewDTO dto) throws Exception {

        List<MultipartFile> upload = req.getFiles("upload");
//        upload.forEach(data -> {
//            System.out.println("name => " + data.getOriginalFilename());
//        });
        int InsertResult = userService.reviewCreate(dto, upload);

        return (InsertResult == 1) ?
                new UserApiResponse(UserApiStatus.SUCCESS, dto) :
                new UserApiResponse(UserApiStatus.FAIL, dto);
    }

    @DeleteMapping("/review/delete/{review_idx}")
    public UserApiResponse reviewDel(@PathVariable("review_idx") String review_idx){

        int deleteResult = userService.reviewDel(review_idx);


        return (deleteResult == 1) ?
        new UserApiResponse(UserApiStatus.SUCCESS, deleteResult) :
        new UserApiResponse(UserApiStatus.FAIL, deleteResult);
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
