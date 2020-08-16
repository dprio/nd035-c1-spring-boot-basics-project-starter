package com.udacity.jwdnd.course1.cloudstorage.gateway.controller.request;

import com.udacity.jwdnd.course1.cloudstorage.domain.Credential;

public class CredentialRequest {

    private Integer credentialId;
    private String url;
    private String username;
    private String password;
    private String decryptedPassword;

    public CredentialRequest(Integer credentialId, String url, String username, String password, String decryptedPassword) {
        this.credentialId = credentialId;
        this.url = url;
        this.username = username;
        this.password = password;
        this.decryptedPassword = decryptedPassword;
    }

    public Credential toCredentialDomain(){
        return new Credential(
                this.credentialId,
                this.url,
                this.username,
                null,
                this.password,
                null
        );
    }

    public Integer getCredentialId() {
        return credentialId;
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

    public String getDecryptedPassword() {
        return decryptedPassword;
    }
}
