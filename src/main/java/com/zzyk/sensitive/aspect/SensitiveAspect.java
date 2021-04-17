package com.zzyk.sensitive.aspect;

import com.zzyk.sensitive.annotation.Sensitive;
import com.zzyk.sensitive.constant.SensitiveConstant;
import com.zzyk.sensitive.util.HttpUtils;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 数据脱敏切面类
 *
 * @author liam
 * @date 2021/04/13
 */
@Aspect
@Component
public class SensitiveAspect {

    @Pointcut("@within(com.zzyk.sensitive.annotation.Sensitive) || @annotation(com.zzyk.sensitive.annotation.Sensitive)")
    public void pointcut() {
    }

    /**
     * 在方法之前执行
     *
     * @throws Exception
     */
    @Before("pointcut()")
    public void before(JoinPoint joinPoint) throws Exception {
        HttpServletRequest request = HttpUtils.currentRequest();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        // 获取controller方法上的注解
        Sensitive sensitive = method.getAnnotation(Sensitive.class);
        if (sensitive == null) {
            sensitive = method.getDeclaringClass().getAnnotation(Sensitive.class);
        }

        // 将标识放入当前请求
        request.setAttribute(SensitiveConstant.IS_SENSITIVE, sensitive.value());

    }
}
