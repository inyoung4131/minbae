package com.minbae.owner.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="owner_idx")
    private Long ownerIdx;

    @Column(unique = true,name="owner_email")
    private String ownerEmail;

    @Column(name="owner_pwd")
    private String ownerPwd;

    @Column(name="owner_nickname")
    private String ownerNickName;
}
