package com.minbae.owner.comm;

import com.minbae.sso.comm.ApiStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class OwnerApiResponse<T> {

    private OwnerApiStatus status;
    private T data;

}
