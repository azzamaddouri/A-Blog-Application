package com.apalindromestring.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        http
                // 1. Disable CSRF (not needed for stateless REST APIs)
                .csrf(csrf -> csrf.disable())

                // 2. Define which endpoints are public vs protected
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET,"/api/v1/posts/**").permitAll()// public
                        .requestMatchers(HttpMethod.GET,"/api/v1/categories/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/v1/tags/**").permitAll()
                        .anyRequest().authenticated()             // everything else → needs login
                )

                // 3. Use stateless session (required for JWT)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
