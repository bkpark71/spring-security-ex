package com.example.security_ex.controller;

import com.example.security_ex.entity.Account;
import com.example.security_ex.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/account/new")
    public String newAccount(){
        return "account/accountForm";
    }

    @PostMapping("/account/new")
    public String newAccount(@ModelAttribute Account account){

        accountService.addAccount(account);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){
        return "account/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Account account){
        UserDetails user = accountService.loadUserByUsername(account.getUsername());
        if(accountService.checkAccount(account)){
            return "redirect:/";
        };
        return "account/login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "account/logout";
    }
}
