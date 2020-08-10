package com.udacity.jwdnd.course1.cloudstorage.services.file;

import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.FileGateway;
import org.springframework.stereotype.Service;

@Service
public class DeleteFileService {

    private final FileGateway fileGateway;

    public DeleteFileService(FileGateway fileGateway) {
        this.fileGateway = fileGateway;
    }

    public void execute(final Integer fileId){
        fileGateway.deleteFileById(fileId);
    }
}
