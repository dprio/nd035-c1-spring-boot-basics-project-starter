package com.udacity.jwdnd.course1.cloudstorage.gateway.h2;

import com.udacity.jwdnd.course1.cloudstorage.domain.User;

import java.util.Optional;

public interface UserGateway {

    Optional<User> findUserByUserName(String userName);

    int insertUser(User user);

}
