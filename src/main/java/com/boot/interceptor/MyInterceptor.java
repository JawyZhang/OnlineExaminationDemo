package com.boot.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Mango
 * @Date 2020-04-09 09:07
 */
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //拦截所有的非登录请求
        if (!"/login".equals(request.getRequestURI())) {
            if (request.getSession().getAttribute("user") == null) {
                request.setAttribute("tip", "登录信息过期，请重新登录");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return false;
            }
        }
        return true;
    }
}
