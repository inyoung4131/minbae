package com.minbae.user.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_idx")
    private long userIdx;

    @Column(nullable = false,name="user_email")
    private String userEmail;

    @Column(nullable = false,name="user_pwd")
    private String userPwd;

    @Column(nullable = false,name="user_nickname")
    private String userNickname;

    @Column(nullable = false,name="user_basic_addr")
    private String userBasicAddr;

    @Column(nullable = false,name="user_detail_addr")
    private String userDetailAddr;

    @Column(nullable = false,name="user_tel")
    private String userTel;

    @Column(nullable = false,name="user_social")
    private String userSocial;

    @Builder
    public User(long userIdx,String userEmail,String userPwd,String userNickName,String userBasicAddr,String userDetailAddr,String userTel,String userSocial){
        this.userIdx=userIdx;
        this.userEmail=userEmail;
        this.userPwd=userPwd;
        this.userNickname=userNickName;
        this.userBasicAddr=userBasicAddr;
        this.userDetailAddr=userDetailAddr;
        this.userTel=userTel;
        this.userSocial=userSocial;
    }
}
