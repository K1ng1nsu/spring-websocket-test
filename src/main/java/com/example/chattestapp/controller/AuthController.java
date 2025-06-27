package com.example.chattestapp.controller;


import com.example.chattestapp.dto.LoginRequest;
import com.example.chattestapp.dto.SignupRequest;
import com.example.chattestapp.service.AuthService;
import com.example.chattestapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/signup")
    public ResponseEntity<?> signup() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("user123123");
        signupRequest.setPassword("user");

        return ResponseEntity.status(HttpStatus.CREATED).body(authService.createUser(signupRequest));
    }

    @GetMapping("/login")
    public ResponseEntity<?> login() {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(new LoginRequest("user123123", "user")));
    }
}
