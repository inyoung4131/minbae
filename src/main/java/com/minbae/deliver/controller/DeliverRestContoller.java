package com.minbae.deliver.controller;

import com.minbae.deliver.dto.AssginCompleteDto;
import com.minbae.deliver.service.DeliverService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DeliverRestContoller {
    private final DeliverService deliverService;

    //기사 배정 완료
    @PutMapping("/deliver/assign/complete")
    public void assignComplete(@RequestBody AssginCompleteDto assginCompleteDto) {
        deliverService.assignComplete(assginCompleteDto.getDeliverIdx(), assginCompleteDto.getTradeIdx());
    }
}
