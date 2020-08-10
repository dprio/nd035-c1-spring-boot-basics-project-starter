package com.udacity.jwdnd.course1.cloudstorage.services.file;

import com.udacity.jwdnd.course1.cloudstorage.domain.File;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.FileGateway;
import org.springframework.stereotype.Service;

@Service
public class DownloadFileService {

    private final FileGateway fileGateway;

    public DownloadFileService(FileGateway fileGateway) {
        this.fileGateway = fileGateway;
    }

    public File execute(final Integer fileId){
        return fileGateway.findFileById(fileId)
                .orElseThrow(() -> new RuntimeException("File not found !"));
    }
}
