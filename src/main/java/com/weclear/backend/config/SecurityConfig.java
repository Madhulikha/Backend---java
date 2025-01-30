package com.weclear.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF protection
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api1/**").permitAll() // Allow all users 
                .requestMatchers("/api1/services/**").permitAll()
                
                .requestMatchers("/api1/addservice").hasRole("ADMIN")
                .requestMatchers("/api1/updateservice/**").hasRole("ADMIN") 
                .requestMatchers("/api1/deleteservice/**").hasRole("ADMIN")// Only allow admins to manage services
                .anyRequest().permitAll()
            )
            .httpBasic(httpBasic -> {}) // Configure HTTP Basic authentication

            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Create session if required
                .maximumSessions(1) // One session per user
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCrypt for password encoding
    }
}
