package com.example.taxraid.controller;

import com.example.taxraid.entity.ProfileDetails;
import com.example.taxraid.service.ProfileDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileDetailsService profileDetailsService;

    public ProfileController(ProfileDetailsService profileDetailsService) {
        this.profileDetailsService = profileDetailsService;
    }

    @GetMapping
    public String showProfilePage(Model model)
    {
        model.addAttribute("profileDetails", new ProfileDetails());

        return "profile";
    }


    @PostMapping("/add-profile-details")
    public String saveProfileDetails(@ModelAttribute("profileDetails") ProfileDetails profileDetails) {
        profileDetailsService.save(profileDetails);
        return "redirect:/profile";
    }
}
