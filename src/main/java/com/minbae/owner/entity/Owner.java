package com.minbae.owner.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long owner_idx;

    @Column(unique = true)
    private String owner_email;

    @Column
    private String owner_pwd;

    @Column
    private String owner_nick_name;
}
