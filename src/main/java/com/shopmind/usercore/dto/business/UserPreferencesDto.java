package com.shopmind.usercore.dto.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

/**
 * Description: 用户偏好
 * Author: huangcy
 * Date: 2025-12-20
 */
@Data
public class UserPreferencesDto {
    private List<String> interests;
    private String language;
}
