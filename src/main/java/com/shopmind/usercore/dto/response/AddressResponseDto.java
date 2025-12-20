package com.shopmind.usercore.dto.response;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * Description: 地址
 * Author: huangcy
 * Date: 2025-12-19
 */
@Data
public class AddressResponseDto {
    /**
     * 地址ID, 以 string 传递给前端
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 地址标签：家庭/公司/学校/其他
     */
    private String label;

    /**
     * 省份编码
     */
    private String provinceCode;

    /**
     * 省份名称
     */
    private String provinceName;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 区县编码
     */
    private String districtCode;

    /**
     * 区县名称
     */
    private String districtName;

    /**
     * 详细地址
     */
    private String detailAddress;

    /**
     * 是否默认地址
     */
    private Boolean isDefault;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系人电话
     */
    private String contactPhone;
}
