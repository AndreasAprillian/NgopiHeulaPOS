package com.example.NgopiHeula.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping("/loginForm")
    public String loginFrom(){
        return "login/login-form";
    }
    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "login/access-denied";
    }
    @GetMapping("/loginFailed")
    public String loginFailed(){
        return "login/login-failed";
    }
}
