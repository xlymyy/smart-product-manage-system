package com.guet.smartshop123.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class LoginController {
    @GetMapping("/toLogin")
    public String login(){
        return "login";
    }
}