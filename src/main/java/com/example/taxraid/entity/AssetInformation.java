package com.example.taxraid.entity;

import com.example.taxraid.enums.AssetType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class AssetInformation extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "asset_type", nullable = false)
    private AssetType assetType;

    @NotNull
    @Column(name = "opening_balance")
    private double openingBalance;

    @NotNull
    @Column(name = "closing_balance")
    private double closingBalance;

    @Column(name = "remarks")
    private String remarks;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "file_id")
    private AppFile file;
}
