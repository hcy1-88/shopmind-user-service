package com.shopmind.usercore.dto.business;

import lombok.Data;

@Data
public class InterestDto {
    /**
     * 兴趣唯一标识（英文小写，如 beauty）
     */
    private String code;

    /**
     * 兴趣中文名称（如 美妆），供 LLM 理解语义
     */
    private String name;

    /**
     * 前端展示图标（emoji 或 icon class）
     */
    private String icon;

    /**
     * 前端展示排序
     */
    private Integer sortOrder;

    /**
     * 是否启用
     */
    private Boolean enabled;
}