package com.shopmind.usercore.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SetPasswordRequest {
    @NotBlank(message = "新密码不能为空！")
    private String newPassword;
}