package com.minbae.user.controller;

import com.minbae.store.entity.Store;
import com.minbae.user.dto.UserAddrChangeDto;
import com.minbae.user.dto.UserResponseStoreListDto;
import com.minbae.user.service.YangsUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class YangsUserRestController {

    private final YangsUserService yangsUserService;

    @PutMapping("/user/main/addr/change")
    public long userAddrChange(UserAddrChangeDto userAddrChangeDto){
        return yangsUserService.userAddrChange(userAddrChangeDto);
    }


    @GetMapping("/user/category/standard")
    public List<Map<String, Object>> getStoreByCategoryAndStandard(Model model
            , @RequestParam("paging") int paging, @RequestParam("category") String category
            , @RequestParam("user_lat") double user_lat, @RequestParam("user_lng") double user_lng){
        return yangsUserService.getStoreByCategoryAndStandard(paging, category, user_lat, user_lng);
    }

    @GetMapping("/user/store_detail/map/store")
    public Store getStoreinfo(@Param("storeIdx") long storeIdx){
        return yangsUserService.getStoreinfo(storeIdx);
    }
}
