package com.udacity.jwdnd.course1.cloudstorage.gateway;

import com.udacity.jwdnd.course1.cloudstorage.domain.Credential;
import com.udacity.jwdnd.course1.cloudstorage.gateway.controller.request.CredentialRequest;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CredentialRequestConverter implements Converter <List<Credential>, List<CredentialRequest>>{

    private final EncryptionService encryptionService;

    public CredentialRequestConverter(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    private CredentialRequest buildCredentialRequest(final Credential credential){
        return new CredentialRequest(
                credential.getCredentialId(),
                credential.getUrl(),
                credential.getUserName(),
                credential.getPassword(),
                encryptionService.decryptValue(credential.getPassword(), credential.getKey())
        );
    }

    @Override
    public List<CredentialRequest> convert(final List<Credential> credential) {
        return credential.stream()
                .map(this::buildCredentialRequest)
                .collect(Collectors.toList());
    }
}
