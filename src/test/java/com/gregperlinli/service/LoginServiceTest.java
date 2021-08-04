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
        final LoginService loginService = new LoginServiceImpl();
        final String username = "李浩霖";
        final String password = "123456";
        boolean isCorrect = loginService.studentLogin(username, password);
        System.out.println(isCorrect);
    }
    /**
     * 测试<code>adminLogin()</code>方法
     */
    @Test
    public void testAdminLogin() {
        final LoginService loginService = new LoginServiceImpl();
        final String username = "gregPerlinLi";
        final String password = "123456";
        boolean isCorrect = loginService.adminLogin(username, password);
        System.out.println(isCorrect);
    }
}
