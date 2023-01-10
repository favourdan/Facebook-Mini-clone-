package com.example.spring_boot_demo.service.serviceImpl;

import com.example.spring_boot_demo.dto.UserDto;
import com.example.spring_boot_demo.model.User;
import com.example.spring_boot_demo.repository.LoginRepository;
import com.example.spring_boot_demo.response.ApiResponse;
import com.example.spring_boot_demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginRepository loginRepository;

    @Override
    public ApiResponse<User> loginNewUser(String email, String password) {
        Optional<User> optionalUserDto = loginRepository.findByEmailAndPassword(email, password);
        if(optionalUserDto.isEmpty())
            return new ApiResponse<>("Wrong email or password", false,null);
        else return new ApiResponse<>("Successfully Logged in.", true , optionalUserDto.get());
    }
}
