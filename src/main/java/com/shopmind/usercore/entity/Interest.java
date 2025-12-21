package com.shopmind.usercore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户兴趣标签（用于 LLM 推荐、个性化 prompt）
 * @TableName sm_interest
 */
@TableName(value ="sm_interest")
@Data
public class Interest {
    /**
     * 兴趣唯一标识（英文小写，如 beauty）
     */
    @TableId
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