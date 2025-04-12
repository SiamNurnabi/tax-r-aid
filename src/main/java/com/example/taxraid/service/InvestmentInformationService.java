package com.example.taxraid.service;

import com.example.taxraid.entity.AssetInformation;
import com.example.taxraid.entity.InvestmentInformation;
import com.example.taxraid.entity.User;
import com.example.taxraid.repository.AssetInformationRepository;
import com.example.taxraid.repository.InvestmentInformationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestmentInformationService {

    private final InvestmentInformationRepository investmentInformationRepository;

    public InvestmentInformationService(InvestmentInformationRepository investmentInformationRepository) {
        this.investmentInformationRepository = investmentInformationRepository;
    }

    public InvestmentInformation save(InvestmentInformation investmentInformation) {
        return investmentInformationRepository.save(investmentInformation);
    }

    public List<InvestmentInformation> getAllInvestmentInformation() {
        return investmentInformationRepository.findAll();
    }

    public List<InvestmentInformation> getAllInvestmentInformation(User user) {
        return investmentInformationRepository.findAllByCreatedBy(user);
    }
}
