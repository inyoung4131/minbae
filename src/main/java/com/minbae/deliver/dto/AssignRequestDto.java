package com.minbae.deliver.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignRequestDto {
    private String message;
    private Long storeIdx;
    private Long tradeHistoryIdx;
    private Long userIdx;
    private Double storeLat;
    private Double storeLng;
    private String storeName;
    private String storeBasicAddr;
    private String userBasicAddr;
}
