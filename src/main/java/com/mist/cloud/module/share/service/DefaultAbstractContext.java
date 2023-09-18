package com.mist.cloud.module.share.service;

import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.convert.impl.CalendarConverter;
import cn.hutool.core.date.CalendarUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.mist.cloud.core.exception.file.FolderException;
import com.mist.cloud.core.utils.Session;
import com.mist.cloud.infrastructure.entity.Folder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author: securemist
 * @Datetime: 2023/9/18 10:08
 * @Description:
 */
@Component
public class DefaultAbstractContext extends AbstractShareContext {
    @Value(("${server.port}"))
    private Integer port;

    @Value("${net.host}")
    private String host;

    private int SHARE_URL_LEN = 12;

    private String urlPrefix;

    @PostConstruct
    public void init() {
        urlPrefix = getLocalHost() + "/share?s=";
    }

    private String getLocalHost() {
        return "http://" + host + ":" + port;
    }

    @Override
    protected String generateUrl() {
        String uid = RandomUtil.randomString(SHARE_URL_LEN);
        return uid;
    }

    protected String getCompleteUrl(String str) {
        return urlPrefix + str;
    }


    /**
     * 解析出过期时间
     * effectiveTime share的有效时间，单位：天
     */
    @Override
    protected Date parseExpireTime(Integer effectiveTime) {
        Date createTime = new Date();
        // 没有过期时间
        if (effectiveTime == null || effectiveTime.equals(0)) {
            return null;
        }

        LocalDateTime target = LocalDateTimeUtil.offset(LocalDateTimeUtil.now(), 1, ChronoUnit.DAYS);

        Date expireTime = Date.from(target.atZone(ZoneId.systemDefault()).toInstant());
        return expireTime;
    }

    @Override
    protected void resaveFolder(Long folderId, Long targetFolderId) {
        // 判断目标文件夹内是否已存在同名的文件夹
        List<Folder> subFolders = folderRepository.findSubFolders(targetFolderId);
        Folder folder = folderRepository.findFolder(folderId);

        if (folder != null) {
            for (Folder subFolder : subFolders) {
                if (subFolder.getName().equals(folder.getName())) {
                    throw new FolderException("文件夹已存在");
                }
            }
        }

        resaveFolderRecur(folderId, targetFolderId);
    }

    private void resaveFolderRecur(Long folderId, Long targetFolderId) {
        // 复制当前文件夹
        Long newFolderId = folderRepository.resaveFolder(folderId, targetFolderId, Session.getLoginId());

        // 复制当前文件夹的子文件
        List<Long> fileIds = folderRepository.findFilesId(folderId);
        for (Long fileId : fileIds) {
            fileRepository.copyFile(fileId, newFolderId);
        }

        // 递归复制所有子文件夹
        List<Long> childFolderIds = folderRepository.findSubFoldersId(folderId);
        for (Long childFolderId : childFolderIds) {
            resaveFolderRecur(childFolderId, newFolderId);
        }
    }
}
