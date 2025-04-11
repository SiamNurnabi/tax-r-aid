package com.example.taxraid.enums;

public enum AssetType {
    FIXED("Fixed"),
    FINANCIAL("Financial");

    private final String value;

    AssetType(String assetType) {
        this.value = assetType;
    }

    public String getValue() {
        return value;
    }
}
