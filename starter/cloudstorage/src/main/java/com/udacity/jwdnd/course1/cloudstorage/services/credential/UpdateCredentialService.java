package com.udacity.jwdnd.course1.cloudstorage.services.credential;

import com.udacity.jwdnd.course1.cloudstorage.domain.Credential;
import com.udacity.jwdnd.course1.cloudstorage.domain.User;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.CredentialGateway;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.UserGateway;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCredentialService {

    @Autowired
    private CredentialGateway credentialGateway;

    @Autowired
    private UserGateway userGateway;

    @Autowired
    private EncryptionService encryptionService;


    public void execute(final Credential credential, final String userName){
        final int userId = userGateway.findUserByUserName(userName)
                .map(User::getUserId)
                .orElseThrow(() -> new RuntimeException("User not found !"));

        final String key = encryptionService.generateKey();
        final String encryptedPass = encryptionService.encryptValue(credential.getPassword(), key);
        final Credential newCredential = new Credential(
                credential.getCredentialId(),
                credential.getUrl(),
                credential.getUserName(),
                key,
                encryptedPass,
                userId
        );

        credentialGateway.updateCredential(newCredential);
    }
}
