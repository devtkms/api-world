package com.devtkms.apiworld.filemanagement.dto;

import lombok.Data;

@Data
public class RegisterFileResponseDto {

    private Long fileId;
    private String fileName;
    private String fileType;
    private Long fileSize;
    private String fileUrl;


}
