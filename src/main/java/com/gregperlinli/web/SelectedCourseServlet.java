package com.gregperlinli.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gregperlinli.pojo.SelectedCourse;
import com.gregperlinli.service.CourseManageService;
import com.gregperlinli.service.CourseSelectionService;
import com.gregperlinli.service.impl.CourseManageServiceImpl;
import com.gregperlinli.service.impl.CourseSelectionServiceImpl;
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
 * 用于已选课程信息的管理（管理员权限）
 *
 * @author gregPerlinLi
 * @see javax.servlet.ServletConfig
 * @see javax.servlet.Servlet
 * @see javax.servlet.http.HttpServlet
 * @see javax.servlet.GenericServlet
 * @see java.io.Serializable
 * @see com.gregperlinli.web.BaseServlet
 * @since 2021-08-16
 */
@WebServlet(name = "SelectedCourseServlet", value = "/admin/selectedCourseServlet")
public class SelectedCourseServlet extends BaseServlet {
    protected final CourseManageService courseManageService = new CourseManageServiceImpl();
    protected final CourseSelectionService courseSelectionService = new CourseSelectionServiceImpl();
    protected final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

    /**
     * 通过Ajax请求获得所有已选课程信息
     *
     * @param request 请求
     * @param response 响应，将会返回一个包含所有已选课程信息的对象集合<code>allSelectedCourse</code>
     * @throws ServletException 抛出错误
     * @throws IOException 抛出错误
     */
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1. 通过CourseSelectionService查询所有已选课程信息
        List<SelectedCourse> allSelectedCourses = courseManageService.getAllSelectedCourse();
        // 2. 把全部已选课程数据转换为Json格式
        String json = gson.toJson(allSelectedCourses);
        // 3. 传回结果
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);

    }

    /**
     * 通过Ajax请求对已选课程信息进行删除操作
     *
     * @param request 请求，需要提供要删除的已选课程信息<code>id</code>
     * @param response 响应，将会返回一个布尔值<code>isCanceled</code>，若为<code>true</code>则删除成功，若为<code>false/code>则删除失败
     * @throws ServletException 抛出错误
     * @throws IOException 抛出错误
     */
    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从request中获取要删除的已选课程信息的id
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        // 通过CourseSelectionService进行退选操作
        boolean isCanceled = courseSelectionService.cancelSelection(id);
        // 设置输出集
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("isCanceled", isCanceled);
        // 转换为Json格式
        String json = gson.toJson(resultMap);
        // 传回结果
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }
}