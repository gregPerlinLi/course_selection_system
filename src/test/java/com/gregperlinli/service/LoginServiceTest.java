package com.gregperlinli.service;

import com.gregperlinli.service.impl.LoginServerImpl;
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
        final LoginServer LOGIN_SERVER = new LoginServerImpl();
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
        final LoginServer LOGIN_SERVER = new LoginServerImpl();
        final String USERNAME = "gregPerlinLi";
        final String PASSWORD = "123456";
        boolean isCorrect = LOGIN_SERVER.adminLogin(USERNAME, PASSWORD);
        System.out.println(isCorrect);
    }
}
