package com.example.security_ex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(Model model, Principal principal){
        return "index";
    }

    @GetMapping("/user")
    public String user(Model model, Principal principal){
        if(principal == null) {
            model.addAttribute("user", "user");
        } else {
            model.addAttribute("user", principal.getName());
        }
        return "user";
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal){
        if(principal == null) {
            model.addAttribute("user", "admin");
        } else {
            model.addAttribute("user", principal.getName());
        }
        return "admin";
    }

    @GetMapping("/access-denied")
    public String accessDenied(Model model, Principal principal){
        if(principal == null) {
            model.addAttribute("user", "guest");
        } else {
            model.addAttribute("user", principal.getName());
        }
        return "access-denied";
    }
}
