package com.learning.security;

import com.learning.model.User;
import com.learning.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Level;

@Log
@Service
public class SecurityUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public SecurityUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        log.info("call SecurityUserDetailsService.loadUserByUsername()");
        log.info("with user:" + user);

        if (null == user) {
            log.log(Level.SEVERE, "User not found: " + username);
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return SecurityUserBuilder.create(user);
    }
}
