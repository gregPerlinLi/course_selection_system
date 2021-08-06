package com.gregperlinli.web;

import com.gregperlinli.pojo.Student;
import com.gregperlinli.service.AccountManageService;
import com.gregperlinli.service.impl.AccountManageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author gregperlinli
 */
@WebServlet(name = "RegisterServlet", value = "/registerServlet")
public class RegisterServlet extends HttpServlet {

    private final AccountManageService accountManageService = new AccountManageServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        // 1. Get req parameters
        String stuNum = req.getParameter("stuNum");
        String username = req.getParameter("username");
        String college = req.getParameter("college");
        String grade = req.getParameter("grade");
        String classes = req.getParameter("classes");
        String password = req.getParameter("encryptedPassword");

        // 2. Check whether the username is correct
        if ( accountManageService.studentRegist(new Student(stuNum, username, password, college, grade, classes)) ) {
            // available
            req.getRequestDispatcher("/pages/user/regist_success.html").forward(req, resp);
        } else {
            // not available
            System.out.println("The username [ " + username + " ] is already exist!");
            req.getRequestDispatcher("/pages/login/register.html").forward(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
