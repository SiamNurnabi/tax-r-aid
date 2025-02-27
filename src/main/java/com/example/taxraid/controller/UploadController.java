package com.example.taxraid.controller;

import com.example.taxraid.entity.BankInformation;
import com.example.taxraid.service.BankInformationService;
import com.example.taxraid.service.StorageService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/upload")
public class UploadController {

    private final BankInformationService bankInformationService;
    private final StorageService storageService;

    public UploadController(BankInformationService bankInformationService,StorageService storageService) {
        this.bankInformationService = bankInformationService;
        this.storageService = storageService;
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

    @PostMapping("fileSystem")
    public ResponseEntity<?> uploadImageToFileSystem(@RequestParam("image") MultipartFile file) throws IOException {
        String uploadImage = storageService.uploadImageToFileSystem(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @PostMapping("fileName")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
        byte[] image = storageService.downloadImageFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }
}
