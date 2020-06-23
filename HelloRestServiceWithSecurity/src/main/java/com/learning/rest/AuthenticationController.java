package com.learning.rest;

import com.learning.config.SecurityConfig;
import com.learning.dto.AuthenticationRequestDto;
import com.learning.dto.AuthenticationResponseDto;
import com.learning.model.User;
import com.learning.security.jwt.JwtTokenProvider;
import com.learning.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;

@Log
@RestController
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JwtTokenProvider jwtTokenProvider,
                                    UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping(value = SecurityConfig.LOGIN_ENDPOINT)
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            log.info("username: " + username);
            log.info("password: " + requestDto.getPassword());

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));

            User user = userService.findByUsername(username);

            if (null == user) {
                log.log(Level.WARNING, "User: " + username + "not found");
                throw new UsernameNotFoundException("User: " + username + "not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            return ResponseEntity.ok(new AuthenticationResponseDto(username, token));
        } catch (AuthenticationException e) {
            log.log(Level.SEVERE, "Invalid username or password");
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
