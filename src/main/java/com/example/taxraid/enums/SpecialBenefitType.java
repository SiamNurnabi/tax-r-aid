package com.example.taxraid.enums;

public enum SpecialBenefitType {
    WAR_WOUNDED_FIGHTER("A gazette war-wounded freedom fighter"),
    Female("Female"),
    THIRD_GENDER("Third Gender"),
    DISABLE_PERSON("Disable Person"),
    AGED_65("Aged 65 years or more"),
    PARENT_WITH_DISABLE_CHILD("A parent of a person with disability");

    private final String value;

    SpecialBenefitType(String assetType) {
        this.value = assetType;
    }

    public String getValue() {
        return value;
    }
}
