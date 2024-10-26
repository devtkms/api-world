package com.devtkms.apiworld.filemanagement.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RegisterFileRequestDto {

    @NotNull
    private MultipartFile file;
}
