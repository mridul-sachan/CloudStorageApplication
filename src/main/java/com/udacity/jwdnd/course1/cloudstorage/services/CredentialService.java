package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    @Autowired
    private CredentialMapper credentialMapper;
    @Autowired
    private EncryptionService encryptionService;

    public int createNewCredential(CredentialsEntity ce)
    {
        SecureRandom random = new SecureRandom();
        byte salt [] = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        ce.setKey(encodedSalt);

        String encryptedPassword = encryptionService.encryptValue(ce.getPassword(),encodedSalt);
        ce.setPassword(encryptedPassword);

        return credentialMapper.insertNewCredential(ce);
    }

    public List<CredentialsEntity> getAllCredentials(Integer userId){ return credentialMapper.findCredentialsByUserId(userId); }

    public int deletecredential(Integer credentialId){return credentialMapper.deleteUserCredential(credentialId);}

    public int updateCredential(CredentialsEntity ceUpdate){
        String encodedSalt = credentialMapper.findKey(ceUpdate.getCredentialId());
        String encryptedPassword = encryptionService.encryptValue(ceUpdate.getPassword(),encodedSalt);
        ceUpdate.setPassword(encryptedPassword);

        return credentialMapper.updateCredential(ceUpdate);
    }

    public CredentialsEntity decodePassword(Integer credentialId){
        return credentialMapper.findByCredentialId(credentialId);
    }

}
