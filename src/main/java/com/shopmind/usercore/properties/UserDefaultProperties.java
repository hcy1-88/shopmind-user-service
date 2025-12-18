package com.shopmind.usercore.properties;

import com.shopmind.usercore.constant.GenderConst;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

/**
 * Description: 新用户默认的字段值
 * Author: huangcy
 * Date: 2025-12-18
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "shopmind.user.default")
@Validated
public class UserDefaultProperties {
    private String avatar;
    private String nickname = "momo";
    @Pattern(
            regexp = "male|female|other|secret",
            message = "默认性别必须是 male / female / other / secret 之一"
    )
    private String gender = GenderConst.SECRET;
}
