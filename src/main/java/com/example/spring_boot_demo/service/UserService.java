package com.example.spring_boot_demo.service;

import com.example.spring_boot_demo.dto.UserDto;
import com.example.spring_boot_demo.response.ApiResponse;

public interface UserService {
    Long likePost (Long userId, Long postId);

    ApiResponse<String> signupNewUser(UserDto userDto);
}
