package com.example.taxraid.controller;

import com.example.taxraid.entity.BankInformation;
import com.example.taxraid.entity.User;
import com.example.taxraid.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/history")
public class HistoryController {

    private final UserService userService;
    private final BankInformationService bankInformationService;
    private final IncomeInformationService incomeInformationService;
    private final AssetInformationService assetInformationService;

    public HistoryController(BankInformationService bankInformationService,
                             IncomeInformationService incomeInformationService,
                             UserService userService,
                             AssetInformationService assetInformationService) {
        this.bankInformationService = bankInformationService;
        this.incomeInformationService = incomeInformationService;
        this.userService = userService;
        this.assetInformationService = assetInformationService;
    }

    @GetMapping
    public String showHistoryPage(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("bankInfoList", bankInformationService.getAllBankInformation(user));
        model.addAttribute("incomeInfoList", incomeInformationService.getAllIncomeInformation(user));
        model.addAttribute("assetInfoList", assetInformationService.getAllAssetInformation(user));
        return "history";
    }
}
