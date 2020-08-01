package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileUploadMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.FileEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileUploadService {


    private FileUploadMapper fileUploadMapper;

    public FileUploadService( FileUploadMapper fileUploadMapper) {
        this.fileUploadMapper = fileUploadMapper;
    }

    public int addFile(FileEntity fileEntity)
   {
       return fileUploadMapper.insertFile(fileEntity);
   }

    public List<FileEntity> getAllFiles(Integer userId) {
        return fileUploadMapper.findFilesByUserId(userId);
    }

    public int deleteFile(String fileNameToDelete, String username) {
        Integer fileId = fileUploadMapper.getFileId(fileNameToDelete, username);
        return fileUploadMapper.deleteUserFile(fileId); }

}
