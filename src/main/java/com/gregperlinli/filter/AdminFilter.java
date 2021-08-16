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
 * @since 2021-08-16
 */
@WebFilter(filterName = "AdminFilter", value = {"/pages/admin/*", "/admin/*"})
public class AdminFilter implements Filter {
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

        Object admin = httpServletRequest.getSession().getAttribute("admin");

        if ( admin == null ) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() +"/pages/login/admin_login.html");
        } else {
            chain.doFilter(request, response);
        }

    }
}
