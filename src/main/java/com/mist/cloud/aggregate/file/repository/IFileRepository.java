package com.mist.cloud.aggregate.file.repository;

import com.mist.cloud.aggregate.file.model.entity.FileSelectReq;
import com.mist.cloud.infrastructure.DO.File;

import java.util.List;

/**
 * @Author: securemist
 * @Datetime: 2023/8/22 14:35
 * @Description:
 */
public interface IFileRepository {
    File findFile(Long fileId);

    File findFileByObj(FileSelectReq file);

    /**
     * 添加文件
     * @param file
     */
    void addFile(File file);

    /**
     * 更新文件重名次数
     * @param fileId
     */
    void updateFileDuplicateTimes(Long fileId);

    /**
     * 文件重命名
     * @param fileId
     * @param fileName
     */
    void renameFile(Long fileId, String fileName);

    /**
     * 删除文件，逻辑删除
     * @param fileId
     */
    void deleteFile(Long fileId);

    /**
     * 删除文件，真实删除
     * @param fileId
     */
    void realDeleteFile(Long fileId);

    /**
     * 复制文件
     * @param fileId 原文件 id
     * @param targetFolderId 目标文件夹 id
     */
    void copyFile( Long fileId, Long targetFolderId);

    /**
     * 判断当前id是文件还是文件夹
     * @param fileId
     * @return
     */
    boolean isFolder(Long fileId);

    /**
     * 根据文件名模糊查询
     * @param value 查询关键字
     * @return
     */
    List<File> searchByName(String value);
}