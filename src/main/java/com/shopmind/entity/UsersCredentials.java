package com.shopmind.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 用户认证凭证表
 * @TableName sm_users_credentials
 */
@TableName(value ="sm_users_credentials")
@Data
public class UsersCredentials {
    /**
     * 凭证ID
     */
    @TableId
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 认证类型：password/sms/wechat
     */
    private String authType;

    /**
     * 凭证标识（手机号/微信openid等）
     */
    private String credentialKey;

    /**
     * 凭证值（密码hash等）
     */
    private String credentialValue;

    /**
     * 微信OpenID
     */
    private String wechatOpenid;

    /**
     * 微信UnionID
     */
    private String wechatUnionid;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;
}