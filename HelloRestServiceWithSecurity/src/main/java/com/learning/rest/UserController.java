package com.learning.rest;

import com.learning.config.SecurityConfig;
import com.learning.dto.UserDto;
import com.learning.model.User;
import com.learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = SecurityConfig.USERS_ENDPOINT)
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "{id}")
    public ResponseEntity getUser(@PathVariable(name = "id") long userId) {
        User user = userService.findByUserId(userId);
        if (null != user) {
            return ResponseEntity.ok(UserDto.of(user));
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
}
