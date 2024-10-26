package com.devtkms.apiworld.filemanagement.repository;

import com.devtkms.apiworld.filemanagement.dao.FilesDao;
import com.devtkms.apiworld.filemanagement.entity.FilesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FileManagementRepository {

    @Autowired
    private FilesDao filesDao;

    /**
     *
     *
     * @param
     */
    public void insertFile(FilesEntity filesEntity) {
        filesDao.insertFile(filesEntity);
    }
}
