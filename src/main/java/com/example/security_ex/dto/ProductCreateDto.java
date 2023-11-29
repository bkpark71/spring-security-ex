package com.example.security_ex.dto;

import com.example.security_ex.entity.Account;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ProductCreateDto {
    private String name;
    private Integer price;
    private Integer quantity;
    private Long managerId;
    private String manager;
}
