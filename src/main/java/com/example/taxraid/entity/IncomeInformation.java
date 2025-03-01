package com.example.taxraid.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class IncomeInformation extends BaseEntity {

    @NotNull
    @Column(name = "company_name")
    private String companyName;

    @NotNull
    @Column(name = "designation")
    private Integer designation;

    @NotNull
    @Column(name = "salary")
    private String salary;

    @NotNull
    @Column(name = "duration")
    private Double duration;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "file_id")
    private AppFile fileId;
}
