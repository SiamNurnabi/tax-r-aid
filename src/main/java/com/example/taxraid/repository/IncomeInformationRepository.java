package com.example.taxraid.repository;

import com.example.taxraid.entity.BankInformation;
import com.example.taxraid.entity.IncomeInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeInformationRepository extends JpaRepository<IncomeInformation, Long> {
}
