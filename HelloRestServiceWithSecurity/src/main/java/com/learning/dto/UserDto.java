package com.learning.dto;

import com.learning.model.User;
import lombok.Getter;

@Getter
public class UserDto {
    private final long id;
    private final String username;
    private final boolean enabled;

    public UserDto(long id, String username, boolean enabled) {
        this.id = id;
        this.username = username;
        this.enabled = enabled;
    }

    public static UserDto of(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.isEnabled());
    }
}
