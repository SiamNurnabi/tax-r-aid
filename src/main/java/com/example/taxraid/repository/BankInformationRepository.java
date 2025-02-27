package com.example.taxraid.repository;

import com.example.taxraid.entity.BankInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankInformationRepository extends JpaRepository<BankInformation, Long> {
}
