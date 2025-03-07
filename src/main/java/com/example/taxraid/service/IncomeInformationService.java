package com.example.taxraid.service;

import com.example.taxraid.entity.BankInformation;
import com.example.taxraid.entity.IncomeInformation;
import com.example.taxraid.repository.BankInformationRepository;
import com.example.taxraid.repository.IncomeInformationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeInformationService {

    private final IncomeInformationRepository incomeInformationRepository;

    public IncomeInformationService(IncomeInformationRepository incomeInformationRepository) {
        this.incomeInformationRepository = incomeInformationRepository;
    }

    public IncomeInformation save(IncomeInformation incomeInformation) {
        return incomeInformationRepository.save(incomeInformation);
    }

    public List<IncomeInformation> getAllIncomeInformation() {
        return incomeInformationRepository.findAll();
    }
}
