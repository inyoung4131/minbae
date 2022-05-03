package com.minbae.user.exception;

import com.minbae.user.exception.comm.UserExceptionType;
import com.minbae.user.exception.comm.UserResponseException;

public class UserCommException extends UserResponseException {
    public UserCommException(UserExceptionType userExceptionType) {
        super(userExceptionType);
    }
}
