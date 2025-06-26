package com.example.chattestapp.service;

import com.example.chattestapp.dto.JwtResponse;
import com.example.chattestapp.dto.LoginRequest;
import com.example.chattestapp.dto.SignupRequest;
import com.example.chattestapp.dto.UserResponse;
import com.example.chattestapp.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;


    public UserResponse createUser(SignupRequest signupRequest) {
        if (userService.existUserByUsername(signupRequest.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        return userService.createUser(signupRequest);
    }

    public JwtResponse login(LoginRequest loginRequest) {

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        UserDetails principal = (UserDetails) authenticate.getPrincipal();

        String jwt = jwtUtil.generateToken(principal);

        return new JwtResponse(jwt);
    }

}
