package com.example.chattestapp.service;

import com.example.chattestapp.User;
import com.example.chattestapp.dto.SignupRequest;
import com.example.chattestapp.dto.UserResponse;
import com.example.chattestapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public boolean existUserByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }


    public UserResponse createUser(SignupRequest signUpRequest) {
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole("USER");

        User save = userRepository.save(user);
        return UserResponse.from(save);
    }

//    public UserResponse createUser(SignupRequest signUpRequest) {
//        User user = new User();
//        user.setUsername(signUpRequest.getUsername());
//        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
//        user.setRole("USER");
//
//        User save = userRepository.save(user);
//        return UserResponse.from(save);
//    }

}
