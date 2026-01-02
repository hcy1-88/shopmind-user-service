package com.shopmind.usercore.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Description:
 * Author: huangcy
 * Date: 2026-01-02
 */
@Data
public class BehaviorCreatedRequestDTO {

    @NotNull
    private Long userId;

    /**
     * 行为类型：view/like/share/search/add_cart/purchase
     */
    private String behaviorType;

    /**
     * 目标类型：product/review/order
     */
    private String targetType;

    /**
     * 比如 商品 id、评论 id、订单 id，跟 targetType 操作对象的类型有关
     */
    private Long targetId;

    /**
     * 搜索关键词, 当 behaviorType 为 search 时有用
     */
    private String searchKeyword;
}
