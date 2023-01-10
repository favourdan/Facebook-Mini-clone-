package com.example.spring_boot_demo.service;

import com.example.spring_boot_demo.dto.UserDto;
import com.example.spring_boot_demo.model.User;
import com.example.spring_boot_demo.response.ApiResponse;

public interface LoginService {
    ApiResponse<User> loginNewUser(String email, String password);
}
