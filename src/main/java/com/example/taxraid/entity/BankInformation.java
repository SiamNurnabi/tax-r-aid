package com.example.taxraid.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class BankInformation extends BaseEntity {

    @NotNull
    @Column(name = "bank_name")
    private String bankName;

    @NotNull
    @Column(name = "acc_no")
    private Integer accNo;

    @NotNull
    @Column(name = "acc_type")
    private String accType;

    @NotNull
    @Column(name = "opening_balance")
    private Double openingBalance;

    @NotNull
    @Column(name = "interest")
    private Double interest;

    @NotNull
    @Column(name = "tds")
    private Double tds;

    @NotNull
    @Column(name = "balance")
    private Double balance;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "file_id")
    private AppFile file;
}
