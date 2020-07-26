package com.udacity.jwdnd.course1.cloudstorage.gateway.h2;

import com.udacity.jwdnd.course1.cloudstorage.domain.File;

import java.util.List;

public interface FileGateway {

    List<File> findFilesByUserId(int userId);

    int insertFile(File file);
}
