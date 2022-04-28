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
    private Long ownerIdx;
    private String ownerEmail;
    private String ownerPwd;
    private String ownerNickName;

    @Builder
    public void OwnerResponseDTO(Long ownerIdx, String ownerEmail, String ownerPwd, String ownerNickName){
        this.ownerIdx = ownerIdx;
        this.ownerEmail = ownerEmail;
        this.ownerPwd = ownerPwd;
        this.ownerNickName = ownerNickName;
    }

    public static OwnerResponseDTO toOwnerResponseDTO(Owner owner){
        return new OwnerResponseDTO(
                owner.getOwnerIdx(),
                owner.getOwnerEmail(),
                owner.getOwnerPwd(),
                owner.getOwnerNickName()
        );
    }

}
