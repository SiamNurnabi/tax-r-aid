package com.example.taxraid.repository;

import com.example.taxraid.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername (@NotNull String userName);

    Optional<User> findByUsernameOrEmail(@NotNull String userName, @NotNull @Email String email);
}
