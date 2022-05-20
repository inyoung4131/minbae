package com.minbae.user.controller;

import com.minbae.sso.comm.ApiResponse;
import com.minbae.sso.comm.ApiStatus;
import com.minbae.sso.service.AdminService;
import com.minbae.store.entity.Store;
import com.minbae.user.comm.UserApiResponse;
import com.minbae.user.dto.UserAddrChangeDto;
import com.minbae.user.dto.UserJoinDto;
import com.minbae.user.service.YangsUserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.startup.ContextConfig;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class YangsUserRestController {

    private final YangsUserService yangsUserService;
    private final AdminService adminService;

    @PutMapping("/user/main/addr/change")
    public long userAddrChange(@RequestBody UserAddrChangeDto userAddrChangeDto) {
        System.out.println(userAddrChangeDto.getUserIdx());
        System.out.println(userAddrChangeDto.getUserBasicAddr());
        System.out.println(userAddrChangeDto.getUserDetailAddr());
        return yangsUserService.userAddrChange(userAddrChangeDto);
    }

    @GetMapping("/user/category/standard")
    public List<Map<String, Object>> getStoreByCategoryAndStandard(Model model
            , @RequestParam("paging") int paging, @RequestParam("category") String category
            , @RequestParam("user_lat") double user_lat, @RequestParam("user_lng") double user_lng) {
        switch (category) {
            case "치킨":
                category = "CHICKEN";
                break;
            case "중국집":
                category = "CHINESEFOOD";
                break;
            case "카페|디저트":
                category = "DESSERT";
                break;
            case "분식":
                category = "BUNSIK";
                break;
            case "피자":
                category = "PIZZA";
                break;
            case "돈가스|회|일식":
                category = "JAPANESEFOOD";
        }

        return yangsUserService.getStoreByCategoryAndStandard(paging, category, user_lat, user_lng);
    }

    @GetMapping("/user/store_detail/map/store")
    public Store getStoreinfo(@Param("storeIdx") long storeIdx) {
        return yangsUserService.getStoreinfo(storeIdx);
    }

    @GetMapping("/user/join/check/email")
    public UserApiResponse userEmailCheck(@RequestParam("username") String userEmail) {
        return new UserApiResponse(yangsUserService.userEmailCheck(userEmail), null);
    }

    @PostMapping("/join/user/complete")
    public ApiResponse userJoinAndLogin(@RequestBody UserJoinDto userJoinDto) {
        if (yangsUserService.join(userJoinDto)) {
            return new ApiResponse(ApiStatus.SUCCESS
                    , adminService.login("user", userJoinDto.getUserEmail(), userJoinDto.getUserPwd())
                    , adminService.getMemberInfo("user", userJoinDto.getUserEmail(), userJoinDto.getUserPwd()));
        } else {
            return new ApiResponse(ApiStatus.FAIL, null, null);
        }
    }

    @GetMapping("/join/user/sms")
    public String smsAuth(@Param("tel") String tel, HttpServletRequest request) {
        return yangsUserService.sendSms(tel);
    }

}
