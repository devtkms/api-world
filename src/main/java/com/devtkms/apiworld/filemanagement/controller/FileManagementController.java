package com.devtkms.apiworld.filemanagement.controller;

import com.devtkms.apiworld.common.dto.ApiResponseDto;
import com.devtkms.apiworld.filemanagement.dto.RegisterFileResponseDto;
import com.devtkms.apiworld.filemanagement.service.FileManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
public class FileManagementController {

    @Autowired
    FileManagementService fileManagementService;

    /**
     * @param
     * @return
     */
    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponseDto<RegisterFileResponseDto>> registerFile(
            @RequestParam("file") MultipartFile file) {

        RegisterFileResponseDto result = fileManagementService.registerFile(file);
        ApiResponseDto<RegisterFileResponseDto> response = ApiResponseDto.success(result);
        return ResponseEntity.ok(response);
    }
}
