package com.minbae.user.dto;

import com.minbae.user.entity.User;
import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserJoinDto {
    private String userEmail;
    private String userPwd;
    private String userNickname;
    private String userBasicAddr;
    private String userDetailAddr;
    private String userTel;
    private String userSocial;

    public User toEntity() {
        return User.builder()
                .userEmail(userEmail)
                .userPwd(userPwd)
                .userNickName(userNickname)
                .userBasicAddr(userBasicAddr)
                .userDetailAddr(userDetailAddr)
                .userTel(userTel)
                .userSocial(userSocial)
                .build();
    }

}
