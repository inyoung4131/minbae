package com.minbae.owner.controller;

import com.minbae.owner.comm.OwnerApiResponse;
import com.minbae.owner.comm.OwnerApiStatus;
import com.minbae.owner.dto.OwnerRequestDTO;
import com.minbae.owner.dto.OwnerResponseDTO;
import com.minbae.owner.service.OwnerService;
import com.minbae.sso.comm.ApiResponse;
import com.minbae.sso.comm.ApiStatus;
import com.minbae.sso.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class OwnerRestController {

    private final OwnerService ownerService;
    private final AdminService adminService;

    @PostMapping("/owner/kakao/login")
    public ApiResponse ownerKakaoLogin(@RequestBody Map<String, Object> param){
        Map<String, Object> kakaoUser = ownerService.kakaoLogin(param);
        return new ApiResponse(ApiStatus.SUCCESS,
                                adminService.login("owner", (String) kakaoUser.get("owner_email"),(String) kakaoUser.get("owner_pwd")),
                                adminService.getMemberInfo("owner",(String) kakaoUser.get("owner_email"),(String) kakaoUser.get("owner_pwd")));
    }

}