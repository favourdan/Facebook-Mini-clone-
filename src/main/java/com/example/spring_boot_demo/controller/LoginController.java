package com.example.spring_boot_demo.controller;

import com.example.spring_boot_demo.model.User;
import com.example.spring_boot_demo.response.ApiResponse;
import com.example.spring_boot_demo.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping
    public ModelAndView displayLoginPage(){
        return new ModelAndView("indexPage/login");
    }

    @PostMapping
    public  String login(@RequestParam String email, @RequestParam String password, Model model , HttpSession session) {
        ApiResponse<User> userOptional = loginService.loginNewUser(email, password);
        if (userOptional.getData() == null) {
            model.addAttribute("error", userOptional.getMessage());
            return "indexPage/login";
        } else {
            session.setAttribute("user", userOptional.getData());
//            session.setAttribute("showInputField", false);
            System.out.println("LOGGED IN@@@");
            return "redirect:/";
        }

    }

}
