package com.gregperlinli.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gregperlinli.pojo.Admin;
import com.gregperlinli.service.AccountManageService;
import com.gregperlinli.service.LoginService;
import com.gregperlinli.service.impl.AccountManageServiceImpl;
import com.gregperlinli.service.impl.LoginServiceImpl;
import com.gregperlinli.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于管理员信息的管理（管理员权限）
 *
 * @author gregPerlinLi
 * @since 2021-08-18
 */
@WebServlet(name = "AdminManageServlet", value = "/admin/adminManageServlet")
public class AdminManageServlet extends BaseServlet {
    protected final AccountManageService accountManageService = new AccountManageServiceImpl();
    protected final LoginService loginService = new LoginServiceImpl();
    protected final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    /**
     * 处理修改管理员自己的信息功能
     * 若修改成功会重定向到<code>index.html</code>，若失败则继续停留在该界面
     *
     * @param request 修改请求，需要通过POST请求提供要修改的管理员<code>id</code>，
     *                用户名<code>username</code>，
     *                MD5加密后的密码<code>password</code>
     * @param response 修改响应
     * @throws ServletException 抛出错误
     * @throws IOException 抛出错误
     */
    protected void updateSelf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin admin = WebUtils.copyParamToBean(request.getParameterMap(), new Admin());

        if ( accountManageService.adminUpdate(admin) ) {
            // 修改成功
            request.getSession().setAttribute("admin", admin);
            response.sendRedirect(request.getContextPath() + "/pages/admin/admin_index.html");
        } else {
            // 修改失败
            System.out.println("The admin [ " + admin.getUsername() + " ] is already exist!");
            response.sendRedirect(request.getContextPath() + "/pages/user/update_admin_inform.html");
        }

    }

    /**
     * 通过Ajax请求检查是否存在该管理员用户名
     *
     * @param request 请求，要在其中提供一个需要检查的管理员用户名参数<code>username</code>
     * @param response 响应，将会返回一个布尔值<code>existUsername</code>，若为<code>True</code>，则已存在该姓用户名，若为<code>false</code>，则不存在该用户名
     * @throws ServletException 抛出错误
     * @throws IOException 抛出错误
     */
    protected void ajaxExistAdminUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        boolean existUsername = accountManageService.existAdminUsername(username);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("existUsername", existUsername);

        String json = gson.toJson(resultMap);

        response.getWriter().write(json);
    }
}
