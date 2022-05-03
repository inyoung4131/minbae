package com.minbae.user.exception.comm;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserErrorResponse {
    private String code;
    private String msg;
}
