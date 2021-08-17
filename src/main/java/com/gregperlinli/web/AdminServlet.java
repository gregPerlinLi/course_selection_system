package com.gregperlinli.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gregperlinli.pojo.Admin;
import com.gregperlinli.service.LoginService;
import com.gregperlinli.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 主要用于管理员的登录退出和注册操作
 *
 * @author gregPerlinLi
 * @see javax.servlet.ServletConfig
 * @see javax.servlet.Servlet
 * @see javax.servlet.http.HttpServlet
 * @see javax.servlet.GenericServlet
 * @see java.io.Serializable
 * @see com.gregperlinli.web.BaseServlet
 * @since 2021-08-06
 */
@WebServlet(name = "AdminServlet", value = "/adminServlet")
public class AdminServlet extends BaseServlet{

    protected final LoginService loginService = new LoginServiceImpl();
    protected final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    /**
     * 处理管理员登录功能<br/>
     * 若登录成功会转发到<code>admin_index.html</src>，若失败则继续停留在登录页面
     *
     * @param request 登录请求，需要提供登录的管理员用户名<code>username</code>，以及经过MD5加密后的登录密码<code>password</code>
     * @param response 登录响应
     * @throws ServletException 抛出错误
     * @throws IOException 抛出错误
     */
    protected void adminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Get request parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println(username);
        System.out.println(password);

        Admin loginAdmin = loginService.adminLogin(username, password);
        // 2.
        if ( loginAdmin != null ) {
            //
            System.out.println("Login success!");
            // 保存登录之后的信息到session域中
            request.getSession().setAttribute("admin", loginAdmin);
            request.getRequestDispatcher("pages/admin/admin_index.html").forward(request, response);
        } else {
            System.out.println("Login failed!");
            request.getRequestDispatcher("/pages/login/admin_login.html").forward(request, response);
        }
    }

    /**
     * 处理管理员退出功能<br/>
     * 退出之后会重定向到<code>admin_login.html</code>
     *
     * @param request 退出请求，<code>session</code>中需要提供一个登录的管理员键值对<code>admin</code>
     * @param response 退出响应
     * @throws ServletException 抛出错误
     * @throws IOException 抛出错误
     */
    protected void adminLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("admin");
        response.sendRedirect(request.getContextPath() + "/pages/login/admin_login.html");
    }

    /**
     * 通过Ajax请求获取登录的管理员信息
     *
     * @param request 请求
     * @param response 响应，将会返回一个以登录的管理员对象<code>admin</code>
     * @throws ServletException 抛出错误
     * @throws IOException 抛出错误
     */
    protected void ajaxGetAdminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin loginAdmin = (Admin) request.getSession().getAttribute("admin");
        String json = gson.toJson(loginAdmin);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }

}
