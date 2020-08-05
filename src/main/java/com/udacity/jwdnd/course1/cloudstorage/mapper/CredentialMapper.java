package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialsEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface CredentialMapper {

    @Insert("INSERT INTO CREDENTIALS  (url, userName, password, `key`,userId ) VALUES(#{url}, #{userName}, #{password}, #{key}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insertNewCredential(CredentialsEntity newCredential);
}
