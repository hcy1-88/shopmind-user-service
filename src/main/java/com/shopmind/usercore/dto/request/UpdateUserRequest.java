package com.shopmind.usercore.dto.request;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String nickname;
    private String avatar;
    // 前端传英文：male/female/other/secret
    private String gender;
    private Integer age;
}