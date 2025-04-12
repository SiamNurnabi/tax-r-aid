package com.example.taxraid.repository;

import com.example.taxraid.entity.ProfileDetails;
import com.example.taxraid.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileDetailsRepository extends JpaRepository<ProfileDetails, Long> {
        ProfileDetails findProfileDetailsByUser(User user);
}
