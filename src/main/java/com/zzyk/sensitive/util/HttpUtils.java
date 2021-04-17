package com.zzyk.sensitive.util;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author liam
 * @date 2021/04/16
 */
public class HttpUtils {

    public static HttpServletRequest currentRequest() {
        ServletRequestAttributes requestAttributes =
            (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return  requestAttributes.getRequest();
    }
}
