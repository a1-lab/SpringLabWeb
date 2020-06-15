package com.learning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/movies")
public class MoviesController {

    @GetMapping("")
    String getMoviesList(){
        return "movies";
    }
}
