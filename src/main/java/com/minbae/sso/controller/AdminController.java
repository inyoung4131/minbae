package com.minbae.sso.controller;


import com.minbae.sso.comm.ApiResponse;
import com.minbae.sso.comm.ApiStatus;
import com.minbae.sso.service.AdminService;
import com.minbae.user.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Api(tags = "A. Login api", description = "로그인 관련 api")
public class AdminController {

    private final AdminService adminService;

    @ApiOperation(value = "로그인", notes = "로그인 api")
    @GetMapping("/login/{role}")
    public ApiResponse login(@PathVariable String role, @RequestBody User user) {

        return new ApiResponse(ApiStatus.SUCCESS, adminService.login(role,user));
    }

}
