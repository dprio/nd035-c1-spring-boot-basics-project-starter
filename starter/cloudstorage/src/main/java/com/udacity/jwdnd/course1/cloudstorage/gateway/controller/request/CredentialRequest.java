package com.udacity.jwdnd.course1.cloudstorage.gateway.controller.request;

import com.udacity.jwdnd.course1.cloudstorage.domain.Credential;

public class CredentialRequest {

    private String url;
    private String username;
    private String password;

    public CredentialRequest(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Credential toCredentialDomain(){
        return new Credential(
                null,
                this.url,
                this.username,
                null,
                this.password,
                null
        );
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
