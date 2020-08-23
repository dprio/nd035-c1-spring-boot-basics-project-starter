package com.udacity.jwdnd.course1.cloudstorage.services.credential;

import com.udacity.jwdnd.course1.cloudstorage.domain.User;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.CredentialGateway;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.UserGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCredentialService {

    @Autowired
    private CredentialGateway credentialGateway;

    @Autowired
    private UserGateway userGateway;

    public void execute(final int credentialId, final String username){
        final int userId = userGateway.findUserByUserName(username)
                .map(User::getUserId)
                .orElseThrow(()->new RuntimeException("User not found !"));

        credentialGateway.deleteCredential(credentialId, userId);
    }

}
