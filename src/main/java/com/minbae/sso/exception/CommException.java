package com.minbae.sso.exception;

import com.minbae.sso.exception.comm.ExceptionType;
import com.minbae.sso.exception.comm.ResponseException;

public class CommException extends ResponseException {
    public CommException(ExceptionType exceptionType) {
        super(exceptionType);
    }
}
