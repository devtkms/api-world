package com.devtkms.apiworld.filemanagement.service;

import com.devtkms.apiworld.filemanagement.dto.RegisterFileResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface FileManagementService {

    /**
     *
     *
     * @param
     * @return
     */
    RegisterFileResponseDto registerFile(MultipartFile file);
}
