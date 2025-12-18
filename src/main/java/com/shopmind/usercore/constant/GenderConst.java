package com.shopmind.usercore.constant;

public class GenderConst {
    public static final String MALE = "male";
    public static final String FEMALE = "female";
    public static final String OTHER = "other";
    public static final String SECRET = "secret";

    // 中文映射
    public static String toChinese(String gender) {
        return switch (gender) {
            case MALE -> "男";
            case FEMALE -> "女";
            case OTHER -> "其它";
            case SECRET -> "保密";
            default -> "未知";
        };
    }

    /**
     * 验证是否合法值
     * @param gender 英文性别
     * @return 布尔
     */
    public static boolean isValid(String gender) {
        return MALE.equals(gender) || FEMALE.equals(gender) || OTHER.equals(gender) || SECRET.equals(gender);
    }
}