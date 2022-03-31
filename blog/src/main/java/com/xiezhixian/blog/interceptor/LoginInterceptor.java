package com.xiezhixian.blog.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <Description> LoginInterceptor 登录拦截器，防止不登陆访问后台
 *
 * @author 26802
 * @version 1.0
 * @ClassName LoginInterceptor
 * @taskId
 * @see com.xiezhixian.blog.interceptor
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if(request.getSession().getAttribute("user")==null){
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
