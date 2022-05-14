package com.minbae.user.exception.comm;

import lombok.Getter;

public enum UserExceptionType {
    NotExistCategoryException("존재하는 카테고리가 아닙니다.","A01"),
    NOT_EXIST_TYPE("존재하는 type이 아닙니다.","etetet"),
    InvalidDtoException("check data : ", "D01"),
    inconsistency("요청 금액과 결제된 금액이 다릅니다.", "444"),
    ;

    @Getter
    private String msg;
    @Getter
    private String code;

    UserExceptionType(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }
}
