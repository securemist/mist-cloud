package com.mist.cloud.domain.tansmit.model.vo;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: securemist
 * @Datetime: 2023/8/17 13:43
 * @Description:
 */
@Data
@Getter
@Setter
@Builder
public class ChunkVo {

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

    /**
     * 真实路径
     */
    private String relativePath;

    /**
     * 文件夹 id
     */
    private Long folderId;

    private MultipartFile file;
}