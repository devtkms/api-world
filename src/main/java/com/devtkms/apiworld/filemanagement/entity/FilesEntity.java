package com.devtkms.apiworld.filemanagement.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FilesEntity {

    private Long fileId;

    @NotNull
    private String fileName;

    @NotNull
    private String fileType;

    @NotNull
    private Long fileSize;

    @NotNull
    private String fileUrl;
}
