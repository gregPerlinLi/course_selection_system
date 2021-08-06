package com.gregperlinli.web;

import com.gregperlinli.service.LoginService;
import com.gregperlinli.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author gregperlinli
 */
@WebServlet(name = "LoginServlet", value = "/loginServlet")
public class LoginServlet extends HttpServlet {

    private final LoginService loginService = new LoginServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Get request parameters
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 2.
        if ( loginService.studentLogin(username, password) != null ) {
            //
            System.out.println("Login success!");
            req.getRequestDispatcher("/pages/user/index.html").forward(req, resp);
        } else {
            System.out.println("Login failed!");
            req.getRequestDispatcher("/pages/login/login.html").forward(req, resp);
        }
    }
}
