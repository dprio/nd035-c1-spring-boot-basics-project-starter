package com.udacity.jwdnd.course1.cloudstorage.gateway.h2.impl;

import com.udacity.jwdnd.course1.cloudstorage.domain.User;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.UserGateway;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserGatewayImpl implements UserGateway {

    private final UserRepository userRepository;

    public UserGatewayImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findUserByUserName(final String userName) {
        return Optional.ofNullable(userRepository.findUserByUserName(userName));
    }

    @Override
    public int insertUser(final User user) {
        return userRepository.insertUser(user);
    }
}
