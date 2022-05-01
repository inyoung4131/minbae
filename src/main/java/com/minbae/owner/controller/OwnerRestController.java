package com.minbae.owner.controller;

import com.minbae.owner.dto.OwnerRequestDTO;
import com.minbae.owner.dto.OwnerResponseDTO;
import com.minbae.owner.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OwnerRestController {

    @Autowired
    private OwnerService ownerService;



    // 로그인 요청 처리
    @PostMapping("/login/owner")
    public ResponseEntity<OwnerResponseDTO> loginOwner(OwnerRequestDTO ownerRequestDTO){
        // service 호출
        OwnerResponseDTO target =  ownerService.loginOwner(ownerRequestDTO);

        return ResponseEntity.status(HttpStatus.OK).body(target);
    }
}