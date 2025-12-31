package com.shopmind.usercore.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.shopmind.usercore.enums.Gender;
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
     * 密码
     */
    private String password;

    /**
     * 头像URL
     */
    private String avatar;

    /**
     * 性别：male/female/other/secret
     */
    private Gender gender;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createdAt;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedAt;

    /**
     * 软删除时间
     */
    private Date deletedAt;
}