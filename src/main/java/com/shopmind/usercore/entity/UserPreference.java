package com.shopmind.usercore.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 用户偏好设置表
 * @TableName sm_users_preferences
 */
@TableName(value ="sm_users_preferences")
@Data
public class UserPreference {
    /**
     * 偏好ID
     */
    @TableId
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 兴趣标签数组
     */
    private Object interests;

    /**
     * 语言偏好：zh/en
     */
    private String language;

    /**
     * 通知设置
     */
    private Object notificationSettings;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;
}