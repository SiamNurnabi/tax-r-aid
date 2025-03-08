package com.example.taxraid.controller;

import com.example.taxraid.entity.BankInformation;
import com.example.taxraid.service.BankInformationService;
import com.example.taxraid.service.IncomeInformationService;
import com.example.taxraid.service.StorageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/history")
public class HistoryController {

    private final BankInformationService bankInformationService;
    private final IncomeInformationService incomeInformationService;
    private final StorageService storageService;

    public HistoryController(BankInformationService bankInformationService,
                             IncomeInformationService incomeInformationService,
                             StorageService storageService) {
        this.bankInformationService = bankInformationService;
        this.incomeInformationService = incomeInformationService;
        this.storageService = storageService;
    }

    @GetMapping
    public String showHistoryPage(Model model) {
        List<BankInformation> bankInformationList = bankInformationService.getAllBankInformation();
        model.addAttribute("bankInfoList", bankInformationList);
        model.addAttribute("incomeInfoList", incomeInformationService.getAllIncomeInformation());
        return "history";
    }
}
