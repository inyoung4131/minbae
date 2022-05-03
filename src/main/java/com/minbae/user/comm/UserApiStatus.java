package com.minbae.user.comm;

import lombok.Getter;

public enum UserApiStatus {
    SUCCESS("SUCCESS"),
    FAIL("FAIL"),
    ;

    @Getter
    private String status;

    UserApiStatus(String status) {
            this.status = status;
        }

}
