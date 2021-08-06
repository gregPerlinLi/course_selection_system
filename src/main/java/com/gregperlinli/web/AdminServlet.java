package com.gregperlinli.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 管理员
 *
 * @author gregPerlinLi
 * @since 2021-08-06
 */
@WebServlet(name = "AdminServlet", value = "/adminServlet")
public class AdminServlet extends BaseServlet{
    protected void adminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 1. Get request parameters
        String username = new String(request.getParameter("username").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String password = request.getParameter("encryptedPassword");

        System.out.println(username);
        System.out.println(password);

        // 2.
        if ( loginService.adminLogin(username, password) != null ) {
            //
            System.out.println("Login success!");
            request.getRequestDispatcher("pages/admin/admin_index.html").forward(request, response);
        } else {
            System.out.println("Login failed!");
            request.getRequestDispatcher("/pages/login/admin_login.html").forward(request, response);
        }
    }
}
