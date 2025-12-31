package com.shopmind.usercore.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Gender {

    MALE("male", "男"),
    FEMALE("female", "女"),
    OTHER("other", "其他"),
    SECRET("secret", "保密");

    /**
     * 存入数据库的实际值（必须用 @EnumValue 标记）
     */
    @EnumValue
    private final String value;

    /**
     * 中文描述（用于展示，非数据库存储）
     */
    private final String description;

    Gender(String value, String description) {
        this.value = value;
        this.description = description;
    }

    /**
     * JSON 序列化时输出 value（如 "male"），而不是整个对象
     */
    @JsonValue
    public String getValue() {
        return this.value;
    }

    /**
     * 根据字符串值获取枚举（可选工具方法）
     */
    public static Gender fromValue(String value) {
        for (Gender gender : Gender.values()) {
            if (gender.value.equals(value)) {
                return gender;
            }
        }
        return null; // 或抛异常：throw new IllegalArgumentException("Invalid gender: " + value);
    }
}