package com.example.taxraid.controller;

import com.example.taxraid.entity.*;
import com.example.taxraid.enums.AppFileType;
import com.example.taxraid.enums.AssetType;
import com.example.taxraid.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/upload")
public class UploadController {

    private final StorageService storageService;
    private final UserService userService;
    private final BankInformationService bankInformationService;
    private final IncomeInformationService incomeInformationService;
    private final AssetInformationService assetInformationService;
    private final InvestmentInformationService investmentInformationService;

    public UploadController(BankInformationService bankInformationService,
                            IncomeInformationService incomeInformationService,
                            StorageService storageService,
                            UserService userService,
                            AssetInformationService assetInformationService, InvestmentInformationService investmentInformationService) {
        this.bankInformationService = bankInformationService;
        this.incomeInformationService = incomeInformationService;
        this.storageService = storageService;
        this.userService = userService;
        this.assetInformationService = assetInformationService;
        this.investmentInformationService = investmentInformationService;
    }

    @GetMapping
    public String showUploadPage(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("bankInformation", new BankInformation());
        model.addAttribute("incomeInformation", new IncomeInformation());
        model.addAttribute("assetInformation", new AssetInformation());
        model.addAttribute("investmentInformation", new InvestmentInformation());
        model.addAttribute("menuItems", AssetType.values());

        model.addAttribute("bankInfoList", bankInformationService.getAllBankInformation(user));
        model.addAttribute("incomeInfoList", incomeInformationService.getAllIncomeInformation(user));
        model.addAttribute("assetInfoList", assetInformationService.getAllAssetInformation(user));
        model.addAttribute("investmentInfoList", investmentInformationService.getAllInvestmentInformation(user));
        return "upload";
    }

    @PostMapping("/add-bank-info")
    public String submitBankInfo(@ModelAttribute("bankInformation") BankInformation bankInformation,
                                 @RequestParam("image") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            AppFile appFile = storageService.uploadImageToFileSystem(file, AppFileType.BANK_STATEMENT);
            bankInformation.setFile(appFile);
            bankInformationService.save(bankInformation);
        }
        return "redirect:/upload";
    }

    @PostMapping("/add-income-info")
    public String submitIncomeInfo(@ModelAttribute("incomeInformation") IncomeInformation incomeInformation,
                                   @RequestParam("image") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            AppFile appFile = storageService.uploadImageToFileSystem(file, AppFileType.INCOME_STATEMENT);
            incomeInformation.setFile(appFile);
            incomeInformationService.save(incomeInformation);
        }
        return "redirect:/upload";
    }

    @PostMapping("/add-asset-info")
    public String submitAssetInfo(@ModelAttribute("assetInformation") AssetInformation assetInformation,
                                  @RequestParam("image") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            AppFile appFile = storageService.uploadImageToFileSystem(file, AppFileType.ASSET);
            assetInformation.setFile(appFile);
            assetInformationService.save(assetInformation);
        }
        return "redirect:/upload";
    }

    @PostMapping("/add-investment-info")
    public String submitInvestmentInfo(@ModelAttribute("investmentInformation") InvestmentInformation investmentInformation,
                                       @RequestParam("image") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            AppFile appFile = storageService.uploadImageToFileSystem(file, AppFileType.ASSET);
            investmentInformation.setFile(appFile);
            investmentInformationService.save(investmentInformation);
        }
        return "redirect:/upload";
    }

    @PostMapping("fileSystem")
    public ResponseEntity<?> uploadImageToFileSystem(@RequestParam("image") MultipartFile file) throws IOException {
        AppFile appFile = storageService.uploadImageToFileSystem(file, AppFileType.BANK_STATEMENT);
        return ResponseEntity.status(HttpStatus.OK)
                .body(appFile.getFileUrl());
    }

    @PostMapping("fileName")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
        byte[] image = storageService.downloadImageFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }
}
