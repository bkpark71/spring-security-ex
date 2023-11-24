package com.example.security_ex.repository;

import com.example.security_ex.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account, Long>{
    Account findByUsername(String username);
}
