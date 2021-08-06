package com.gregperlinli.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 通过<code>action</code>调用各种Servlet业务方法
 *
 * @author gregperlinli
 */
@WebServlet(name = "BaseServlet", value = "/BaseServlet")
public abstract class BaseServlet extends HttpServlet {

    /**
     * 用于在GET以及Ajax请求下调用各种业务方法
     *
     * @param request 请求，需要提供要调用的业务方法名<code>action</code>
     * @param response 响应
     * @throws ServletException 抛出错误
     * @throws IOException 抛出错误
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            // Through the action business identification string, get the corresponding business method reflection
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            // Call target business method
            method.invoke(this, request, response);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    /**
     * 用于在POST请求下调用各种业务方法
     *
     * @param request 请求，需要提供要调用的业务方法名<code>action</code>（建议用隐藏型输入标签来在表单中存放）
     * @param response 响应
     * @throws ServletException 抛出错误
     * @throws IOException 抛出错误
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            // Through the action business identification string, get the corresponding business method reflection
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            // Call target business method
            method.invoke(this, request, response);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}
