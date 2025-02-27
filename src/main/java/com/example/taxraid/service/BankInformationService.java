package com.example.taxraid.service;

import com.example.taxraid.entity.BankInformation;
import com.example.taxraid.repository.BankInformationRepository;
import org.springframework.stereotype.Service;

@Service
public class BankInformationService {

    private final BankInformationRepository bankInformationRepository;

    public BankInformationService(BankInformationRepository bankInformationRepository) {
        this.bankInformationRepository = bankInformationRepository;
    }

    public BankInformation save(BankInformation bankInformation) {
        return bankInformationRepository.save(bankInformation);
    }
}
