package com.udacity.jwdnd.course1.cloudstorage.domain;

public class Credential {

    private Integer credentialId;
    private String url;
    private String userName;
    private String key;
    private String password;
    private Integer userId;


    public Credential(Integer credentialId, String url, String userName, String key, String password, Integer userId) {
        this.credentialId = credentialId;
        this.url = url;
        this.userName = userName;
        this.key = key;
        this.password = password;
        this.userId = userId;
    }

    public Integer getCredentialId() {
        return credentialId;
    }

    public String getUrl() {
        return url;
    }

    public String getUserName() {
        return userName;
    }

    public String getKey() {
        return key;
    }

    public String getPassword() {
        return password;
    }

    public Integer getUserId() {
        return userId;
    }
}
