package com.shopmind.usercore.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class UserResponseDTO {
    private Long id;
    private String phoneNumber;
    private String nickname;
    private String avatar;
    private String gender; // 后端返回中文性别
    private Integer age;
    private Date createdAt;
    private Date updatedAt;
}