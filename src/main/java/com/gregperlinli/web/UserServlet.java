package com.gregperlinli.web;

import com.google.gson.Gson;
import com.gregperlinli.pojo.Classes;
import com.gregperlinli.pojo.College;
import com.gregperlinli.pojo.Grade;
import com.gregperlinli.pojo.Student;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gregperlinli
 */
@WebServlet(name = "UserServlet", value = "/userServlet")
public class UserServlet extends BaseServlet {
    /**
     * Function of handling login
     *
     * @param request request
     * @param response response
     * @throws ServletException e
     * @throws IOException e
     */
    protected void studentLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        request.setCharacterEncoding("UTF-8");

        // 1. Get request parameters
        String username = new String(request.getParameter("username").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String password = request.getParameter("encryptedPassword");

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
     * Function of handling register
     * @param request request
     * @param response response
     * @throws ServletException e
     * @throws IOException e
     */
    protected void studentRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 0. Set the character encoding
        request.setCharacterEncoding("UTF-8");

        // 1. Get request parameters
        String stuNum = request.getParameter("stuNum");
        String username = new String(request.getParameter("username").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String college = new String(request.getParameter("college").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String grade = new String(request.getParameter("grade").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String classes = new String(request.getParameter("classes").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String password = request.getParameter("encryptedPassword");

        // 2. Check whether the username is correct
        if ( accountManageService.studentRegist(new Student(stuNum, username, password, college, grade, classes)) ) {
            // available
            request.getRequestDispatcher("/pages/user/regist_success.html").forward(request, response);
        } else {
            // not available
            System.out.println("The username [ " + username + " ] is already exist!");
            request.getRequestDispatcher("/pages/login/register.html").forward(request, response);
        }

    }

    protected void ajaxExistStuNum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String stuNum = request.getParameter("stuNum");

        boolean existStuNum = accountManageService.existStudentStuNum(stuNum);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("existStuNum", existStuNum);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        response.getWriter().write(json);
    }
    protected void ajaxExistStuUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        boolean existUsername = accountManageService.existStudentUsername(username);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("existUsername", existUsername);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        response.getWriter().write(json);
    }
    protected void ajaxSearchCollege(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<College> colleges = cgcms.searchAllCollege();
        Gson gson = new Gson();
        String json = gson.toJson(colleges);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }
    protected void ajaxSearchGrade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Grade> grades = cgcms.searchAllGrade();
        Gson gson = new Gson();
        String json = gson.toJson(grades);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }
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
