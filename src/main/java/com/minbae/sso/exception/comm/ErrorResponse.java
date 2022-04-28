package com.minbae.sso.exception.comm;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    private String code;
    private String msg;
}