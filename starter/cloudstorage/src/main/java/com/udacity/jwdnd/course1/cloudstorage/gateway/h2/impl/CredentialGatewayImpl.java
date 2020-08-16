package com.udacity.jwdnd.course1.cloudstorage.gateway.h2.impl;

import com.udacity.jwdnd.course1.cloudstorage.domain.Credential;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.CredentialGateway;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.repositories.CredentialRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CredentialGatewayImpl implements CredentialGateway {

    private final CredentialRepository credentialRepository;

    public CredentialGatewayImpl(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    @Override
    public List<Credential> findCredentialsByUserId(int userId) {
        return credentialRepository.findCredentialsByUserId(userId);
    }

    @Override
    public int insertCredential(final Credential credential) {
        return credentialRepository.insertCredential(credential);
    }

    @Override
    public void updateCredential(final Credential credential) {
        credentialRepository.updateCredential(credential);
    }
}
