package com.shopmind.usercore.dto.response;

import com.shopmind.usercore.enums.BehaviorTargetType;
import com.shopmind.usercore.enums.BehaviorType;
import lombok.Data;

import java.util.Date;

/**
 * Description:
 * Author: huangcy
 * Date: 2026-01-01
 */
@Data
public class UserBehaviorResponseDTO {
    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 行为类型：view/like/share/search/add_cart/purchase
     */
    private BehaviorType behaviorType;

    /**
     * product/review/order/keyword
     */
    private BehaviorTargetType targetType;

    /**
     * 目标 id
     */
    private Long targetId;

    /**
     * 关键词
     */
    private String searchKeyword;

    /**
     * 行为发生时间
     */
    private Date createdAt;
}
