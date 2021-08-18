package com.gregperlinli.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用于进行管理员权限认证
 *
 * @author gregPerlinLi
 * @see javax.servlet.Filter
 * @since 2021-08-16
 */

@WebFilter(filterName = "StudentFilter", value = "/pages/user/*")
public class StudentFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        Object student = httpServletRequest.getSession().getAttribute("student");

        if ( student == null ) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() +"/pages/login/login.html");
        } else {
            chain.doFilter(request, response);
        }
    }
}
