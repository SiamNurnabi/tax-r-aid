package com.example.taxraid.service;

import com.example.taxraid.entity.AppFile;
import com.example.taxraid.repository.StorageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class StorageServiceImpl implements StorageService {
    private final StorageRepository storageRepository;
    private final String FOLDER_PATH = "/Users/siam/Documents";

    public StorageServiceImpl(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public AppFile uploadImageToFileSystem(MultipartFile file) throws IOException {
        Path fileNameAndPath = Paths.get(FOLDER_PATH, file.getOriginalFilename());
        AppFile appFile = storageRepository.save(AppFile.builder()
                .fileName(file.getOriginalFilename())
                .fileSize(file.getSize())
                .fileType(file.getContentType())
                .fileUrl(fileNameAndPath.toString()).build());
        StringBuilder fileNames = new StringBuilder();
        fileNames.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());

        return appFile;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<AppFile> appFile = storageRepository.findByFileName(fileName);
        String fileUrl = appFile.get().getFileUrl();
        byte[] images = Files.readAllBytes(new File(fileUrl).toPath());
        return images;
    }
}
