package com.minbae.sso.exception.comm;

public class ResponseException extends RuntimeException {
    public ExceptionType exceptionType;
    public ResponseException(ExceptionType exceptionType){
        this.exceptionType = exceptionType;
    }
}