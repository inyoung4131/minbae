package com.minbae.owner.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OwnerRequestDTO {

    private String owner_email;
    private String owner_pwd;

    @Builder
    public void OwnerRequestDTO(String owner_email, String owner_pwd){
        this.owner_email = owner_email;
        this.owner_pwd = owner_pwd;
    }
}
