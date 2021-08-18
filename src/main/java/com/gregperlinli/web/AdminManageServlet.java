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
import java.util.List;
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


    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * 处理修改管理员信息功能
     * 若修改成功会重定向到<code>admin_management.html</code>，若失败则继续停留在该界面
     *
     * @param request 修改请求，需要通过POST请求提供要修改的管理员<code>id</code>，
     *                用户名<code>username</code>，
     *                MD5加密后的密码<code>password</code>
     * @param response 修改响应
     * @throws ServletException 抛出错误
     * @throws IOException 抛出错误
     */
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin admin = WebUtils.copyParamToBean(request.getParameterMap(), new Admin());

        if ( accountManageService.adminUpdate(admin) ) {
            // 修改成功
            response.sendRedirect(request.getContextPath() + "/pages/admin/admin_management.html");
        } else {
            // 修改失败
            System.out.println("The student [ " + admin.getUsername() + " ] is already exist!");
            response.sendRedirect(request.getContextPath() + "/pages/admin/update_admin.html?id=" + admin.getId());
        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * 通过Ajax请求获取某个<code>id</code>对应的管理员信息
     *
     * @param request 请求，要在其中输入一个需要获取的管理员<code>id</code>
     * @param response 响应，将会返回一个包含所查找的管理员信息的对象<code>course</code>
     * @throws ServletException 抛出错误
     * @throws IOException 抛出错误
     */
    protected void getAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        Admin admin = accountManageService.getAdminById(id);

        String json = gson.toJson(admin);

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }

    /**
     * 通过Ajax请求获得所有管理员信息
     *
     * @param request 请求
     * @param response 响应，将会返回一个包含所有管理员信息的对象集合<code>allCourse</code>
     * @throws ServletException 抛出错误
     * @throws IOException 抛出错误
     */
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 通过AccountManageService查询全部课程
        List<Admin> allAdmin = accountManageService.getAllAdmin();
        // 2. 把全部课程数据转换为Json格式
        String json = gson.toJson(allAdmin);
        // 3. 传回结果
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }

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
