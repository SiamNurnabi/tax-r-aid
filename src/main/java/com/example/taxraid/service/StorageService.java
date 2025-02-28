package com.example.taxraid.service;

import com.example.taxraid.entity.AppFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {
    AppFile uploadImageToFileSystem(MultipartFile file) throws IOException;

    byte[] downloadImageFromFileSystem(String fileName) throws IOException;

}
