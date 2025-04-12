package com.example.taxraid.entity;

import com.example.taxraid.enums.InvestmentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class InvestmentInformation extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "investment_type", nullable = false, columnDefinition = "varchar(255)")
    private InvestmentType investmentType;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "account_number")
    private String accountNumber;

    @NotNull
    @Column(name = "opening_balance")
    private Double openingBalance;

    @NotNull
    @Column(name = "balance")
    private Double balance;

    @NotNull
    @Column(name = "tds")
    private Double tds;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "file_id")
    private AppFile file;
}
