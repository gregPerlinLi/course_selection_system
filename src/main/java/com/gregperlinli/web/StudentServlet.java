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
 * @see javax.servlet.ServletConfig
 * @see javax.servlet.Servlet
 * @see javax.servlet.http.HttpServlet
 * @see javax.servlet.GenericServlet
 * @see java.io.Serializable
 * @see com.gregperlinli.web.BaseServlet
 * @since 2021-08-12
 */
@WebServlet(name = "StudentServlet", value = "/admin/studentServlet")
public class StudentServlet extends BaseServlet {
    protected final AccountManageService accountManageService = new AccountManageServiceImpl();
    protected final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    /**
     * 处理添加学生功能<br/>
     * 若添加成功会重定向到<code>student_management.html</code>，若失败则继续停留在该界面
     *
     * @param request 添加请求，需要通过POST请求提供学生学号<code>stuNum</code>，
     *                学生学号<code>username</code>，
     *                所在学院<code>college</code>，
     *                所在年级<code>grade</code>，
     *                所在班级<code>stuClass</code>，
     *                MD5加密后的密码<code>password</code>
     * @param response 添加响应
     * @throws ServletException 抛出错误
     * @throws IOException 抛出错误
     */
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student student = WebUtils.copyParamToBean(request.getParameterMap(), new Student());

        if ( accountManageService.studentRegist(student) ) {
            // 添加成功
            response.sendRedirect(request.getContextPath() + "/pages/admin/student_management.html");
        } else {
            // 添加失败
            System.out.println("The studentt [ " + student.getUsername() + " ] is already exist!");
            response.sendRedirect(request.getContextPath() + "pages/admin/add_student.html");
        }
    }

    /**
     * 处理修改学生信息功能
     * 若修改成功会重定向到<code>student_management.html</code>，若失败则继续停留在该界面
     *
     * @param request 修改请求，需要通过POST请求提供要修改的学生<code>id</code>，
     *                学生学号<code>stuNum</code>，
     *                学生姓名<code>username</code>，
     *                所在学院<code>college</code>，
     *                所在年级<code>grade</code>，
     *                所在班级<code>stuClass</code>，
     *                MD5加密后的密码<code>password</code>
     * @param response 修改响应
     * @throws ServletException 抛出错误
     * @throws IOException 抛出错误
     */
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

    /**
     * 通过Ajax请求处理删除学生功能
     *
     * @param request 删除请求，要在其中输入一个需要删除的学生<coed>id</coed>
     * @param response 删除响应，将会返回一个布尔值<code>isDeleted</code>，若为<code>True</code>，则成功删除，若为<code>false</code>，则删除失败
     * @throws ServletException 抛出错误
     * @throws IOException 抛出错误
     */
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从request中获取要删除的id值
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
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

    /**
     * 通过Ajax请求获取某个<code>id</code>对应的课程信息
     *
     * @param request 请求，要在其中输入一个需要获取的学生<code>id</code>
     * @param response 响应，将会返回一个包含所查找的学生信息的对象<code>course</code>
     * @throws ServletException 抛出错误
     * @throws IOException 抛出错误
     */
    protected void getStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        Student student = accountManageService.getStuById(id);

        String json = gson.toJson(student);

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }

    /**
     * 通过Ajax请求获得所有学生信息
     *
     * @param request 请求
     * @param response 响应，将会返回一个包含所有学生信息的对象集合<code>allCourse</code>
     * @throws ServletException 抛出错误
     * @throws IOException 抛出错误
     */
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
