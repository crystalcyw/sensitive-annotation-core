package com.zzyk.sensitive.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 敏感字段标记注解(使用在controller接口上)
 *
 * @author liam
 * @date 4/13/21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface Sensitive {

    /**
     * 是否脱敏，默认值true
     *
     * @return
     */
    boolean value() default true;
}
