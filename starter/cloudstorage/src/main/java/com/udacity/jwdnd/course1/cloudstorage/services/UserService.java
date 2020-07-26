package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.domain.User;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.UserGateway;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserGateway userGateway;
    private final HashService hashService;

    public UserService(UserGateway userGateway, HashService hashService) {
        this.userGateway = userGateway;
        this.hashService = hashService;
    }

    public int createUser (final User user){
        this.validateUser(user);

        final String salt = hashService.generateSalt();
        final String password = hashService.getHashedValue(user.getPassword(), salt);

        final User newUser = new User(
                null,
                user.getUserName(),
                salt,
                password,
                user.getFirstName(),
                user.getLastName()
        );

        return userGateway.insertUser(newUser);
    }

    private void validateUser(final User user){
        userGateway.findUserByUserName(user.getUserName())
                .ifPresent((it) -> {throw new RuntimeException("User already exists");});
    }
}
