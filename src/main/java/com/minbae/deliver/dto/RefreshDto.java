package com.minbae.deliver.dto;

import lombok.Data;

@Data
public class RefreshDto {
    String message;
    Long id;
    double lat;
    double lng;
}
