package com.minbae.store.Entity;

import com.minbae.comm.Role;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class StoreOwner {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idx;

    @Enumerated(EnumType.STRING)
    @Column
    @NotNull
    private Role role;

    @Column(unique = true)
    private String id;

    @Column
    private String pwd;
}
