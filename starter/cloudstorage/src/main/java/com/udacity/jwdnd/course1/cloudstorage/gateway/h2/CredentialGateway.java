package com.udacity.jwdnd.course1.cloudstorage.gateway.h2;

import com.udacity.jwdnd.course1.cloudstorage.domain.Credential;

import java.util.List;

public interface CredentialGateway {

    List<Credential> findCredentialsByUserId(int userId);

    int insertCredential(Credential credential);
}
