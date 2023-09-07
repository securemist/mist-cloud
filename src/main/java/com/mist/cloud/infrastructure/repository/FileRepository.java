package com.mist.cloud.core.infrastructure.repository;

import com.mist.cloud.core.config.IdGenerator;
import com.mist.cloud.core.infrastructure.pojo.FileCopyReq;
import com.mist.cloud.core.infrastructure.pojo.FileSelectReq;
import com.mist.cloud.module.file.repository.IFileRepository;
import com.mist.cloud.core.infrastructure.entity.File;
import com.mist.cloud.core.infrastructure.mapper.FileMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: securemist
 * @Datetime: 2023/8/22 14:34
 * @Description:
 */
@Component
public class FileRepository implements IFileRepository {
    @Resource
    private FileMapper fileMapper;

    @Override
    public File findFile(Long fileId) {
        File file = fileMapper.selectFileById(fileId);
        return file;
    }

    @Override
    public File findFileByObj(FileSelectReq fileSelectReq) {
        return fileMapper.selectFileByObj(fileSelectReq);
    }


    @Override
    public void addFile(File file) {
        fileMapper.insertFile(file);
    }

    @Override
    public void updateFileDuplicateTimes(Long fileId) {
        FileSelectReq fileSelectReq = FileSelectReq.builder()
                .id(fileId)
                .build();

        fileMapper.updateFileDuplicateTimes(fileSelectReq);
    }

    @Override
    public void renameFile(Long fileId, String fileName) {
        FileSelectReq fileSelectReq = FileSelectReq.builder()
                .fileName(fileName)
                .id(fileId)
                .build();

        fileMapper.renameFile(fileSelectReq);
    }

    @Override
    public void deleteFile(Long fileId) {
        FileSelectReq fileSelectReq = FileSelectReq.builder()
                .id(fileId)
                .build();

        fileMapper.deleteFile(fileSelectReq);
    }

    @Override
    public void realDeleteFile(Long fileId) {
        FileSelectReq fileSelectReq = FileSelectReq.builder()
                .id(fileId)
                .build();

        fileMapper.realDeleteFile(fileSelectReq);
    }

    @Override
    public void copyFile(Long fileId, Long targetFolderId) {
        FileCopyReq fileCopyReq = FileCopyReq.builder()
                .fileId(fileId)
                .targetFolderId(targetFolderId)
                .newFileId(IdGenerator.fileId())
                .build();

        fileMapper.copyFile(fileCopyReq);

    }

    @Override
    public boolean isFolder(Long fileId) {
        File file = fileMapper.selectFileById(fileId);
        return file == null;
    }

    @Override
    public List<File> searchByName(String value) {
        List<File> fileList = fileMapper.search(value);
        return fileList;
    }

}
