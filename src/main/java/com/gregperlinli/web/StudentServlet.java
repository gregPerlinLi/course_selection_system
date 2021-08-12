package com.gregperlinli.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gregperlinli.pojo.Student;
import com.gregperlinli.service.AccountManageService;
import com.gregperlinli.service.impl.AccountManageServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 * 用于学生管理（管理员权限）
 *
 * @author gregPerlinLi
 * @since 2021-08-12
 */
@WebServlet(name = "StudentServlet", value = "/admin/studentServlet")
public class StudentServlet extends BaseServlet {
    private final AccountManageService accountManageService = new AccountManageServiceImpl();
    protected final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 通过AccountManageService查询全部课程
        List<Student> allStudent = accountManageService.getAllStudent();
        // 2. 把全部课程数据转换为Json格式
        String json = gson.toJson(allStudent);
        // 3. 传回结果
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }

    protected void existStuNum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void existStuUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
