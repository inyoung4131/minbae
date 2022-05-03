package com.minbae.sso.comm;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T,S> {
    private ApiStatus status;
    private T data;
    private S memberData;
}
