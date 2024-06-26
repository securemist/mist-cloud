package com.mist.cloud.module.user.service;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.CaptchaUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;


/**
 * @Author: securemist
 * @Datetime: 2023/9/6 14:09
 * @Description: 验证码服务
 */
@Service
public class CaptchaService {

    @PostConstruct
    public void init() {

    }

    public AbstractCaptcha createCode(String uid) {
        AbstractCaptcha shearCaptcha = CaptchaUtil.createShearCaptcha(250, 100, 4, 4);
        String code = shearCaptcha.getCode();

        // 创建验证码或者刷新验证码
//        captcharMap.put(uid, code);

        // 第一次创建验证码
        return shearCaptcha;
    }

//    public boolean verify(String uuid, String userInputCode) {
//        String code = captcharMap.get(uuid);
//
//        // 获取不到用户会话的uid信息
//        if (code == null) {
//            return false;
//        }
//
//        // 不管成功与否都删除验证码
//        captcharMap.remove(uuid);
//
//        return code.equals(userInputCode);
//    }
}
