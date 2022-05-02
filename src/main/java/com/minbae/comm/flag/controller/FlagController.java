package com.minbae.comm.flag.controller;

import com.minbae.comm.flag.dto.FlagSaveDto;
import com.minbae.comm.flag.serivce.FlagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FlagController {

    private final FlagService flagService;

    @PostMapping("/owner/create/flag")
    public String createFlag(@RequestBody FlagSaveDto flagSaveDto){
        System.out.println(flagSaveDto.getFlagLat());
        return flagService.createFlag(flagSaveDto)>0?"등록이 완료되었습니다":"등록 실패했습니다";
    }

}
