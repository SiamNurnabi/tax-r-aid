package com.example.taxraid.entity;

import com.example.taxraid.enums.ResidentialStatus;
import com.example.taxraid.enums.SpecialBenefitType;
import com.example.taxraid.enums.TaxPayerStatus;
import com.example.taxraid.util.PatternConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class ProfileDetails extends BaseEntity {

    @NotNull
    @Column(unique = true, name = "phone_no")
    @Pattern(regexp = PatternConstants.PHONE_PATTERN, message = "Phone no must be 11 digit")
    private String phoneNo;

    @NotNull
    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "nid")
    private String nid;

    @NotNull
    @Column(name = "tin")
    private String tin;

    @NotNull
    @Column(name = "circle")
    private String circle;

    @NotNull
    @Column(name = "taxes_zone")
    private String taxesZone;

    @NotNull
    @Column(name = "residential_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ResidentialStatus residentialStatus;

    @NotNull
    @Column(name = "taxpayer_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TaxPayerStatus taxPayerStatus;

    @NotNull
    @Column(name = "special_benefit_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private SpecialBenefitType specialBenefitType;

    @NotNull
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "spouse_name")
    private String spouseName;

    @Column(name = "spouse_tin")
    private String spouseTin;

    @Column(name = "employer_mae")
    private String employerName;

    @Column(name = "org_name")
    private String orgName;

    @Column(name = "org_bin")
    private String orgBin;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
