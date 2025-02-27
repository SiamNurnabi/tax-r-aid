package com.example.taxraid.service;

import com.example.taxraid.entity.AppFile;
import com.example.taxraid.repository.StorageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class StorageServiceImpl implements StorageService {
    private final StorageRepository storageRepository;
    private final String FOLDER_PATH = "/home/";

    public StorageServiceImpl(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath = FOLDER_PATH + file.getOriginalFilename();
        file.transferTo(new File(filePath));
        AppFile appFile = storageRepository.save(AppFile.builder()
                .fileName(file.getOriginalFilename())
                .fileSize(file.getSize())
                .fileType(file.getContentType())
                .fileUrl(filePath).build());

        return "Successfully uploaded file" + appFile.getFileName();
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<AppFile> appFile = storageRepository.findByFileName(fileName);
        String fileUrl = appFile.get().getFileUrl();
        byte[] images = Files.readAllBytes(new File(fileUrl).toPath());
        return images;
    }
}
