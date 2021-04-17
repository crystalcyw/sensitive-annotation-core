package com.zzyk.sensitive.enums;

/**
 * 脱敏类型枚举
 *
 * @author liam
 * @date 4/13/21
 */
public enum SensitiveType {

    /**
     * 中文名 (张三 → 张*)
     */
    CHINESE_NAME("(?<=.{1}).", "*"),

    /**
     * 密码
     */
    PASSWORD(".", "*"),

    /**
     * 身份证号
     */
    ID_CARD("(?<=\\w{3})\\w(?=\\w{4})", "*"),

    /**
     * 座机号
     */
    FIXED_PHONE("(?<=\\w{3})\\w(?=\\w{2})", "*"),

    /**
     * 手机号
     */
    MOBILE_PHONE("(?<=\\w{3})\\w(?=\\w{4})", "*"),

    /**
     * 地址
     */
    ADDRESS("(.{5}).+(.{4})", "$1*****$2"),

    /**
     * 电子邮件
     */
    EMAIL("(\\w+)\\w{3}@(\\w+)", "$1***@$2"),

    /**
     * 银行卡
     */
    BANK_CARD("(?<=\\w{4})\\w(?=\\w{4})", "*"),

    /**
     * 公司开户银行联号
     */
    CNAPS_CODE("(?<=\\w{4})\\w(?=\\w{4})", "*"),

    /**
     * 默认值
     */
    DEFAULT_TYPE("", "");

    /**
     * 正则
     */
    private final String pattern;

    /**
     * 替换字符
     */
    private final String targetChar;


    SensitiveType(String pattern, String targetChar) {
        this.pattern = pattern;
        this.targetChar = targetChar;
    }

    public String getPattern() {
        return pattern;
    }


    public String getTargetChar() {
        return targetChar;
    }
}
