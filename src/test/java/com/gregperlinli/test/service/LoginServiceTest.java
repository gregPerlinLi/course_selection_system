package com.gregperlinli.test.service;

import com.gregperlinli.pojo.Admin;
import com.gregperlinli.pojo.Student;
import com.gregperlinli.service.LoginService;
import com.gregperlinli.service.impl.LoginServiceImpl;
import org.junit.jupiter.api.Test;

/**
 * 对<code>LoginService</code>进行单元测试
 *
 * @author gregperlinli
 */
public class LoginServiceTest {
    final private LoginService loginService = new LoginServiceImpl();

    /**
     * 测试<code>studentLogin()</code>方法
     */
    @Test
    public void studentLogin() {
        final String username = "李浩霖";
        final String password = "123456";
        Student student = loginService.studentLogin(username, password);
        boolean isCorrect;
        if ( student != null ) {
            isCorrect = true;
        } else {
            isCorrect = false;
        }
        System.out.println(isCorrect);
    }
    /**
     * 测试<code>adminLogin()</code>方法
     */
    @Test
    public void adminLogin() {
        final String username = "gregPerlinLi";
        final String password = "123456";
        Admin admin = loginService.adminLogin(username, password);
        boolean isCorrect;
        if ( admin != null ) {
            isCorrect = true;
        } else {
            isCorrect = false;
        }
        System.out.println(isCorrect);
    }
}
