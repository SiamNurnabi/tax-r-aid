package com.example.taxraid.controller;

import com.example.taxraid.entity.BankInformation;
import com.example.taxraid.service.BankInformationService;
import com.example.taxraid.service.IncomeInformationService;
import com.example.taxraid.service.StorageService;
import com.example.taxraid.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/history")
public class HistoryController {

    private final BankInformationService bankInformationService;
    private final IncomeInformationService incomeInformationService;
    private final UserService userService;

    public HistoryController(BankInformationService bankInformationService,
                             IncomeInformationService incomeInformationService,
                             UserService userService) {
        this.bankInformationService = bankInformationService;
        this.incomeInformationService = incomeInformationService;
        this.userService = userService;
    }

    @GetMapping
    public String showHistoryPage(Model model) {
        List<BankInformation> bankInformationList = bankInformationService.getAllBankInformation(userService.getCurrentUser());
        model.addAttribute("bankInfoList", bankInformationList);
        model.addAttribute("incomeInfoList", incomeInformationService.getAllIncomeInformation());
        return "history";
    }
}
