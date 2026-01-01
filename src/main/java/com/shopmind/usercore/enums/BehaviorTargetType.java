package com.shopmind.usercore.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 行为目标类型枚举
 * 对应值: product / review / order
 */
@Getter
public enum BehaviorTargetType {

    PRODUCT("product", "商品"),
    REVIEW("review", "评论"),
    ORDER("order", "订单"),
    KEYWORD("keyword", "关键词");

    @EnumValue
    private final String value;

    private final String description;

    BehaviorTargetType(String value, String description) {
        this.value = value;
        this.description = description;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

    public static BehaviorTargetType fromValue(String value) {
        for (BehaviorTargetType type : BehaviorTargetType.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        return null; // 或抛异常
    }
}