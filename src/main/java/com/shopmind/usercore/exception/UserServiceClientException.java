package com.shopmind.usercore.exception;

import com.shopmind.framework.util.MessageSourceHelper;

import java.io.Serial;

/**
 * Description: 用户业务异常，直接抛出 （适用于微服务之间的调用）
 * Author: huangcy
 * Date: 2025-12-19
 */
public class UserServiceClientException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private final String code;

    /**
     * 错误消息（支持动态填充）
     */
    private final String message;

    /**
     * 资源文件基础名称
     * 对应 resources 目录下的 user_message_zh_CN.properties
     */
    private static final String RESOURCE_BASE_NAME = "user_message";

    /**
     * 构造方法1：只传入错误码
     * 从国际化文件中读取错误消息
     *
     * @param code 错误码（如 "USER0001"）
     */
    public UserServiceClientException(String code) {
        this.code = code;
        this.message = MessageSourceHelper.getMessage(RESOURCE_BASE_NAME, code);
    }

    /**
     * 构造方法2：传入错误码和参数
     * 从国际化文件中读取错误消息模板，并填充参数
     *
     * @param code 错误码（如 "USER0002"）
     * @param args 参数（按顺序填充消息模板中的 {}, {}, {}...）
     */
    public UserServiceClientException(String code, Object... args) {
        this.code = code;
        this.message = MessageSourceHelper.getMessage(RESOURCE_BASE_NAME, code, args);
    }

    /**
     * 构造方法3：直接指定错误码和消息（不使用国际化）
     *
     * @param code    错误码
     * @param message 错误消息
     */
    public UserServiceClientException(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
