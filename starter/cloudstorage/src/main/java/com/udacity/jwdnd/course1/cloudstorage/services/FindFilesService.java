package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.domain.File;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.FileGateway;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.UserGateway;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class FindFilesService {

    private final FileGateway fileGateway;
    private final UserGateway userGateway;

    public FindFilesService(FileGateway fileGateway, UserGateway userGateway) {
        this.fileGateway = fileGateway;
        this.userGateway = userGateway;
    }

    public List<File> execute(final String userName){
        return userGateway.findUserByUserName(userName)
                .map(user -> fileGateway.findFilesByUserId(user.getUserId()))
                .orElse(Collections.emptyList());
    }
}
