package com.minbae.sso.exception.comm;

import com.minbae.sso.comm.ApiResponse;
import com.minbae.sso.comm.ApiStatus;
import com.minbae.sso.exception.CommException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e){
        List<FieldError> errorList = e.getBindingResult().getFieldErrors();
        StringBuilder errorMsg = new StringBuilder();
        errorList.forEach( item -> errorMsg.append(item.getField()+" "));
        ExceptionType exceptionType = ExceptionType.InvalidDtoException;
        ErrorResponse errorResponse = new ErrorResponse(exceptionType.getCode(), exceptionType.getMsg() + errorMsg);
        return new ResponseEntity<>(new ApiResponse<>(ApiStatus.FAIL, errorResponse), HttpStatus.OK);
    }

    @ExceptionHandler(CommException.class)
    protected ResponseEntity<ApiResponse> handleAuthException(final CommException e){
        ErrorResponse errorResponse = new ErrorResponse(e.exceptionType.getCode(), e.exceptionType.getMsg());
        return new ResponseEntity<>(new ApiResponse(ApiStatus.FAIL, errorResponse), HttpStatus.OK);
    }
}
