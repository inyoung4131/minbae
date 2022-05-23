package com.minbae.deliver.dto;

import lombok.Data;
import lombok.Setter;

@Data
public class ResponseMessageDto {
    private String Message;
    private Long tradeHistoryIdx;
}
