package com.shopmind.usercore.exception;

import com.shopmind.framework.util.MessageSourceHelper;
import com.shopmind.framework.exception.ShopmindException;
import java.io.Serial;
import java.util.Map;

/**
 * 用户服务业务异常类
 * 继承自 ShopmindException，用于处理用户服务相关的业务异常
 *
 * 使用示例：
 * <pre>
 * // 示例1：简单抛出异常
 * throw new UserServiceException("USER0001");
 *
 * // 示例2：带参数的异常
 * throw new UserServiceException("USER0002", userId);
 *
 * // 示例3：多参数异常
 * throw new UserServiceException("USER0302", userId, currentBalance, requiredAmount);
 *
 * // 示例4：带上下文信息的异常
 * Map<String, Object> context = new HashMap<>();
 * context.put("userId", userId);
 * context.put("operator", currentOperator);
 * throw new UserServiceException("USER0101", context, userId);
 * </pre>
 */
public class UserServiceException extends ShopmindException {

    @Serial
    private static final long serialVersionUID = 1L;

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
    public UserServiceException(String code) {
        super(code, MessageSourceHelper.getMessage(RESOURCE_BASE_NAME, code));
    }

    /**
     * 构造方法2：传入错误码和参数
     * 从国际化文件中读取错误消息模板，并填充参数
     *
     * @param code 错误码（如 "USER0002"）
     * @param args 参数（按顺序填充消息模板中的 {}, {}, {}...）
     */
    public UserServiceException(String code, Object... args) {
        super(code, MessageSourceHelper.getMessage(RESOURCE_BASE_NAME, code, args));
    }

    /**
     * 构造方法3：直接指定错误码和消息（不使用国际化）
     *
     * @param code    错误码
     * @param message 错误消息
     */
    public UserServiceException(String code, String message) {
        super(code, message);
    }

    /**
     * 构造方法4：带上下文信息
     * 上下文信息用于日志埋点和监控
     *
     * @param code    错误码
     * @param context 上下文信息（如 userId, operator 等）
     * @param args    参数
     */
    public UserServiceException(String code, Map<String, Object> context, Object... args) {
        super(code, MessageSourceHelper.getMessage(RESOURCE_BASE_NAME, code, args), context);
    }

    /**
     * 构造方法5：带原始异常
     * 用于包装底层异常
     *
     * @param code  错误码
     * @param cause 原始异常
     * @param args  参数
     */
    public UserServiceException(String code, Throwable cause, Object... args) {
        super(code, MessageSourceHelper.getMessage(RESOURCE_BASE_NAME, code, args), cause);
    }
}
