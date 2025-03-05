package com.example.taxraid.entity;

import com.example.taxraid.enums.AppFileType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "app_file")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "file_name", nullable = false)
    private String fileName;

    @NotNull
    @Column(name = "file_url", nullable = false)
    private String fileUrl;

    @NotNull
    @Column(name = "file_size", nullable = false)
    private Long fileSize;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "file_type", nullable = false)
    private AppFileType fileType;
}
