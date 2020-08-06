package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialsEntity;
import com.udacity.jwdnd.course1.cloudstorage.model.NotesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {

    @Autowired
    private CredentialMapper credentialMapper;

    public int createNewCredential(CredentialsEntity ce)
    {
        return credentialMapper.insertNewCredential(ce);
    }

    public List<CredentialsEntity> getAllCredentials(Integer userId){ return credentialMapper.findCredentialsByUserId(userId); }

    public int deletecredential(Integer credentialId){return credentialMapper.deleteUserCredential(credentialId);}

    public int updateCredential(CredentialsEntity ceUpdate){ return credentialMapper.updateCredential(ceUpdate); }
}
