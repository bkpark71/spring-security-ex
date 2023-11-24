package com.example.security_ex.service;

import com.example.security_ex.entity.Account;
import com.example.security_ex.repository.AccountRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if(account == null) {
            throw new UsernameNotFoundException(username);
        }
        // Account 정보를 UserDetails 로 변경해주기
        return User.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .roles(account.getRole())
                .build();
    }

    public boolean checkAccount(Account account){
        if(account.checkPassword(account.getPassword(), passwordEncoder)){
            return true;
        }
        return false;
    }
    public Account addAccount(Account account) {
        validateDuplicateAccount(account);
        Account hashedAccount = account.hashPassword(passwordEncoder);
        return accountRepository.save(hashedAccount);
    }

    private void validateDuplicateAccount(Account account) {
        Account findAccount = accountRepository.findByUsername(account.getUsername());
        if(findAccount != null) {
            throw new IllegalStateException("이미 가입된 사용자입니다.");
        }
    }
}
