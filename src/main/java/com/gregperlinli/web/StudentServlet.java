package com.gregperlinli.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gregperlinli.pojo.Student;
import com.gregperlinli.service.AccountManageService;
import com.gregperlinli.service.impl.AccountManageServiceImpl;
import com.gregperlinli.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于学生管理（管理员权限）
 *
 * @author gregPerlinLi
 * @since 2021-08-12
 */
@WebServlet(name = "StudentServlet", value = "/admin/studentServlet")
public class StudentServlet extends BaseServlet {
    protected final AccountManageService accountManageService = new AccountManageServiceImpl();
    protected final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("updating");
        Student student = WebUtils.copyParamToBean(request.getParameterMap(), new Student());

        if ( accountManageService.studentUpdate(student) ) {
            // 修改成功
            response.sendRedirect(request.getContextPath() + "/pages/admin/student_management.html");
        } else {
            // 修改失败
            System.out.println("The student [ " + student.getUsername() + " ] is already exist!");
            response.sendRedirect(request.getContextPath() + "/pages/admin/update_student.html?id=" + student.getId());
        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从request中获取要删除的id值
        int id = Integer.parseInt(request.getParameter("id"));
        // 删除数据，并获取是否成功删除
        boolean isDeleted = accountManageService.studentDelete(id);
        // 设置输出集
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("isDeleted", isDeleted);
        // 转换为Json格式
        String json = gson.toJson(resultMap);
        // 传回删除结果
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }

    protected void getStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = accountManageService.getStuById(id);

        String json = gson.toJson(student);

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
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
