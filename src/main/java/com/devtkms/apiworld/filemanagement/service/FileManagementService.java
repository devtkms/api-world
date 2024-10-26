package com.devtkms.apiworld.filemanagement.service;

import com.devtkms.apiworld.filemanagement.dto.DeserializeJsonToObjectResponseDto;
import com.devtkms.apiworld.filemanagement.dto.RegisterFileResponseDto;
import com.devtkms.apiworld.filemanagement.dto.SerializeObjectToJsonRequestDto;
import org.springframework.web.multipart.MultipartFile;

public interface FileManagementService {

    /**
     *
     *
     * @param
     * @return
     */
    RegisterFileResponseDto registerFile(MultipartFile file);

    /**
     *
     *
     * @param
     * @return
     */
    String serializeObjectToJson(SerializeObjectToJsonRequestDto requestDto);


    /**
     *
     *
     * @param
     * @return
     */
    DeserializeJsonToObjectResponseDto deserializeJsonToObject(String json, Class<DeserializeJsonToObjectResponseDto> clazz);
}
