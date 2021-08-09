package com.gregperlinli.web;

import com.google.gson.Gson;
import com.gregperlinli.pojo.Classes;
import com.gregperlinli.pojo.College;
import com.gregperlinli.pojo.Grade;
import com.gregperlinli.pojo.Student;
import com.gregperlinli.service.AccountManageService;
import com.gregperlinli.service.CollegeGradeClassManageService;
import com.gregperlinli.service.LoginService;
import com.gregperlinli.service.impl.AccountManageServiceImpl;
import com.gregperlinli.service.impl.CollegeGradeClassManageServiceImpl;
import com.gregperlinli.service.impl.LoginServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gregperlinli
 */
@WebServlet(name = "UserServlet", value = "/userServlet")
public class UserServlet extends BaseServlet {

    protected final LoginService loginService = new LoginServiceImpl();
    protected final AccountManageService accountManageService = new AccountManageServiceImpl();
    protected final CollegeGradeClassManageService cgcms = new CollegeGradeClassManageServiceImpl();

    /**
     * 处理学生登录功能<br/>
     * 若登录成功会转发到<code>index.html</src>，若失败则继续停留在登录页面
     *
     * @param request 登录请求，需要提供登录的学生姓名<code>username</code>，以及经过MD5加密后的登录密码<code>password</code>
     * @param response 登录响应
     * @throws ServletException 抛出错误
     * @throws IOException 抛出错误
     */
    protected void studentLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        // 1. Get request parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println(username);
        System.out.println(password);

        // 2.
        if ( loginService.studentLogin(username, password) != null ) {
            //
            System.out.println("Login success!");
            request.getRequestDispatcher("/pages/user/index.html").forward(request, response);
        } else {
            System.out.println("Login failed!");
            request.getRequestDispatcher("/pages/login/login.html").forward(request, response);
        }
    }

    /**
     * 处理学生注册功能<br/>
     * 若注册成功则会转发到<code>regist_success</code>页面，若失败则继续停留在原来的页面
     *
     * @param request 注册请求，需要提供要注册的学生学号<code>stuNum</code>，
     *                学生姓名<code>username</code>，
     *                学生所在学院<code>college</code>，
     *                所在年级<code>grade</code>，
     *                所在班级<code>classes</code>，
     *                以及经过MD5加密后的密码<code>password</code>
     * @param response 注册响应
     * @throws ServletException 抛出错误
     * @throws IOException 抛出错误
     */
    protected void studentRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Get request parameters
        Student student = WebUtils.copyParamToBean(request.getParameterMap(), new Student());

        // 2. Check whether the username is correct
        if ( accountManageService.studentRegist(student) ) {
            // available
            request.getRequestDispatcher("/pages/user/regist_success.html").forward(request, response);
        } else {
            // not available
            System.out.println("The username [ " + student.getUsername() + " ] is already exist!");
            request.getRequestDispatcher("/pages/login/register.html").forward(request, response);
        }

    }

    /**
     * 通过Ajax请求检查是否存在该学生学号
     *
     * @param request 请求，要在其中输入一个需要检查的学生学号参数<code>stuNum</code>
     * @param response 响应，将会返回一个布尔值<code>existStuNum</code>，若为<code>True</code>，则已存在该学号，若为<code>false</code>，则不存在该学号
     * @throws ServletException 抛出错误
     * @throws IOException 抛出错误
     */
    protected void ajaxExistStuNum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stuNum = request.getParameter("stuNum");

        boolean existStuNum = accountManageService.existStudentStuNum(stuNum);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("existStuNum", existStuNum);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        response.getWriter().write(json);
    }

    /**
     * 通过Ajax请求检查是否存在该学生学号
     *
     * @param request 请求，要在其中提供一个需要检查的学生姓名参数<code>username</code>
     * @param response 响应，将会返回一个布尔值<code>existUsername</code>，若为<code>True</code>，则已存在该姓名，若为<code>false</code>，则不存在该姓名
     * @throws ServletException 抛出错误
     * @throws IOException 抛出错误
     */
    protected void ajaxExistStuUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        boolean existUsername = accountManageService.existStudentUsername(username);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("existUsername", existUsername);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        response.getWriter().write(json);
    }

    /**
     * 通过Ajax请求获取所有的学院信息
     *
     * @param request 请求
     * @param response 响应，将会返回一个集合，里面包含所有的学院名字
     * @throws ServletException 抛出错误
     * @throws IOException 抛出错误
     */
    protected void ajaxSearchCollege(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<College> colleges = cgcms.searchAllCollege();
        Gson gson = new Gson();
        String json = gson.toJson(colleges);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }

    /**
     * 通过Ajax请求获取所有的年级信息
     *
     * @param request 请求
     * @param response 响应，将会返回一个集合，里面包含所有的年级名字
     * @throws ServletException 抛出错误
     * @throws IOException 抛出错误
     */
    protected void ajaxSearchGrade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Grade> grades = cgcms.searchAllGrade();
        Gson gson = new Gson();
        String json = gson.toJson(grades);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }

    /**
     * 通过Ajax请求获取该学院和年级下的所有班级
     *
     * @param request 请求，要在其中提供一个需要查询的班级所在的学院<code>college</code>和年级<code>grade</code>信息
     * @param response 响应，将会返回一个集合，里面包含了该学院/年级下的所有班级
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxSearchClasses(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String college = request.getParameter("college");
        String grade = request.getParameter("grade");

        List<Classes> classes = cgcms.searchClassByCollegeAndGrade(college, grade);
        Gson gson = new Gson();
        String json = gson.toJson(classes);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }

}
