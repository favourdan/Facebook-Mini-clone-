package com.example.spring_boot_demo.controller;

import com.example.spring_boot_demo.dto.UserDto;
import com.example.spring_boot_demo.response.ApiResponse;
import com.example.spring_boot_demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/signUp")
public class SignupController {
    private final UserService userService;

    @GetMapping
     public ModelAndView getSignupPage(){
        ModelAndView signupView = new ModelAndView("signUp");
        UserDto userDto = new UserDto();
        signupView.addObject("userDto", userDto);
        return signupView;
    }
     @PostMapping
    public String signupNewUser(@ModelAttribute UserDto userDto, Model model){
        ApiResponse<String> apiResponse = userService.signupNewUser(userDto);
        if(!apiResponse.getStatus()){
            model.addAttribute("error", apiResponse.getMessage());
            return "signUp";
        }
        else return "redirect:/login";
     }
}
