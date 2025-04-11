package com.example.taxraid.entity;

import com.example.taxraid.util.PatternConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

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

}
