package com.udacity.jwdnd.course1.cloudstorage.domain;

public class Credential {

    private Integer credencialId;
    private String url;
    private String userName;
    private String key;
    private String password;
    private Integer userId;


    public Credential(Integer credencialId, String url, String userName, String key, String password, Integer userId) {
        this.credencialId = credencialId;
        this.url = url;
        this.userName = userName;
        this.key = key;
        this.password = password;
        this.userId = userId;
    }

    public Integer getCredencialId() {
        return credencialId;
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
