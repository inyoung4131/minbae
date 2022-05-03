package com.minbae.user.exception.comm;

import com.minbae.user.comm.UserApiResponse;
import com.minbae.user.comm.UserApiStatus;
import com.minbae.user.exception.UserCommException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class UserExceptionController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<UserApiResponse> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e){
        List<FieldError> errorList = e.getBindingResult().getFieldErrors();
        StringBuilder errorMsg = new StringBuilder();
        errorList.forEach( item -> errorMsg.append(item.getField()+" "));
        UserExceptionType userExceptionType = UserExceptionType.InvalidDtoException;
        UserErrorResponse userErrorResponse = new UserErrorResponse(userExceptionType.getCode(), userExceptionType.getMsg() + errorMsg);
        return new ResponseEntity<>(new UserApiResponse(UserApiStatus.FAIL, userErrorResponse), HttpStatus.OK);
    }

    @ExceptionHandler(UserCommException.class)
    protected ResponseEntity<UserApiResponse> handleAuthException(final UserCommException e){
        UserErrorResponse userErrorResponse = new UserErrorResponse(e.userExceptionType.getCode(), e.userExceptionType.getMsg());
        return new ResponseEntity<>(new UserApiResponse(UserApiStatus.FAIL, userErrorResponse), HttpStatus.OK);
    }
}
