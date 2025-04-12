package com.example.taxraid.enums;

public enum InvestmentType {
    LIFE_INSURANCE("Life Insurance"),
    SANCHAYPATRA("Sanchaypatra"),
    DPS("DPS"),
    FDR("FRD"),
    PROVIDENT_FUND("Provident Fund");

    private final String value;

    InvestmentType(String type) {
        this.value = type;
    }

    public String getValue() {
        return value;
    }
    }
