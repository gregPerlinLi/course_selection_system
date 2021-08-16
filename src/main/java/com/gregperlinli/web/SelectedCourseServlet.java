package com.gregperlinli.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gregperlinli.pojo.SelectedCourse;
import com.gregperlinli.service.CourseManageService;
import com.gregperlinli.service.impl.CourseManageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 用于已选课程信息的管理（管理员权限）
 *
 * @author gregPerlinLi
 * @since 2021-08-16
 */
@WebServlet(name = "SelectedCourseServlet", value = "/admin/selectedCourseServlet")
public class SelectedCourseServlet extends BaseServlet {
    protected final CourseManageService courseManageService = new CourseManageServiceImpl();
    protected final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1. 通过CourseSelectionService查询所有已选课程信息
        List<SelectedCourse> allSelectedCourses = courseManageService.getAllSelectedCourse();
        // 2. 把全部已选课程数据转换为Json格式
        String json = gson.toJson(allSelectedCourses);
        // 3. 传回结果
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);

    }
}