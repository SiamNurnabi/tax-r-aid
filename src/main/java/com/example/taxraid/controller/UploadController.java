package com.example.taxraid.controller;

import com.example.taxraid.entity.BankInformation;
import com.example.taxraid.service.BankInformationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/upload")
public class UploadController {

    public final BankInformationService bankInformationService;

    public UploadController(BankInformationService bankInformationService) {
        this.bankInformationService = bankInformationService;
    }

    @GetMapping
    public String showUploadPage(Model model) {

        model.addAttribute("bankInformation", new BankInformation());
        return "upload";
    }

    @PostMapping("/add-bank-info")
    public String submitBankInfo(@ModelAttribute("bankInformation") BankInformation bankInformation) {

        bankInformationService.save(bankInformation);
        return "redirect:/upload";
    }
}
