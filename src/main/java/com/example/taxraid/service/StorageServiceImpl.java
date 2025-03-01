package com.example.taxraid.service;

import com.example.taxraid.entity.AppFile;
import com.example.taxraid.enums.AppFileType;
import com.example.taxraid.repository.StorageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Optional;

@Service
public class StorageServiceImpl implements StorageService {
    private final StorageRepository storageRepository;
    private final String FOLDER_PATH = "uploaded-files";

    public StorageServiceImpl(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public AppFile uploadImageToFileSystem(MultipartFile file, AppFileType appFileType) throws IOException {
        Path uploadPath = Paths.get(FOLDER_PATH + "/" + appFileType);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        InputStream inputStream = file.getInputStream();
        Path filePath = uploadPath.resolve(Objects.requireNonNull(file.getOriginalFilename()));
        AppFile appFile = storageRepository.save(AppFile.builder()
                .fileName(file.getOriginalFilename())
                .fileSize(file.getSize())
                .fileType(appFileType)
                .fileUrl(filePath.toString()).build());
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        return appFile;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<AppFile> appFile = storageRepository.findByFileName(fileName);
        String fileUrl = appFile.get().getFileUrl();
        byte[] images = Files.readAllBytes(new File(fileUrl).toPath());
        return images;
    }
}
