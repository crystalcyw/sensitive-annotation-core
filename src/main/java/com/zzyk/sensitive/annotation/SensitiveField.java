package com.zzyk.sensitive.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zzyk.sensitive.enums.SensitiveType;
import com.zzyk.sensitive.json.SensitiveFieldSerialize;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 敏感字段标记注解（使用在字段上）
 *
 * @author liam
 * @date 4/13/21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@JacksonAnnotationsInside
@JsonSerialize(using = SensitiveFieldSerialize.class)
public @interface SensitiveField {

    /**
     * 脱敏类型
     *
     * @return
     */
    SensitiveType value() default SensitiveType.DEFAULT_TYPE;

    /**
     * 输入格式，使用正则，优先级高于 value
     *
     * @return
     */
    String pattern() default "";

    /**
     * 替换目标字符，优先级高于value
     *
     * @return
     */
    String targetChar() default "*";
}
