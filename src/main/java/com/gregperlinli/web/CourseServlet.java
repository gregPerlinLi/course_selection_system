package com.gregperlinli.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gregperlinli.pojo.Course;
import com.gregperlinli.service.CourseManageService;
import com.gregperlinli.service.impl.CourseManageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于课程的管理（管理员权限）
 *
 * @author gregPerlinLi
 * @since 2021-08-11
 */
@WebServlet(name = "CourseServlet", value = "/admin/courseServlet")
public class CourseServlet extends BaseServlet {
    protected final CourseManageService courseManageService = new CourseManageServiceImpl();
    protected final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String courseName = request.getParameter("courseName");
        Date startDate = Date.valueOf(request.getParameter("startDate"));
        String startTimeStr = request.getParameter("startTime");
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        java.util.Date d = null;
        try {
            d = format.parse(startTimeStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Time startTime = new java.sql.Time(d.getTime());
        Integer maxStu = Integer.parseInt(request.getParameter("maxStu"));
        Course course = new Course(courseName, startDate, startTime, maxStu);

        if ( courseManageService.addCourse(course) ) {
            // 添加成功
            response.sendRedirect(request.getContextPath() + "/pages/admin/course_management.html");
        } else {
            // 添加失败
            System.out.println("The course [ " + course.getCourseName() + " ] is already exist!");
            request.getRequestDispatcher("/pages/admin/add_course.html").forward(request, response);
        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从request中获取要删除的id值
        int id = Integer.parseInt(request.getParameter("id"));
        // 删除数据，并获取是否成功删除
        boolean isDeleted = courseManageService.deleteCourse(id);
        // 设置输出集
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("isDeleted", isDeleted);
        // 转换为Json格式
        String json = gson.toJson(resultMap);
        // 传回删除结果
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void getCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Course course = courseManageService.getCourseById(id);

        String json = gson.toJson(course);

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);

    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 通过CourseManageService查询全部课程
        List<Course> allCourse = courseManageService.getAllCourse();
        // 2. 把全部课程数据转换为Json格式
        String json = gson.toJson(allCourse);
        // 3. 传回结果
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }

    protected void existCourseName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseName = request.getParameter("courseName");

        boolean existCourseName = courseManageService.existCourseName(courseName);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("existCourseName", existCourseName);

        String json = gson.toJson(resultMap);

        response.getWriter().write(json);
    }
}
