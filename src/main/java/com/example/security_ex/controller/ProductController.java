package com.example.security_ex.controller;

import com.example.security_ex.dto.ProductCreateDto;
import com.example.security_ex.entity.Account;
import com.example.security_ex.service.AccountService;
import com.example.security_ex.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final AccountService accountService;
    private final ProductService productService;

    @GetMapping("/")
    public String index(Model model, Principal principal){
        return "index";
    }

    @GetMapping("/new")   // admin 만 등록 가능
    public String user(Model model, Principal principal){
        if(principal != null) {
            Account account = accountService.getAccount(principal.getName());
            model.addAttribute("managerId", account.getId());
            model.addAttribute("manager", principal.getName());
        }
        return "product/productForm";
    }

    @PostMapping("/new")   // admin 만 등록 가능
    public String user(@ModelAttribute ProductCreateDto productCreateDto,
                       Model model,
                       Principal principal){
        //if(principal != null) {}
        productService.addProduct(productCreateDto);
        return "redirect:/products/list";
    }

    @GetMapping("/list") // 조회는 로그인을 해야 볼 수 있고, user와 admin 둘 다 가능함
    public String admin(Model model, Principal principal){
        if(principal != null) {
            model.addAttribute("products", productService.getAllProducts());
        }
        return "product/productList";
    }
}
