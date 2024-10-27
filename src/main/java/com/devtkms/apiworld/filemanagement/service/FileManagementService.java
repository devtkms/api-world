package com.devtkms.apiworld.filemanagement.service;

import com.devtkms.apiworld.filemanagement.dto.DeserializeJsonToObjectResponseDto;
import com.devtkms.apiworld.filemanagement.dto.RegisterFileResponseDto;
import com.devtkms.apiworld.filemanagement.dto.SerializeObjectToJsonRequestDto;
import org.springframework.web.multipart.MultipartFile;

public interface FileManagementService {

    /**
     * Registers a file by saving it to the specified directory and storing its metadata in the database.
     *
     * @param file The file to be registered.
     * @return A RegisterFileResponseDto containing details about the registered file.
     */
    RegisterFileResponseDto registerFile(MultipartFile file);

    /**
     * Asynchronously uploads a file by saving it to the specified directory.
     *
     * @param file The file to be uploaded.
     */
    void asyncUploadFile(MultipartFile file);

    /**
     * Serializes an object to a JSON string.
     *
     * @param requestDto The object to be serialized.
     * @return A JSON string representation of the object.
     */
    String serializeObjectToJson(SerializeObjectToJsonRequestDto requestDto);

    /**
     * Deserializes a JSON string to an object of the specified class.
     *
     * @param json The JSON string to be deserialized.
     * @param clazz The class of the object to be returned.
     * @return An instance of the specified class populated with data from the JSON string.
     */
    DeserializeJsonToObjectResponseDto deserializeJsonToObject(String json, Class<DeserializeJsonToObjectResponseDto> clazz);
}