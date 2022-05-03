package com.minbae.menu.controller;

import com.minbae.menu.service.MenuService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class MenuRestController {

    private final MenuService menuService;

}
