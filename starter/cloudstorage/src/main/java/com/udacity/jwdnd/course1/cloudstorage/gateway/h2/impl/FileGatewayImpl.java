package com.udacity.jwdnd.course1.cloudstorage.gateway.h2.impl;

import com.udacity.jwdnd.course1.cloudstorage.domain.File;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.FileGateway;
import com.udacity.jwdnd.course1.cloudstorage.gateway.h2.repositories.FileRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FileGatewayImpl implements FileGateway {

    private final FileRepository fileRepository;

    public FileGatewayImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public List<File> findFilesByUserId(final int userId) {
        return fileRepository.findFilesByUserId(userId);
    }

    @Override
    public Optional<File> findFileByUserIdAndFileName(final int userId, final String fileName) {
        return Optional.ofNullable(fileRepository.findFileByUserIdAndName(userId, fileName));
    }

    @Override
    public Optional<File> findFileById(int fileId) {
        return Optional.ofNullable(fileRepository.findFileById(fileId));
    }

    @Override
    public int insertFile(final File file) {
        return fileRepository.insertFile(file);
    }

    @Override
    public void deleteFileById(int fileId) {
        fileRepository.deleteFile(fileId);
    }
}
