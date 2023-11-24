package com.example.security_ex.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * WebSecurityConfigurerAdapter가 Spring Security 5.7.0-M2부터 deprecated 됨.
     * component-based security configuration으로의 사용자 전환 격려 위함.
     * 따라서 아래와 같이 bean으로 등록하여 사용.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       http
                .authorizeRequests()
                .requestMatchers("/account/new", "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin(login -> login.loginPage("/login")
                                        .usernameParameter("username")
                                        .passwordParameter("password")
                                        .defaultSuccessUrl("/"));
        return http.build();
    }
}

//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig {
//
//    @Bean
//    public static PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(request -> request
//                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
//                .anyRequest().authenticated()	// 어떠한 요청이라도 인증필요
//        )
//                .requestMatchers("/user/login", "/user/join", "/user/check").permitAll()
//                .anyRequest().authenticated();
//        // 인증 필요시 로그인 페이지와 로그인 성공시 리다이랙팅 경로 지정
//        http
//                .formLogin()
//                .loginPage("/user/login")
//                .usernameParameter("userId")
//                .passwordParameter("pwd")
//                // 로그인이 수행될 uri 매핑 (post 요청)
//                .loginProcessingUrl("/user/login")
//                .successHandler(new LoginSuccessHandler())
//                .failureHandler(new LoginFailureHandler());
//        // logout
//        http.logout().logoutUrl("/logout").logoutSuccessUrl("/user/login");
//        http.userDetailsService(userService);
//        return http.build();
//    }


//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(request -> request
//                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
//                        .anyRequest().authenticated()	// 어떠한 요청이라도 인증필요
//                );
//        http
//                .formLogin()
//                .loginPage("/user/login")
//                .defaultSuccessUrl("/", true)	// 성공 시 홈으로
//                .permitAll();	// 홈 이동이 막히면 안되므로 모두 허용
//                .usernameParameter("userId")
//                .passwordParameter("pwd")
               // .formLogin(login -> login	// form 방식 로그인 사용
            //    )
             //   .logout(withDefaults());	// 로그아웃은 기본설정으로 (/logout으로 인증해제)
//        http.logout(withDefaults());
//        return http.build();
//    }
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("user1111")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("admin1111")
//                .roles("ADMIN", "USER")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }
//}
