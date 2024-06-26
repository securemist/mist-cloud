package com.mist.cloud.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: securemist
 * @Datetime: 2023/7/18 15:11
 * @Description:
 */
@Component
public class FileConfig {
    @Value("${upload.base-path}")
    private String path;

    @Value("${upload.checkmd5}")
    public boolean checkmd5;

    /**
     * 文件上传之后最终存储的位置
     * @return
     */
    public String getBasePath() {
        return path + "/file";
    }

    /**
     * 文件下载时的临时存储位置
     * @return
     */
    public String getDownloadPath() {
        return path + "/download";
    }

    /**
     * 文件上传时的临时存储位置
     * @return
     */
    public String getUploadPath() {
        return path + "/upload";
    }
}
