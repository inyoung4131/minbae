package com.minbae.sso.controller;


import com.minbae.sso.comm.ApiResponse;
import com.minbae.sso.comm.ApiStatus;
import com.minbae.sso.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Api(tags = "A. Login api", description = "로그인 관련 api")
public class AdminController {

    private final AdminService adminService;

    @ApiOperation(value = "로그인", notes = "로그인 api")
    @GetMapping("/login/{role}")
    public ApiResponse login(@PathVariable String role,String email,String pwd) {
        return new ApiResponse(ApiStatus.SUCCESS, adminService.login(role,email,pwd));
    }

}
