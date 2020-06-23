package com.learning.dto;

import lombok.Getter;

@Getter
public class AuthenticationResponseDto {
    private final String username;
    private final String token;

    public AuthenticationResponseDto(String username, String token) {
        this.username = username;
        this.token = token;
    }
}
