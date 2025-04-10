package com.example.taxraid.controller;

import com.example.taxraid.entity.User;
import com.example.taxraid.service.CustomUserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("register")
public class AuthenticationController {

    private final CustomUserDetailsService userDetailsService;

    public AuthenticationController(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("user") User user,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        // save the user in db
        userDetailsService.saveUser(user);
        redirectAttributes.addAttribute("message", "User registered successfully");
        return "redirect:/register";

    }
}
