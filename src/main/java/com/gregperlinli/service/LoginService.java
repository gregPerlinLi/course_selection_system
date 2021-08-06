package com.gregperlinli.service;

import com.gregperlinli.pojo.Admin;
import com.gregperlinli.pojo.Student;

/**
 * 用于进行登录操作
 *
 * @author gregperlinli
 * @since 2021-7-26
 */
public interface LoginService {

    /**
     * 普通学生的登录操作
     *
     * @param username 要登录的学生账号名
     * @param password 登录时输入的密码
     * @return 返回一个登录好的学生对象，若返回值为<code>null</code>则用户名或密码错误
     */
    Student studentLogin(String username, String password);

    /**
     * 管理员的登录操作
     *
     * @param username 要登录的管理员账号名
     * @param password 登录时输入的密码
     * @return 返回一个登录好的管理员对象，若返回值为<code>null</code>则用户名或密码错误
     */
    Admin adminLogin(String username, String password);

}
