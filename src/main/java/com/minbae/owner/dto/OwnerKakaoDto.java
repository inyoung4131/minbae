package com.minbae.owner.dto;

import com.minbae.owner.entity.Owner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OwnerKakaoDto {

    private Long ownerIdx;
    private String ownerEmail;
    private String ownerPwd;
    private String ownerNickName;

}
