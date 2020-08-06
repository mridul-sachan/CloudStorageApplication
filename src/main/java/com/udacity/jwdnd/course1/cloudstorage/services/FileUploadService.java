package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileUploadMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.FileEntity;
import org.apache.ibatis.annotations.Param;
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

    public int deleteFile(Integer fileId) { return fileUploadMapper.deleteUserFile(fileId); }

    public FileEntity getFilebyId(Integer fileId){ return fileUploadMapper.findFileById(fileId); }

    public boolean isDuplicateFileName(Integer userId, String fileName)
    {
        return fileUploadMapper.DuplicateFiles(userId, fileName) != null;
    }

}
