package com.shopmind.usercore.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shopmind.usercore.enums.Gender;
import lombok.Data;

import java.util.Date;

@Data
public class UserResponseDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    private String phoneNumber;
    private String nickname;
    private String avatar;
    private Gender gender;
    private Integer age;
    private Date createdAt;
    private Date updatedAt;
    /**
     * 单向 Hash 加密过的密码
     */
    private String passwordHash;
}