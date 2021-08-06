package com.gregperlinli.web;

import com.gregperlinli.service.AccountManageService;
import com.gregperlinli.service.CollegeGradeClassManageService;
import com.gregperlinli.service.LoginService;
import com.gregperlinli.service.impl.AccountManageServiceImpl;
import com.gregperlinli.service.impl.CollegeGradeClassManageServiceImpl;
import com.gregperlinli.service.impl.LoginServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author gregperlinli
 */
@WebServlet(name = "BaseServlet", value = "/BaseServlet")
public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            // Through the action business identification string, get the corresponding business method reflection
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            // Call target business method
            method.invoke(this, request, response);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            // Through the action business identification string, get the corresponding business method reflection
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            // Call target business method
            method.invoke(this, request, response);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}
