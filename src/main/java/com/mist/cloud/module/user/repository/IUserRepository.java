package com.mist.cloud.module.user.repository;

import com.mist.cloud.module.user.mode.UserRegisterInfo;
import com.mist.cloud.infrastructure.entity.User;

/**
 * @Author: securemist
 * @Datetime: 2023/9/6 16:20
 * @Description:
 */
public interface IUserRepository {


    /**
     * 获取用户信息
     *
     * @param username
     */
    User getUser(String username);

    User getUser(Long userId);

    User getUserByEmail(String email);

    /**
     * 注册用户
     *
     * @param userRegisterInfo
     * @return
     */
    User addUser(UserRegisterInfo userRegisterInfo);

    /**
     * 检查邮箱是否已经注册过
     *
     * @param email
     * @return
     */
    boolean checkEmailRegistered(String email);


    /**
     * 获取用户根文件夹
     *
     * @param userId
     */
    Long getRootFolderId(Long userId);
}
