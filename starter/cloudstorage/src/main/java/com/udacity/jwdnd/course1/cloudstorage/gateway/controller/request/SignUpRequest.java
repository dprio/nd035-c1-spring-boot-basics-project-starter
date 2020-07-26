package com.udacity.jwdnd.course1.cloudstorage.gateway.controller.request;

import com.udacity.jwdnd.course1.cloudstorage.domain.User;

public class SignUpRequest {

    private String firstName;
    private String lastName;
    private String userName;
    private String password;

    public SignUpRequest(String firstName, String lastName, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    public User toUserDomain(){
        return new User(
                null,
                this.userName,
                null,
                this.password,
                this.firstName,
                this.lastName
        );
    }
}
