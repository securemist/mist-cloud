package com.mist.cloud.module.transmit.service;

import com.mist.cloud.core.config.FileConfig;
import com.mist.cloud.module.file.repository.IFileRepository;
import com.mist.cloud.module.file.repository.IFolderRepository;
import com.mist.cloud.infrastructure.entity.File;

import javax.annotation.Resource;

/**
 * @Author: securemist
 * @Datetime: 2023/8/22 13:36
 * @Description:
 */
public class TransmitSupport {
    @Resource
    protected FileConfig fileConfig;
    @Resource
    protected IFileRepository fileRepository;
    @Resource
    protected IFolderRepository folderRepository;

    public File findFile(Long fileId){
        return fileRepository.findFile(fileId);
    }
}