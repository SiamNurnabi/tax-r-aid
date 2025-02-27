package com.example.taxraid.entity;

import jakarta.persistence.*;
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

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_url", nullable = false)
    private String fileUrl;

    @Column(name = "file_size", nullable = false)
    private Long fileSize;

    @Column(name = "file_type", nullable = false)
    private String fileType;
}
