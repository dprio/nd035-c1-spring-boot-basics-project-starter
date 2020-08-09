package com.udacity.jwdnd.course1.cloudstorage.gateway.controller.request;

import com.udacity.jwdnd.course1.cloudstorage.domain.Credential;

public class CredentialRequest {

    private String url;
    private String userName;
    private String password;

    public CredentialRequest(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    public Credential toCredentialDomain(){
        return new Credential(
                null,
                this.url,
                this.userName,
                null,
                this.password,
                null
        );
    }
}
