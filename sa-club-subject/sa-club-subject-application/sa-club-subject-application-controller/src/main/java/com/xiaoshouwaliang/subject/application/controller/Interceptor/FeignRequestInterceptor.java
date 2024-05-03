package com.xiaoshouwaliang.subject.application.controller.Interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author 小手WA凉
 * @date 2024-05-03
 */
@Component
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {

        ServletRequestAttributes servletRequestAttributes =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        if(Objects.nonNull(request)){
            String loginId = request.getHeader("loginId");
            if(StringUtils.isNotBlank(loginId)){
                requestTemplate.header("loginId",loginId);
            }
        }
    }
}
