package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.domain.File;
import com.udacity.jwdnd.course1.cloudstorage.domain.User;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.FileGateway;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.UserGateway;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UploadFileService {

    private final FileGateway fileGateway;
    private final UserGateway userGateway;

    public UploadFileService(FileGateway fileGateway, UserGateway userGateway) {
        this.fileGateway = fileGateway;
        this.userGateway = userGateway;
    }

    public void execute(final MultipartFile multipartFile, final String userName) throws IOException {
        final Integer userId = userGateway.findUserByUserName(userName)
                .map(User::getUserId)
                .orElse(null);

        this.validateFile(multipartFile, userId);

        final File file = new File(
                null,
                multipartFile.getOriginalFilename(),
                multipartFile.getContentType(),
                String.valueOf(multipartFile.getSize()),
                userId,
                multipartFile.getBytes()
        );

        fileGateway.insertFile(file);
    }

    private void validateFile(final MultipartFile multipartFile, final Integer userId){
        final String fileName = multipartFile.getOriginalFilename();

        fileGateway.findFileByUserIdAndFileName(userId,fileName)
                .ifPresent(file -> {
                    throw new RuntimeException(String.format("Already exists a file with name: %s", fileName));
                });

    }
}
