package com.dizi.dz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login/oauth2/code/dizi-cloud")
    public String oauth2Login() {
        return "code";
    }

}
