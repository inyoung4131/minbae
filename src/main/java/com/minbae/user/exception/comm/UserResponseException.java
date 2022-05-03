package com.minbae.user.exception.comm;


public class UserResponseException extends RuntimeException {
    public UserExceptionType userExceptionType;
    public UserResponseException(UserExceptionType userExceptionType){
        this.userExceptionType = userExceptionType;
    }
}
