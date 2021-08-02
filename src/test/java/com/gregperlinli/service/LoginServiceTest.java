package com.gregperlinli.service;

import com.gregperlinli.service.impl.LoginServiceImpl;
import org.junit.jupiter.api.Test;

/**
 * 对<code>LoginService</code>进行单元测试
 *
 * @author gregperlinli
 */
public class LoginServiceTest {
    /**
     * 测试<code>studentLogin()</code>方法
     */
    @Test
    public void testStudentLogin() {
        final LoginService LOGIN_SERVER = new LoginServiceImpl();
        final String USERNAME = "李浩霖";
        final String PASSWORD = "123456";
        boolean isCorrect = LOGIN_SERVER.studentLogin(USERNAME, PASSWORD);
        System.out.println(isCorrect);
    }
    /**
     * 测试<code>adminLogin()</code>方法
     */
    @Test
    public void testAdminLogin() {
        final LoginService LOGIN_SERVER = new LoginServiceImpl();
        final String USERNAME = "gregPerlinLi";
        final String PASSWORD = "123456";
        boolean isCorrect = LOGIN_SERVER.adminLogin(USERNAME, PASSWORD);
        System.out.println(isCorrect);
    }
}
