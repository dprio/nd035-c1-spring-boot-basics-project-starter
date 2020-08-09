package com.udacity.jwdnd.course1.cloudstorage.services.credential;

import com.udacity.jwdnd.course1.cloudstorage.domain.Credential;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.CredentialGateway;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.UserGateway;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class FindCredentialsService {

    private final CredentialGateway credentialGateway;
    private final UserGateway userGateway;

    public FindCredentialsService(CredentialGateway credentialGateway, UserGateway userGateway) {
        this.credentialGateway = credentialGateway;
        this.userGateway = userGateway;
    }

    public List<Credential> execute(final String userName){
        return userGateway.findUserByUserName(userName)
                .map(user -> credentialGateway.findCredentialsByUserId(user.getUserId()))
                .orElse(Collections.emptyList());

    }
}
