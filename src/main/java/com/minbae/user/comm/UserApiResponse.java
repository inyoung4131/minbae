package com.minbae.user.comm;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserApiResponse<T> {
    private UserApiStatus status;
    private T data;
}
