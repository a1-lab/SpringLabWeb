package com.learning.security;

import com.learning.model.Role;
import com.learning.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class SecurityUserBuilder {
    public SecurityUserBuilder() {
    }

    public static SecurityUser create(User user) {
        return new SecurityUser(user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.isEnabled(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles())));
    }

    public static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).
                collect(Collectors.toList());
    }
}
