package com.example.security_ex.service;

import com.example.security_ex.dto.ProductCreateDto;
import com.example.security_ex.entity.Account;
import com.example.security_ex.entity.Product;
import com.example.security_ex.repository.AccountRepository;
import com.example.security_ex.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;

    public Product addProduct(ProductCreateDto productCreateDto) {
        validateDuplicateProduct(productCreateDto.getName());
        Product product = new Product();
        product.setName(productCreateDto.getName());
        product.setPrice(productCreateDto.getPrice());
        product.setQuantity(productCreateDto.getQuantity());
        Optional<Account> byId = accountRepository.findById(productCreateDto.getManagerId());
        product.setManager(byId.get());
        return productRepository.save(product);
    }

    private void validateDuplicateProduct(String name) {
        Product findProduct = productRepository.findByName(name);
        if(findProduct != null) {
            throw new IllegalStateException("이미 등록된 제품입니다.");
        }
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
