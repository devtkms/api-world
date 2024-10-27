package com.devtkms.apiworld.filemanagement.service.impl;

import com.devtkms.apiworld.filemanagement.dto.DeserializeJsonToObjectResponseDto;
import com.devtkms.apiworld.filemanagement.dto.RegisterFileResponseDto;
import com.devtkms.apiworld.filemanagement.dto.SerializeObjectToJsonRequestDto;
import com.devtkms.apiworld.filemanagement.entity.FilesEntity;
import com.devtkms.apiworld.common.exception.FileUploadException;
import com.devtkms.apiworld.filemanagement.repository.FileManagementRepository;
import com.devtkms.apiworld.filemanagement.service.FileManagementService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
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

    /**
     * Registers a file by saving it to the specified directory and storing its metadata in the database.
     *
     * @param file The file to be registered.
     * @return A RegisterFileResponseDto containing details about the registered file.
     * @throws FileUploadException if the file is empty or an error occurs during file storage.
     */
    @Override
    @Transactional
    public RegisterFileResponseDto registerFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new FileUploadException("File is empty");
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
                    throw new FileUploadException("Failed to create directory: " + uploadDir);
                }
            }

            File destinationFile = new File(uploadDir + "/" + file.getOriginalFilename());
            file.transferTo(destinationFile);
            filesEntity.setFileUrl(destinationFile.getAbsolutePath());
        } catch (IOException e) {
            throw new FileUploadException("Failed to store file: " + e.getMessage());
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

    /**
     * Asynchronously uploads a file by saving it to the specified directory.
     *
     * @param file The file to be uploaded.
     * @throws FileUploadException if the file is empty or an error occurs during file upload.
     */
    @Override
    @Async
    public void asyncUploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new FileUploadException("File is empty");
        }

        try {
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                boolean isDirectoryCreated = directory.mkdirs();
                if (!isDirectoryCreated) {
                    throw new FileUploadException("Failed to create directory: " + uploadDir);
                }
            }

            File destinationFile = new File(uploadDir + "/" + file.getOriginalFilename());
            file.transferTo(destinationFile);
            System.out.println("File uploaded: " + destinationFile.getAbsolutePath());
        } catch (IOException e) {
            throw new FileUploadException("File upload failed: " + e.getMessage());
        }
    }

    /**
     * Serializes an object to a JSON string.
     *
     * @param requestDto The object to be serialized.
     * @return A JSON string representation of the object.
     * @throws RuntimeException if serialization fails.
     */
    @Override
    public String serializeObjectToJson(SerializeObjectToJsonRequestDto requestDto) {
        try {
            return objectMapper.writeValueAsString(requestDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Serialization failed", e);
        }
    }

    /**
     * Deserializes a JSON string to an object of the specified class.
     *
     * @param json The JSON string to be deserialized.
     * @param clazz The class of the object to be returned.
     * @return An instance of the specified class populated with data from the JSON string.
     * @throws RuntimeException if deserialization fails.
     */
    @Override
    public DeserializeJsonToObjectResponseDto deserializeJsonToObject(String json, Class<DeserializeJsonToObjectResponseDto> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException("Deserialization failed", e);
        }
    }
}