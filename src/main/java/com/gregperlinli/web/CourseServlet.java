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


    /**
     * 处理添加课程功能<br/>
     * 若添加成功会重定向到<code>course_management.html</code>，若失败则继续停留在该界面
     *
     * @param request 添加请求，需要通过POST请求提供课程名<code>courseName</code>，
     *                开始选课日期<code>startDate</code>，
     *                开始选课日期<code>startTime</code>，
     *                限选人数<code>maxStu</code>
     * @param response 添加响应
     * @throws ServletException 抛出错误
     * @throws IOException 抛出错误
     */
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
            response.sendRedirect(request.getContextPath() + "/pages/admin/add_course.html");
        }
    }

    /**
     * 通过Ajax请求处理删除课程功能
     *
     * @param request 删除请求，要在其中输入一个需要删除的课程<coed>id</coed>
     * @param response 删除响应，将会返回一个布尔值<code>isDeleted</code>，若为<code>True</code>，则成功删除，若为<code>false</code>，则删除失败
     * @throws ServletException 抛出错误
     * @throws IOException 抛出错误
     */
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

    /**
     * 处理修改课程功能
     * 若修改成功会重定向到<code>course_management.html</code>，若失败则继续停留在该界面
     *
     * @param request 修改请求，需要通过POST请求提供要修改的课程<code>id</code>，
     *                课程名<code>courseName</code>，
     *                开始选课日期<code>startDate</code>，
     *                开始选课日期<code>startTime</code>，
     *                限选人数<code>maxStu</code>
     * @param response 修改响应
     * @throws ServletException 抛出错误
     * @throws IOException 抛出错误
     */
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
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
        Course course = new Course(id, courseName, startDate, startTime, maxStu);

        if ( courseManageService.updateCourse(course) ) {
            // 添加成功
            response.sendRedirect(request.getContextPath() + "/pages/admin/course_management.html");
        } else {
            // 添加失败
            System.out.println("The course [ " + course.getCourseName() + " ] is already exist!");
            response.sendRedirect(request.getContextPath() + "/pages/admin/update_course.html?id=" + course.getId());
        }
    }

    /**
     * 通过Ajax请求获取某个<code>id</code>对应的课程信息
     *
     * @param request 请求，要在其中输入一个需要获取的课程<code>id</code>
     * @param response 响应，将会返回一个包含所查找的课程信息的对象<code>course</code>
     * @throws ServletException 抛出错误
     * @throws IOException 抛出错误
     */
    protected void getCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Course course = courseManageService.getCourseById(id);

        String json = gson.toJson(course);

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);

    }

    /**
     * 通过Ajax请求获得所有课程信息
     *
     * @param request 请求
     * @param response 响应，将会返回一个包含所有课程信息的对象集合<code>allCourse</code>
     * @throws ServletException 抛出错误
     * @throws IOException 抛出错误
     */
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 通过CourseManageService查询全部课程
        List<Course> allCourse = courseManageService.getAllCourse();
        // 2. 把全部课程数据转换为Json格式
        String json = gson.toJson(allCourse);
        // 3. 传回结果
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }

    /**
     * 通过Ajax请求检查是否存在该课程名
     *
     * @param request 请求，要在其中输入一个需要检查的课程名参数<code>courseName</code>
     * @param response 响应，将会返回一个布尔值<code>existCourseName</code>，若为<code>True</code>，则已存在该课程名，若为<code>false</code>，则不存在该课程名
     * @throws ServletException 抛出错误
     * @throws IOException 抛出错误
     */
    protected void existCourseName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseName = request.getParameter("courseName");

        boolean existCourseName = courseManageService.existCourseName(courseName);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("existCourseName", existCourseName);

        String json = gson.toJson(resultMap);

        response.getWriter().write(json);
    }
}
