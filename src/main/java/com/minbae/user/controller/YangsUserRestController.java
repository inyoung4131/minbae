package com.minbae.user.controller;

import com.minbae.user.dto.UserAddrChangeDto;
import com.minbae.user.service.YangsUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class YangsUserRestController {

    private final YangsUserService yangsUserService;

    @PutMapping("/user/main/addr/change")
    public long userAddrChange(UserAddrChangeDto userAddrChangeDto){
        return yangsUserService.userAddrChange(userAddrChangeDto);
    }
}
