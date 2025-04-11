package com.example.taxraid.repository;

import com.example.taxraid.entity.AssetInformation;
import com.example.taxraid.entity.BankInformation;
import com.example.taxraid.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetInformationRepository extends JpaRepository<AssetInformation, Long> {
    List<AssetInformation> findAllByCreatedBy(User createdBy);
}
