package com.example.taxraid.enums;

public enum ResidentialStatus {
    RESIDENT("Resident"),
    NON_RESIDENT("Non-resident");

    private final String value;

    ResidentialStatus(String assetType) {
        this.value = assetType;
    }

    public String getValue() {
        return value;
    }
}
