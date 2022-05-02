package com.minbae.user.controller;

import com.minbae.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/store/{category}")
    public List<Map<String, Object>> getStoreByCategory(@PathVariable("category") String category) {
        return userService.getStoreByCategory(category);
    }


}
