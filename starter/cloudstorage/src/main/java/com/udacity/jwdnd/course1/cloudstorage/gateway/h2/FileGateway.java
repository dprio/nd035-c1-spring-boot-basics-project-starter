package com.udacity.jwdnd.course1.cloudstorage.gateway.h2;

import com.udacity.jwdnd.course1.cloudstorage.domain.File;

import java.util.List;
import java.util.Optional;

public interface FileGateway {

    List<File> findFilesByUserId(int userId);

    Optional<File> findFileByUserIdAndFileName(int userId, String fileName);

    int insertFile(File file);
}
