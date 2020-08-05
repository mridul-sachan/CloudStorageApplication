package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialsEntity;
import com.udacity.jwdnd.course1.cloudstorage.model.NotesEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Insert("INSERT INTO CREDENTIALS  (url, userName, password, `key`,userId ) VALUES(#{url}, #{userName}, #{password}, #{key}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insertNewCredential(CredentialsEntity newCredential);

    @Select("SELECT * FROM CREDENTIALS WHERE CREDENTIALS.userid = #{userId}")
    List<CredentialsEntity> findCredentialsByUserId(@Param("userId") Integer userId);

    @Delete("Delete FROM CREDENTIALS  WHERE credentialId = #{credentialId}")
    int deleteUserCredential(Integer credentialId);
}
