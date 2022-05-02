package com.minbae.comm.flag.dto;

import com.minbae.comm.flag.entity.Flag;
import com.minbae.store.entity.Store;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class FlagSaveDto {

    private Long flagIdx;
    private Store storeIdx;
    private Double flagLat;
    private Double flagLng;
    private LocalDateTime flagStartDate;

    public Flag toEntity(){
        return Flag.builder()
                .storeIdx(storeIdx)
                .flagLat(flagLat)
                .flagLng(flagLng)
                .build();
    }

}
