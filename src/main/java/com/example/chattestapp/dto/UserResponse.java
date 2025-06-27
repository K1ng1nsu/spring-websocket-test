package com.example.chattestapp.dto;

import com.example.chattestapp.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String username;
    private String role;

    public static UserResponse from(User save) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(save.getUsername());
        userResponse.setRole(save.getRole());
        return userResponse;
    }
}
