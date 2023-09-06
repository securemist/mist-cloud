package com.mist.cloud.common.result;

import cn.hutool.extra.tokenizer.Result;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: securemist
 * @Datetime: 2023/9/6 15:39
 * @Description:
 */
public class AjaxJson<T> implements Serializable {
    private static final long serialVersionUID = 1L;    // 序列化版本号

    public static final int CODE_SUCCESS = 200;            // 成功状态码
    public static final int CODE_ERROR = 500;            // 错误状态码
    public static final int CODE_WARNING = 501;            // 警告状态码
    public static final int CODE_NOT_JUR = 403;            // 无权限状态码
    public static final int CODE_NOT_LOGIN = 401;        // 未登录状态码
    public static final int CODE_INVALID_REQUEST = 400;    // 无效请求状态码
    public static final int REQUIRED_PASSWORD = 405;       // 未输入密码
    public static final int INVALID_PASSWORD = 406;        // 无效的密码

    @ApiModelProperty(value = "业务状态码，0 为正常，其他值均为异常，异常情况下见响应消息", example = "0")
    private final int code;

    @ApiModelProperty(value = "响应消息", example = "ok")
    private String msg;

    @ApiModelProperty(value = "响应数据")
    private T data;


    /**
     * 返回code
     */
    public int getCode() {
        return this.code;
    }


    /**
     * 给 msg 赋值，连缀风格
     */
    public AjaxJson<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }


    public String getMsg() {
        return this.msg;
    }


    /**
     * 给data赋值，连缀风格
     */
    public AjaxJson<T> setData(T data) {
        this.data = data;
        return this;
    }

    // ============================  构建  ==================================

    public AjaxJson(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public AjaxJson(int code, String msg, T data, Long dataCount) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    // 返回成功
    public static AjaxJson<Void> getSuccess() {
        return new AjaxJson<>(CODE_SUCCESS, "ok", null, null);
    }


    public static AjaxJson<Void> getSuccess(String msg) {
        return new AjaxJson<>(CODE_SUCCESS, msg, null, null);
    }


    public static AjaxJson<?> getSuccess(String msg, Object data) {
        return new AjaxJson<>(CODE_SUCCESS, msg, data, null);
    }


    public static <T> AjaxJson<T> getSuccessData(T data) {
        return new AjaxJson<>(CODE_SUCCESS, "ok", data, null);
    }


    public static AjaxJson<?> getSuccessArray(Object... data) {
        return new AjaxJson<>(CODE_SUCCESS, "ok", data, null);
    }


    // 返回失败
    public static AjaxJson<?> getError() {
        return new AjaxJson<>(CODE_ERROR, "error", null, null);
    }


    public static AjaxJson<String> getError(String msg) {
        return new AjaxJson<>(CODE_ERROR, msg, null, null);
    }


    public static AjaxJson<String> getBadRequestError(String msg) {
        return new AjaxJson<>(CODE_INVALID_REQUEST, msg, null, null);
    }


    // 返回警告
    public static AjaxJson<?> getWarning() {
        return new AjaxJson<>(CODE_ERROR, "warning", null, null);
    }


    public static AjaxJson<?> getWarning(String msg) {
        return new AjaxJson<>(CODE_WARNING, msg, null, null);
    }


    // 返回未登录
    public static AjaxJson<?> getNotLogin() {
        return new AjaxJson<>(CODE_NOT_LOGIN, "未登录，请登录后再次访问", null, null);
    }


    // 返回没有权限的
    public static AjaxJson<?> getNotJur(String msg) {
        return new AjaxJson<>(CODE_NOT_JUR, msg, null, null);
    }


    // 返回一个自定义状态码的
    public static AjaxJson<?> get(int code, String msg) {
        return new AjaxJson<>(code, msg, null, null);
    }


    // 返回分页和数据的
    public static AjaxJson<?> getPageData(Long dataCount, Object data) {
        return new AjaxJson<>(CODE_SUCCESS, "ok", data, dataCount);
    }


    // 返回，根据布尔值来确定最终结果的  (true=ok，false=error)
    public static AjaxJson<?> getByBoolean(boolean b) {
        return b ? getSuccess("ok") : getError("error");
    }


    public T getData() {
        return data;
    }


}
