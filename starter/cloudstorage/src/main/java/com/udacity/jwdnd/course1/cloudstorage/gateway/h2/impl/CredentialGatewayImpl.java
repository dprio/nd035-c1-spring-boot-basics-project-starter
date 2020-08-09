package com.udacity.jwdnd.course1.cloudstorage.gateway.h2.impl;

import com.udacity.jwdnd.course1.cloudstorage.domain.Credential;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.CredentialGateway;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.repositories.CredencialRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CredentialGatewayImpl implements CredentialGateway {

    private final CredencialRepository credencialRepository;

    public CredentialGatewayImpl(CredencialRepository credencialRepository) {
        this.credencialRepository = credencialRepository;
    }

    @Override
    public List<Credential> findCredentialsByUserId(int userId) {
        return credencialRepository.findCredentialsByUserId(userId);
    }

    @Override
    public int insertCredential(final Credential credential) {
        return credencialRepository.insertCredential(credential);
    }
}
