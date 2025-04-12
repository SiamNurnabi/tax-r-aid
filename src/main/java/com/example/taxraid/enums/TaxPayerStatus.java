package com.example.taxraid.enums;

public enum TaxPayerStatus {
    INDIVIDUAL("Individual"),
    FIRM("Firm"),
    HINDU_UNDIVIDED_FAMILY("Hindo Undivided Family"),
    OTHERS("Others");

    private final String value;

    TaxPayerStatus(String assetType) {
        this.value = assetType;
    }

    public String getValue() {
        return value;
    }
}
