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
     * Registers a file by accepting a multipart file and saving it.
     *
     * @param file The file to be registered.
     * @return A ResponseEntity containing the API response with file registration details.
     */
    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponseDto<RegisterFileResponseDto>> registerFile(
            @RequestParam("file") MultipartFile file) {

        RegisterFileResponseDto result = fileManagementService.registerFile(file);
        ApiResponseDto<RegisterFileResponseDto> response = ApiResponseDto.success(result);
        return ResponseEntity.ok(response);
    }

    /**
     * Asynchronously uploads a file by accepting a multipart file.
     *
     * @param file The file to be uploaded.
     * @return A ResponseEntity containing a success message.
     */
    @PostMapping(value = "/async", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponseDto<String>> asyncUploadFile(@RequestParam("file") MultipartFile file) {
        fileManagementService.asyncUploadFile(file);
        ApiResponseDto<String> response = ApiResponseDto.success("File uploaded successfully");
        return ResponseEntity.ok(response);
    }

    /**
     * Serializes an object to a JSON string.
     *
     * @param serializeObjectToJsonRequestDto The object to be serialized.
     * @return A ResponseEntity containing the JSON representation of the object.
     */
    @PostMapping(value = "/serialize", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDto<String>> serializeObject(@RequestBody SerializeObjectToJsonRequestDto serializeObjectToJsonRequestDto) {
        String jsonResponse = fileManagementService.serializeObjectToJson(serializeObjectToJsonRequestDto);
        ApiResponseDto<String> response = ApiResponseDto.success(jsonResponse);
        return ResponseEntity.ok(response);
    }

    /**
     * Deserializes a JSON string to an object of the specified class.
     *
     * @param json The JSON string to be deserialized.
     * @return A ResponseEntity containing the deserialized object.
     */
    @PostMapping(value = "/deserialize", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseDto<DeserializeJsonToObjectResponseDto>> deserializeObject(@RequestBody String json) {
        DeserializeJsonToObjectResponseDto deserializeJsonToObjectResponseDto = fileManagementService.deserializeJsonToObject(json, DeserializeJsonToObjectResponseDto.class);
        ApiResponseDto<DeserializeJsonToObjectResponseDto> response = ApiResponseDto.success(deserializeJsonToObjectResponseDto);
        return ResponseEntity.ok(response);
    }
}