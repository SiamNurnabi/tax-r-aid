package com.example.taxraid.repository;

import com.example.taxraid.entity.BankInformation;
import com.example.taxraid.entity.IncomeInformation;
import com.example.taxraid.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeInformationRepository extends JpaRepository<IncomeInformation, Long> {
    List<IncomeInformation> findAllByCreatedBy(User createdBy);
}
