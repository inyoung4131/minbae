package com.minbae.owner.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OwnerRequestDTO {

    private String ownerEmail;
    private String ownerPwd;

    @Builder
    public void OwnerRequestDTO(String ownerEmail, String ownerPwd){
        this.ownerEmail = ownerEmail;
        this.ownerPwd = ownerPwd;
    }
}
