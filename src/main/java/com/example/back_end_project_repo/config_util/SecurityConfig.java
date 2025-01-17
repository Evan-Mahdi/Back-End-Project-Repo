package com.example.back_end_project_repo.config_util;

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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                // Permit access to login, registration, and OAuth2 endpoints
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/login", "/api/register", "/api/login", "/oauth2-login").permitAll()
//                        .anyRequest().authenticated()
//                )
//                // Enable OAuth2 Login
//                .oauth2Login(oauth2 -> oauth2
//                        .defaultSuccessUrl("http://localhost:4200/home", true) // Redirect after successful login
//                )
//                // Enable form-based login
//                .formLogin(form -> form
//                        .defaultSuccessUrl("http://localhost:4200/home", true) // Redirect after successful login
//                        .permitAll()
//                )
//                // Configure logout
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/")
//                        .invalidateHttpSession(true)
//                        .deleteCookies("JSESSIONID")
//                )
//                // Disable CSRF (for APIs)
//                .csrf(csrf -> csrf.disable());
//
//        return http.build();
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll(); // Allow all requests without authentication
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
