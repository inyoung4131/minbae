package com.minbae.comm;


import lombok.Getter;


public enum Role {
    GUEST("GUEST"),
    USER("USER"),
    STOREOWNER("STOREOWNER"),
    DELIVER("DELIVER"),
    ADMIN("ADMIN");

    @Getter
    private String role;

    Role(String role){this.role=role;}
}