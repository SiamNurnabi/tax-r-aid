package com.example.taxraid.config;

import com.example.taxraid.entity.User;
import com.example.taxraid.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class AuditConfig {
    @Bean
    public AuditorAware<User> auditorAware(UserRepository userRepository) {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()) {
                return Optional.empty(); // Anonymous or no-auth user
            }
            String username = authentication.getName();
            return userRepository.findByUsername(username); // Fetch full User object

        };
    }
}
