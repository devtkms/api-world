package com.devtkms.apiworld.filemanagement.dao;

import com.devtkms.apiworld.filemanagement.entity.FilesEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FilesDao {
    /**
     * Inserts a file entity into the database.
     *
     * @param filesEntity The FilesEntity object containing the file metadata to be inserted.
     */
    void insertFile(FilesEntity filesEntity);
}