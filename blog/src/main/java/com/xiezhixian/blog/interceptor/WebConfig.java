package com.xiezhixian.blog.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <Description> WebConfig 配置拦截器拦截哪些请求
 *
 * @author 26802
 * @version 1.0
 * @ClassName WebConfig
 * @taskId
 * @see com.xiezhixian.blog.interceptor
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin","/admin/login","/admin/register");

    }
}
