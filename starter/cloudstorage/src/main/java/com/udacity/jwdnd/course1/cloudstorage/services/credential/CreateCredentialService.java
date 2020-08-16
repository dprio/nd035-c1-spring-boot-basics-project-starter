package com.udacity.jwdnd.course1.cloudstorage.services.credential;

import com.udacity.jwdnd.course1.cloudstorage.domain.Credential;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.CredentialGateway;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.UserGateway;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import org.springframework.stereotype.Service;

@Service
public class CreateCredentialService {

    private final CredentialGateway credentialGateway;
    private final UserGateway userGateway;
    private final EncryptionService encryptionService;

    public CreateCredentialService(CredentialGateway credentialGateway, UserGateway userGateway, EncryptionService encryptionService) {
        this.credentialGateway = credentialGateway;
        this.userGateway = userGateway;
        this.encryptionService = encryptionService;
    }

    public Integer execute(final Credential credential, final String userName){
        return userGateway.findUserByUserName(userName)
                .map(user -> {
                    final String key = encryptionService.generateKey();
                    final String encryptedPass = encryptionService.encryptValue(credential.getPassword(), key);
                    final Credential newCredential = new Credential(
                            null,
                            credential.getUrl(),
                            credential.getUserName(),
                            key,
                            encryptedPass,
                            user.getUserId()
                    );
                    return credentialGateway.insertCredential(newCredential);
                }).orElseThrow(() -> new RuntimeException("User not found !"));
    }
}
