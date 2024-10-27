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
     * Inserts a file entity into the database using the FilesDao.
     *
     * @param filesEntity The FilesEntity object containing the file metadata to be inserted.
     */
    public void insertFile(FilesEntity filesEntity) {
        filesDao.insertFile(filesEntity);
    }
}