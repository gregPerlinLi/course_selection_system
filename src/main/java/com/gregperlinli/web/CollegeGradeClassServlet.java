package com.gregperlinli.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gregperlinli.pojo.Classes;
import com.gregperlinli.pojo.College;
import com.gregperlinli.pojo.Grade;
import com.gregperlinli.service.CollegeGradeClassManageService;
import com.gregperlinli.service.impl.CollegeGradeClassManageServiceImpl;
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
 * 用于学院/年级/班级的管理（管理员权限）
 *
 * @author gregPerlinLi
 * @see javax.servlet.ServletConfig
 * @see javax.servlet.Servlet
 * @see javax.servlet.http.HttpServlet
 * @see javax.servlet.GenericServlet
 * @see java.io.Serializable
 * @see com.gregperlinli.web.BaseServlet
 * @since 2021-08-17
 */
@WebServlet(name = "CollegeGradeClassServlet", value = "/admin/collegeGradeClassServlet")
public class CollegeGradeClassServlet extends BaseServlet {
    protected final CollegeGradeClassManageService cgcms = new CollegeGradeClassManageServiceImpl();
    protected final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    protected final static String COLLEGE = "college";
    protected final static String GRADE = "grade";
    protected final static String CLASSES = "class";

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        String json = "";
        // 判断要获得的是学院，年级，还是班级，然后把全部数据转换为Json格式
        if (COLLEGE.equals(type)) {
            List<College> colleges = cgcms.searchAllCollege();
            json = gson.toJson(colleges);
        }
        if (GRADE.equals(type)) {
            List<Grade> grades = cgcms.searchAllGrade();
            json = gson.toJson(grades);
        }
        if (CLASSES.equals(type)) {
            List<Classes> classesList = cgcms.searchAllClass();
            json = gson.toJson(classesList);
        }
        // 传回结果
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        boolean isAdded = false;
        // 判断要获得的是学院，年级，还是班级
        if (COLLEGE.equals(type)) {
            isAdded = cgcms.collegeInsert(new College(request.getParameter("collegeName")));
        }
        if (GRADE.equals(type)) {
            isAdded = cgcms.gradeInsert(new Grade(request.getParameter("gradeName")));
        }
        if (CLASSES.equals(type)) {
            if (cgcms.classInsert(WebUtils.copyParamToBean(request.getParameterMap(), new Classes()))) {
                response.sendRedirect(request.getContextPath() + "/pages/admin/classes_management.html");
            } else {
                System.out.println("The class is already exist!");
                response.sendRedirect(request.getContextPath() + "/pages/admin/add_classes.html");
            }
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("isAdded", isAdded);

        String json = gson.toJson(resultMap);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        boolean isUpdated = false;
        // 判断要获得的是学院，年级，还是班级
        if (COLLEGE.equals(type)) {
            isUpdated = cgcms.collegeUpdate(new College(WebUtils.parseInt(request.getParameter("id"), 0), request.getParameter("collegeName")));
        }
        if (GRADE.equals(type)) {
            isUpdated = cgcms.gradeUpdate(new Grade(WebUtils.parseInt(request.getParameter("id"), 0), request.getParameter("gradeName")));
        }
        if (CLASSES.equals(type)) {
            if (cgcms.classUpdate(WebUtils.copyParamToBean(request.getParameterMap(), new Classes()))) {
                response.sendRedirect(request.getContextPath() + "/pages/admin/classes_management.html");
            } else {
                System.out.println("The class is already exist!");
                response.sendRedirect(request.getContextPath() + "/pages/admin/update_classes.html");
            }
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("isUpdated", isUpdated);

        String json = gson.toJson(resultMap);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        boolean isDeleted = false;
        // 判断要获得的是学院，年级，还是班级
        if (COLLEGE.equals(type)) {
            isDeleted = cgcms.collegeDelete(WebUtils.parseInt(request.getParameter("id"), 0));
        }
        if (GRADE.equals(type)) {
            isDeleted = cgcms.gradeDelete(WebUtils.parseInt(request.getParameter("id"), 0));
        }
        if (CLASSES.equals(type)) {
            isDeleted = cgcms.classDelete(WebUtils.parseInt(request.getParameter("id"), 0));
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("isDeleted", isDeleted);

        String json = gson.toJson(resultMap);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }

    protected void ajaxExistClassName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String className = request.getParameter("className");

        boolean existClassName = cgcms.existClassName(className);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("existClassName", existClassName);

        String json = gson.toJson(resultMap);

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }

    protected void getClass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = WebUtils.parseInt(request.getParameter("id"), 0);

        Classes classes = cgcms.getClassById(id);

        String json = gson.toJson(classes);

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(json);
    }
}
