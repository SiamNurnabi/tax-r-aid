package com.example.taxraid.controller;

import com.example.taxraid.entity.ProfileDetails;
import com.example.taxraid.entity.User;
import com.example.taxraid.enums.ResidentialStatus;
import com.example.taxraid.enums.SpecialBenefitType;
import com.example.taxraid.enums.TaxPayerStatus;
import com.example.taxraid.service.CustomUserDetailsService;
import com.example.taxraid.service.ProfileDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Objects;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileDetailsService profileDetailsService;
    private final CustomUserDetailsService userDetailsService;

    public ProfileController(ProfileDetailsService profileDetailsService, CustomUserDetailsService userDetailsService) {
        this.profileDetailsService = profileDetailsService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping
    public String showProfilePage(Model model)
    {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User existingUser = userDetailsService.findByUserName(user.getUsername());
        ProfileDetails profileDetails = existingUser.getProfileDetails();

        if(Objects.isNull(profileDetails)) {
            profileDetails = new ProfileDetails();
        }

        model.addAttribute("user", user);
        model.addAttribute("profileDetails", profileDetails);
        model.addAttribute("residentialStatusList", Arrays.asList(ResidentialStatus.values()));
        model.addAttribute("taxPayerStatusList", Arrays.asList(TaxPayerStatus.values()));
        model.addAttribute("specialBenefitTypeList", Arrays.asList(SpecialBenefitType.values()));

        return "profile";
    }


    @PostMapping("/add-profile-details")
    public String saveProfileDetails(@Valid @ModelAttribute("profileDetails") ProfileDetails profileDetails,
                                     @ModelAttribute("user") User user) {
        User currentUser = userDetailsService.findByUserName(user.getUsername());

        if (currentUser.getProfileDetails() != null) {
            ProfileDetails existingProfile = currentUser.getProfileDetails();
            BeanUtils.copyProperties(profileDetails, existingProfile, "id");

            profileDetailsService.save(existingProfile);
        } else {
            profileDetailsService.save(profileDetails);
            currentUser.setProfileDetails(profileDetails);
            userDetailsService.updateUser(currentUser);
        }

        return "redirect:/profile";
    }
}
