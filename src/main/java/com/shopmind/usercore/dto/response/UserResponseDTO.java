package com.shopmind.usercore.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserResponseDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    private String phoneNumber;
    private String nickname;
    private String avatar;
    private String gender;
    private Integer age;
    private Date createdAt;
    private Date updatedAt;
    /**
     * 单向 Hash 加密过的密码
     */
    private String passwordHash;
}