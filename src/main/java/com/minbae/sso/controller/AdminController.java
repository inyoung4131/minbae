package com.minbae.sso.controller;


import com.minbae.sso.comm.ApiResponse;
import com.minbae.sso.comm.ApiStatus;
import com.minbae.sso.jwt.JwtTokenProvider;
import com.minbae.sso.service.AdminService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;

@RequiredArgsConstructor
@RestController
@Api(tags = "A. Login api", description = "로그인 관련 api")
public class AdminController {

    private final AdminService adminService;
    private final JwtTokenProvider jwtTokenProvider;

    @ApiOperation(value = "로그인", notes = "로그인 api")
    @GetMapping("/login/{role}")
    public ApiResponse login(@PathVariable String role,String email,String pwd) {
        return new ApiResponse(ApiStatus.SUCCESS, adminService.login(role,email,pwd));
    }

    @GetMapping("/test/check/{role}")
    public boolean check(@PathVariable String role){
        String token = jwtTokenProvider.getToken();
        boolean result = jwtTokenProvider.validateExpToken(token);
        boolean result2 = false;
        if(result){
            result2=jwtTokenProvider.validateExpTokenForRole(token,role);
        }
        return result2;
    }

}
