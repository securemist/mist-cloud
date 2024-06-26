package com.mist.cloud.infrastructure.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @Author: securemist
 * @Datetime: 2023/8/17 09:01
 * @Description:
 */
@Data
@Builder
public class Chunk implements Serializable {

    private Long id;
    /**
     * 当前文件块，从1开始
     */
    private Integer chunkNumber;
    /**
     * 分块大小
     */
    private Long chunkSize;
    /**
     * 当前分块大小
     */
    private Long currentChunkSize;
    /**
     * 总大小
     */
    private Long totalSize;
    /**
     * 文件标识
     */
    private String identifier;
    /**
     * 文件名
     */
    private String filename;
    /**
     * 总块数
     */
    private Integer totalChunks;

    private MultipartFile file;
}
