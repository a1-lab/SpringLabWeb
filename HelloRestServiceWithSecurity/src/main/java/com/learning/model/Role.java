package com.learning.model;

import lombok.Getter;

@Getter
public class Role {
    private final ROLE_ENUM roleValue;

    public Role(ROLE_ENUM value){
        roleValue = value;
    }

    public String getRoleName(){
        return roleValue.name();
    }
}
