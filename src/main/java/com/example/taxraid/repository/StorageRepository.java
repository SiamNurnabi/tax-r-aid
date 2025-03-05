package com.example.taxraid.repository;

import com.example.taxraid.entity.AppFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StorageRepository extends JpaRepository<AppFile, Long> {
    Optional<AppFile> findByFileName(String fileName);
}
