package com.devtkms.apiworld.filemanagement.service.impl;

import com.devtkms.apiworld.filemanagement.dto.DeserializeJsonToObjectResponseDto;
import com.devtkms.apiworld.filemanagement.dto.RegisterFileResponseDto;
import com.devtkms.apiworld.filemanagement.dto.SerializeObjectToJsonRequestDto;
import com.devtkms.apiworld.filemanagement.entity.FilesEntity;
import com.devtkms.apiworld.filemanagement.repository.FileManagementRepository;
import com.devtkms.apiworld.filemanagement.service.FileManagementService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileManagementServiceImpl implements FileManagementService {

    private final ObjectMapper objectMapper;

    public FileManagementServiceImpl() {
        this.objectMapper = new ObjectMapper();
    }

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

    @Override
    public String serializeObjectToJson(SerializeObjectToJsonRequestDto requestDto) {
        try {
            return objectMapper.writeValueAsString(requestDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Serialization failed", e);
        }
    }

    @Override
    public DeserializeJsonToObjectResponseDto deserializeJsonToObject(String json, Class<DeserializeJsonToObjectResponseDto> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException("Deserialization failed", e);
        }
    }
}