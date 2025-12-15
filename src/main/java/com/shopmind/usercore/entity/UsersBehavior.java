package com.shopmind.usercore.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 用户行为追踪表
 * @TableName sm_users_behaviors
 */
@TableName(value ="sm_users_behaviors")
@Data
public class UsersBehavior {
    /**
     * 行为ID
     */
    @TableId
    private Long id;

    /**
     * 用户ID（可为空，匿名用户）
     */
    private Long userId;

    /**
     * 会话ID
     */
    private String sessionId;

    /**
     * 行为类型：view/like/share/search/add_cart/purchase
     */
    private String behaviorType;

    /**
     * 目标类型：product/review/order
     */
    private String targetType;

    /**
     * 目标ID
     */
    private Long targetId;

    /**
     * 行为元数据
     */
    private Object metadata;

    /**
     * 创建时间
     */
    private Date createdAt;
}