package com.learning.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    private final Long id;
    private final String username;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final boolean enabled;
    private final List<Role> roles = new ArrayList<>();

    public User(Long id,
                String username,
                String password,
                String firstName,
                String lastName,
                String email,
                boolean enabled,
                Role... roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enabled = enabled;
        for (Role role : roles) {
            this.roles.add(role);
        }
    }
}
