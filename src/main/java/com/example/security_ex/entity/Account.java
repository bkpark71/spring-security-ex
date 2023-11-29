package com.example.security_ex.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length=8, unique=true)
    private String username;
    //@Column(length=8)
    private String password;
    @Column(length=10)
    private String role;

    /**
     * 비밀번호 암호화
     * @param passwordEncoder
     * @return Account
     */
    public Account hashPassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(this.password);
        return this;
    }

    /**
     * 비밀번호 체크(입력한 암호값과 암호화된 암호값 비교)
     * @param password
     * @param passwordEncoder
     * @return true | false
     */
    public boolean checkPassword(String password, PasswordEncoder passwordEncoder){
        return passwordEncoder.matches(password, this.password);
    }
}
