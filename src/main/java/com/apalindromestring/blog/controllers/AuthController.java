package com.apalindromestring.blog.controllers;

import com.apalindromestring.blog.domain.dtos.AuthResponse;
import com.apalindromestring.blog.domain.dtos.LoginRequest;
import com.apalindromestring.blog.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;


    @PostMapping
    public ResponseEntity<AuthResponse> register(@RequestBody LoginRequest loginRequest) {
        UserDetails userDetails = authenticationService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        String token = authenticationService.generateToken(userDetails);
         AuthResponse authResponse = AuthResponse.builder().token(token).expiresIn(86400).build();

         return ResponseEntity.ok(authResponse);
    }
}
