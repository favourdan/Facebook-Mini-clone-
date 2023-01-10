package com.example.spring_boot_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/home"})
public class UserController {
    @GetMapping
    public String getHomePage(Model model) {
        model.addAttribute("greeting", "Good morning, sucker!!!");
        return "index";
    }

}
