package com.shopmind.usercore.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 微信登录临时票据表
 * @TableName sm_users_wechat_login_tickets
 */
@TableName(value ="sm_users_wechat_login_tickets")
@Data
public class UserWechatLoginTicket {
    /**
     * 票据ID
     */
    @TableId
    private Long id;

    /**
     * 票据唯一标识
     */
    private String ticket;

    /**
     * 二维码URL
     */
    private String qrCodeUrl;

    /**
     * 状态：pending/scanned/confirmed/expired
     */
    private String status;

    /**
     * 微信OpenID
     */
    private String openid;

    /**
     * 用户ID（扫码确认后）
     */
    private Long userId;

    /**
     * 扫码时间
     */
    private Date scannedAt;

    /**
     * 确认时间
     */
    private Date confirmedAt;

    /**
     * 过期时间
     */
    private Date expiredAt;

    /**
     * 创建时间
     */
    private Date createdAt;
}