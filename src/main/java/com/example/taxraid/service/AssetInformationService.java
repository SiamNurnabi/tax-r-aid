package com.example.taxraid.service;

import com.example.taxraid.entity.AssetInformation;
import com.example.taxraid.entity.User;
import com.example.taxraid.repository.AssetInformationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetInformationService {

    private final AssetInformationRepository assetInformationRepository;

    public AssetInformationService(AssetInformationRepository assetInformationRepository) {
        this.assetInformationRepository = assetInformationRepository;
    }

    public AssetInformation save(AssetInformation assetInformation) {
        return assetInformationRepository.save(assetInformation);
    }

    public List<AssetInformation> getAllAssetInformation() {
        return assetInformationRepository.findAll();
    }

    public List<AssetInformation> getAllAssetInformation(User user) {
        return assetInformationRepository.findAllByCreatedBy(user);
    }
}
