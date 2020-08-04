package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.FileEntity;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileUploadMapper {

    @Select("SELECT * FROM FILES  WHERE userId = #{userId}")
    User getUserFiles(Integer userId);

    @Insert("INSERT INTO FILES  (filename, contenttype, filesize, userid, filedata) VALUES(#{fileName}, #{contentType},#{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFile(FileEntity uploadedFile);


    @Select("SELECT * FROM FILES WHERE files.userid = #{userId}")
    List<FileEntity> findFilesByUserId(@Param("userId") Integer userId);

    @Delete("Delete FROM FILES  WHERE fileId = #{fileId}")
    int deleteUserFile(Integer userId);

    @Select("SELECT * FROM FILES WHERE files.fileid = #{fileId}")
    FileEntity findFileById(@Param("fileId") Integer fileId);
}
