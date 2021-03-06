package com.jedrzejewski.photogallery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String loginAction() {
        return "login";
    }

    @GetMapping("/403")
    public String accessDeniedAction() {
        return "403";
    }
}
