package com.minbae.user.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_idx;

    @Column(nullable = false)
    private String user_email;

    @Column(nullable = false)
    private String user_pwd;

    @Column(nullable = false)
    private String user_nickname;

    @Column(nullable = false)
    private String user_basic_addr;

    @Column(nullable = false)
    private String user_detail_addr;

    @Column(nullable = false)
    private String user_tel;

    @Column(nullable = false)
    private String user_social;

    @Builder
    public User(String user_email,String user_pwd,String user_nickName,String user_basic_addr,String user_detail_addr,String user_tel,String user_social){
        this.user_email=user_email;
        this.user_pwd=user_pwd;
        this.user_nickname=user_nickName;
        this.user_basic_addr=user_basic_addr;
        this.user_detail_addr=user_detail_addr;
        this.user_tel=user_tel;
        this.user_social=user_social;
    }
}
