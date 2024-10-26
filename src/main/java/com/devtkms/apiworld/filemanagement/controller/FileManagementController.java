package com.devtkms.apiworld.filemanagement.controller;

import com.devtkms.apiworld.common.dto.ApiResponseDto;
import com.devtkms.apiworld.filemanagement.dto.DeserializeJsonToObjectResponseDto;
import com.devtkms.apiworld.filemanagement.dto.RegisterFileResponseDto;
import com.devtkms.apiworld.filemanagement.dto.SerializeObjectToJsonRequestDto;
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

//    @PostMapping(value = "/async-upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<ApiResponseDto<String>> asyncUploadFile(
//            @RequestParam("file") MultipartFile file) {
//        try {
//
//            fileManagementService.asyncUploadFile(file);
//            ApiResponseDto<String> response = ApiResponseDto.success("File uploaded successfully");
//            return ResponseEntity.ok(response);
//        } catch (IOException e) {
//            ApiResponseDto<String> response = ApiResponseDto.error("File upload failed: " + e.getMessage());
//            return ResponseEntity.status(500).body(response);
//        }
//    }


    @PostMapping(value = "/serialize", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDto<String>> serializeObject(@RequestBody SerializeObjectToJsonRequestDto serializeObjectToJsonRequestDto) {
        String jsonResponse = fileManagementService.serializeObjectToJson(serializeObjectToJsonRequestDto);
        ApiResponseDto<String> response = ApiResponseDto.success(jsonResponse);
        return ResponseEntity.ok(response);
    }


    @PostMapping(value = "/deserialize", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDto<DeserializeJsonToObjectResponseDto>> deserializeObject(@RequestBody String json) {
        DeserializeJsonToObjectResponseDto deserializeJsonToObjectResponseDto = fileManagementService.deserializeJsonToObject(json, DeserializeJsonToObjectResponseDto.class);
        ApiResponseDto<DeserializeJsonToObjectResponseDto> response = ApiResponseDto.success(deserializeJsonToObjectResponseDto);
        return ResponseEntity.ok(response);
    }
}
