package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.UserGateway;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthorizationService implements AuthenticationProvider {

    private final UserGateway userGateway;
    private final HashService hashService;

    public AuthorizationService(UserGateway userGateway, HashService hashService) {
        this.userGateway = userGateway;
        this.hashService = hashService;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        final String userName = authentication.getName();
        final String password = authentication.getCredentials().toString();

        return userGateway.findUserByUserName(userName)
                .map(user -> {
                    final String salt = user.getSalt();
                    final String hashedPassword = hashService.getHashedValue(password, salt);

                    if(hashedPassword.equals(password)){
                        return new UsernamePasswordAuthenticationToken(userName, hashedPassword, new ArrayList<>());
                    }
                    return null;
                }).orElse(null);
    }

    @Override
    public boolean supports(final Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
