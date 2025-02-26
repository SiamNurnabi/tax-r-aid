package com.example.taxraid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/upload")
public class UploadController {

    @GetMapping
    public String showUploadPage() {
        return "upload";
    }
}
