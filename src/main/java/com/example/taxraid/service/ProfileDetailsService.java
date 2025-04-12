package com.example.taxraid.service;

import com.example.taxraid.entity.ProfileDetails;
import com.example.taxraid.entity.User;
import com.example.taxraid.repository.ProfileDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileDetailsService {
    private final ProfileDetailsRepository profileDetailsRepository;

    public ProfileDetailsService(ProfileDetailsRepository profileDetailsRepository) {
        this.profileDetailsRepository = profileDetailsRepository;
    }

    public ProfileDetails save(ProfileDetails profileDetails) {
        return profileDetailsRepository.save(profileDetails);
    }

    public ProfileDetails findProfileDetailsByUser(User user) {
        return profileDetailsRepository.findProfileDetailsByUser(user);
    }
}
