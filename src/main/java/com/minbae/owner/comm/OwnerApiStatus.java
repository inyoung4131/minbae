package com.minbae.owner.comm;

import lombok.Getter;

public enum OwnerApiStatus {

    SUCCESS("SUCCESS"),
    FAIL("FAIL"),
    ;

    @Getter
    private String status;

    OwnerApiStatus(String status) {
        this.status = status;
    }

}