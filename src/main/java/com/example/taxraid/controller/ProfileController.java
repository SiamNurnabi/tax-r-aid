package com.example.taxraid.controller;

import com.example.taxraid.entity.ProfileDetails;
import com.example.taxraid.entity.User;
import com.example.taxraid.enums.ResidentialStatus;
import com.example.taxraid.enums.SpecialBenefitType;
import com.example.taxraid.enums.TaxPayerStatus;
import com.example.taxraid.repository.ProfileDetailsRepository;
import com.example.taxraid.service.CustomUserDetailsService;
import com.example.taxraid.service.ProfileDetailsService;
import com.example.taxraid.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
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
    private final UserService userService;
    private final CustomUserDetailsService customUserDetailsService;
    private final ProfileDetailsRepository profileDetailsRepository;

    public ProfileController(ProfileDetailsService profileDetailsService, UserService userService, CustomUserDetailsService customUserDetailsService,
                             ProfileDetailsRepository profileDetailsRepository) {
        this.profileDetailsService = profileDetailsService;
        this.userService = userService;
        this.customUserDetailsService = customUserDetailsService;
        this.profileDetailsRepository = profileDetailsRepository;
    }

    @GetMapping
    public String showProfilePage(Model model)
    {
        User existingUser = userService.getCurrentUser();
        ProfileDetails profileDetails = profileDetailsService.findProfileDetailsByUser(existingUser);

        if(Objects.isNull(profileDetails)) {
            profileDetails = new ProfileDetails();
        }

        model.addAttribute("user", existingUser);
        model.addAttribute("profileDetails", profileDetails);
        model.addAttribute("residentialStatusList", Arrays.asList(ResidentialStatus.values()));
        model.addAttribute("taxPayerStatusList", Arrays.asList(TaxPayerStatus.values()));
        model.addAttribute("specialBenefitTypeList", Arrays.asList(SpecialBenefitType.values()));

        return "profile";
    }

    @Transactional
    @PostMapping("/add-profile-details")
    public String saveProfileDetails(@Valid @ModelAttribute("profileDetails") ProfileDetails profileDetails,
                                     @ModelAttribute("user") User user) {
        User currentUser = userService.getCurrentUser();
        ProfileDetails existingProfile = profileDetailsService.findProfileDetailsByUser(currentUser);

        if (Objects.nonNull(existingProfile)) {
            BeanUtils.copyProperties(profileDetails, existingProfile, "id", "user");

            profileDetailsService.save(existingProfile);
        } else {
            profileDetails.setUser(currentUser);
            profileDetailsRepository.save(profileDetails);
        }

        return "redirect:/profile";
    }
}
