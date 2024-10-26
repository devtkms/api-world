package com.devtkms.apiworld.filemanagement.dao;

import com.devtkms.apiworld.filemanagement.entity.FilesEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FilesDao {
    /**
     *
     *
     * @param
     */
    void insertFile(FilesEntity filesEntity);
}
