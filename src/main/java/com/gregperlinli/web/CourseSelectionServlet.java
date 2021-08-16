package com.gregperlinli.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gregperlinli.pojo.Course;
import com.gregperlinli.pojo.SelectedCourse;
import com.gregperlinli.pojo.Student;
import com.gregperlinli.service.CourseManageService;
import com.gregperlinli.service.CourseSelectionService;
import com.gregperlinli.service.impl.CourseManageServiceImpl;
import com.gregperlinli.service.impl.CourseSelectionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 用于学生选课
 *
 * @author gregPerlinLi
 * @since 2021-08-16
 */
@WebServlet(name = "CourseSelectionServlet", value = "/courseSelectionServlet")
public class CourseSelectionServlet extends BaseServlet {
    protected final CourseManageService courseManageService = new CourseManageServiceImpl();
    protected final CourseSelectionService courseSelectionService = new CourseSelectionServiceImpl();
    protected final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 通过CourseSelectionService查询全部可选课程
        List<Course> enabledCourses = courseSelectionService.queryEnabledCourse();
        // 2. 把全部课程数据转换为Json格式
        String json = gson.toJson(enabledCourses);
        // 3. 传回结果
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }

    protected void getSelectedCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 通过CourseSelectionService查询该学生下所有已选课程
        List<SelectedCourse> selectedCourses = courseSelectionService.queryStudentSelectedCourse(((Student) request.getSession().getAttribute("student")).getUsername());
        // 2. 把全部已选课程数据转换为Json格式
        String json = gson.toJson(selectedCourses);
        // 3. 传回结果
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }

    protected void getAllCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 通过CourseSelectionService查询全部可选课程
        List<Course> allCourse = courseManageService.getAllCourse();
        // 2. 把全部课程数据转换为Json格式
        String json = gson.toJson(allCourse);
        // 3. 传回结果
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }

    protected void getEnabledCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 通过CourseSelectionService查询全部可选课程
        List<Course> enabledCourses = courseSelectionService.queryEnabledCourse();
        // 2. 把全部课程数据转换为Json格式
        String json = gson.toJson(enabledCourses);
        // 3. 传回结果
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }
}