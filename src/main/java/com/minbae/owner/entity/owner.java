package com.minbae.owner.entity;

import com.minbae.comm.Role;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class owner {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long owner_idx;

    @Column
    @NotNull
    private String owner_email;

    @Column(unique = true)
    private String owner_pwd;

    @Column
    private String owner_nick_name;
}
