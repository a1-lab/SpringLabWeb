package com.learning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class WelcomeController {

    @GetMapping("welcome")
    public String welcome(ModelMap modelMap) {
        modelMap.addAttribute("message", "test attribute");
        return "welcome";
    }

    @GetMapping
    public String welcomeRoot() {
        return "welcome";
    }
}
