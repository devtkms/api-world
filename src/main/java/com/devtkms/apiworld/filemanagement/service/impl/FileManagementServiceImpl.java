package com.devtkms.apiworld.filemanagement.service.impl;

import com.devtkms.apiworld.filemanagement.dto.RegisterFileResponseDto;
import com.devtkms.apiworld.filemanagement.entity.FilesEntity;
import com.devtkms.apiworld.filemanagement.repository.FileManagementRepository;
import com.devtkms.apiworld.filemanagement.service.FileManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileManagementServiceImpl implements FileManagementService {

    @Autowired
    private FileManagementRepository fileManagementRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    @Transactional
    public RegisterFileResponseDto registerFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        FilesEntity filesEntity = new FilesEntity();
        filesEntity.setFileName(file.getOriginalFilename());
        filesEntity.setFileType(file.getContentType());
        filesEntity.setFileSize(file.getSize());

        try {
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                boolean isDirectoryCreated = directory.mkdirs();
                if (!isDirectoryCreated) {
                    throw new RuntimeException("Failed to create directory: " + uploadDir);
                }
            }

            File destinationFile = new File(uploadDir + "/" + file.getOriginalFilename());
            file.transferTo(destinationFile);
            filesEntity.setFileUrl(destinationFile.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file: " + e.getMessage(), e);
        }

        fileManagementRepository.insertFile(filesEntity);
        RegisterFileResponseDto registerFileResponseDto = new RegisterFileResponseDto();
        registerFileResponseDto.setFileId(filesEntity.getFileId());
        registerFileResponseDto.setFileName(filesEntity.getFileName());
        registerFileResponseDto.setFileType(filesEntity.getFileType());
        registerFileResponseDto.setFileSize(filesEntity.getFileSize());
        registerFileResponseDto.setFileUrl(filesEntity.getFileUrl());

        return registerFileResponseDto;
    }
}