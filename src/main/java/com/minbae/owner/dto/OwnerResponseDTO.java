package com.minbae.owner.dto;

import com.minbae.owner.entity.Owner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OwnerResponseDTO {
    private Long owner_idx;
    private String owner_email;
    private String owner_pwd;
    private String owner_nick_name;

    @Builder
    public void OwnerResponseDTO(Long owner_idx, String owner_email, String owner_pwd, String owner_nick_name){
        this.owner_idx = owner_idx;
        this.owner_email = owner_email;
        this.owner_pwd = owner_pwd;
        this.owner_nick_name = owner_nick_name;
    }

    public static OwnerResponseDTO toOwnerResponseDTO(Owner owner){
        return new OwnerResponseDTO(
                owner.getOwner_idx(),
                owner.getOwner_email(),
                owner.getOwner_pwd(),
                owner.getOwner_nick_name()
        );
    }

}
