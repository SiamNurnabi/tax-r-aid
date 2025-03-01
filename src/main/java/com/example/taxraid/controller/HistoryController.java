package com.example.taxraid.controller;

import com.example.taxraid.entity.BankInformation;
import com.example.taxraid.service.BankInformationService;
import com.example.taxraid.service.StorageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/history")
public class HistoryController {

    private final BankInformationService bankInformationService;
    private final StorageService storageService;

    public HistoryController(BankInformationService bankInformationService, StorageService storageService) {
        this.bankInformationService = bankInformationService;
        this.storageService = storageService;
    }

    @GetMapping
    public String showHistoryPage(Model model) {
        List<BankInformation> bankInformationList = bankInformationService.getAllBankInformation();
        model.addAttribute("bankInformationList", bankInformationList);
        return "history";
    }
}
