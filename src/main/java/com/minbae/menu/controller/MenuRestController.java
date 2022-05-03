package com.minbae.menu.controller;

import com.minbae.menu.dto.MenuSaveRequestDto;
import com.minbae.menu.entity.Menu;
import com.minbae.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@RequiredArgsConstructor
@RestController
public class MenuRestController {

    private final MenuService menuService;

    // 특정 사장님 신규 메뉴 등록
    @PostMapping(value = "/create/menu", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Menu> createMenu(@RequestPart(value = "key") MenuSaveRequestDto requestDto,
                                           @RequestPart(value = "file",required = false) MultipartFile file){
        System.out.println("requestDTO ==>");
        log.info("requestDto");
        System.out.println("file ==>");
        log.info("file");

        Menu savedResult = menuService.createMenu(requestDto, file);
        return (savedResult != null) ?
                ResponseEntity.status(HttpStatus.CREATED).body(savedResult)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }



}
