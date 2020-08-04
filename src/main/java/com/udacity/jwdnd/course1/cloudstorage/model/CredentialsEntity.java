package com.udacity.jwdnd.course1.cloudstorage.model;

public class CredentialsEntity {

    private Integer credentialId;
    private String key;
    private String  password;
    private String url;
    private Integer Id;
    private String userName;

    public CredentialsEntity(Integer credentialId, String key, String password, String url, Integer id, String userName) {
        this.credentialId = credentialId;
        this.key = key;
        this.password = password;
        this.url = url;
        Id = id;
        this.userName = userName;
    }

    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}