package com.learning.rest;

import com.learning.config.SecurityConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = SecurityConfig.TEST_ENDPOINT)
public class HelloController {
    @GetMapping
    String getHello() {
        return "Hello! I'm ok!";
    }
}
