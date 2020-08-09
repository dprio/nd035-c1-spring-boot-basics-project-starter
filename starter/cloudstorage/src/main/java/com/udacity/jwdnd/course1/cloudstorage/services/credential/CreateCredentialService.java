package com.udacity.jwdnd.course1.cloudstorage.services.credential;

import com.udacity.jwdnd.course1.cloudstorage.domain.Credential;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.CredentialGateway;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.UserGateway;
import org.springframework.stereotype.Service;

@Service
public class CreateCredentialService {

    private final CredentialGateway credentialGateway;
    private final UserGateway userGateway;

    public CreateCredentialService(CredentialGateway credentialGateway, UserGateway userGateway) {
        this.credentialGateway = credentialGateway;
        this.userGateway = userGateway;
    }

    public Integer execute(final Credential credential, final String userName){
        return userGateway.findUserByUserName(userName)
                .map(user -> {
                    final Credential newCredential = new Credential(
                            null,
                            credential.getUrl(),
                            credential.getUserName(),
                            null,
                            credential.getPassword(),
                            user.getUserId()
                    );
                    return credentialGateway.insertCredential(newCredential);
                }).orElseThrow(() -> new RuntimeException("User not found !"));
    }
}
