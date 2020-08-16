package com.udacity.jwdnd.course1.cloudstorage.services.credential;

import com.udacity.jwdnd.course1.cloudstorage.domain.User;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.CredentialGateway;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.UserGateway;
import org.springframework.stereotype.Service;

@Service
public class DeleteCredentialService {

    private final CredentialGateway credentialGateway;
    private final UserGateway userGateway;

    public DeleteCredentialService(CredentialGateway credentialGateway, UserGateway userGateway) {
        this.credentialGateway = credentialGateway;
        this.userGateway = userGateway;
    }

    public void execute(final int credentialId, final String username){
        final int userId = userGateway.findUserByUserName(username)
                .map(User::getUserId)
                .orElseThrow(()->new RuntimeException("User not found !"));

        credentialGateway.deleteCredential(credentialId, userId);
    }

}
