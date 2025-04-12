package com.example.taxraid.repository;

import com.example.taxraid.entity.AssetInformation;
import com.example.taxraid.entity.InvestmentInformation;
import com.example.taxraid.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvestmentInformationRepository extends JpaRepository<InvestmentInformation, Long> {
    List<InvestmentInformation> findAllByCreatedBy(User createdBy);
}
