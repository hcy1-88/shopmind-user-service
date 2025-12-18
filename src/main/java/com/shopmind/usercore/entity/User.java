package com.shopmind.usercore.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 用户基本信息表
 * @TableName sm_users
 */
@TableName(value ="sm_users")
@Data
public class User {
    /**
     * 用户ID（分布式ID）
     */
    @TableId
    private Long id;

    /**
     * 手机号（可为空，微信登录用户可能没有手机号）
     */
    private String phoneNumber;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 头像URL
     */
    private String avatar;

    /**
     * 性别：male/female/other
     */
    private String gender;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 软删除时间
     */
    private Date deletedAt;
}