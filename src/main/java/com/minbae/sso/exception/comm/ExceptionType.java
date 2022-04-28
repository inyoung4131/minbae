package com.minbae.sso.exception.comm;

import lombok.Getter;

public enum ExceptionType {

    NotExistException("존재하는 계정이 아닙니다.","A01"),

    InvalidDtoException("check data : ", "D01"),
    ;

    @Getter
    private String msg;
    @Getter
    private String code;

    ExceptionType(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }
    
}
